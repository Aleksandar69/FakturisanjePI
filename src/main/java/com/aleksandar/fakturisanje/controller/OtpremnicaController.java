package com.aleksandar.fakturisanje.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aleksandar.fakturisanje.converter.OtpremnicaDtoToOtpremnica;
import com.aleksandar.fakturisanje.converter.OtpremnicaToOtpremnicaDto;
import com.aleksandar.fakturisanje.converter.RobaUslugaToRobaUslugaDto;
import com.aleksandar.fakturisanje.converter.StavkaOtpremniceToStavkaOtpremniceDto;
import com.aleksandar.fakturisanje.dto.OtpremnicaDto;
import com.aleksandar.fakturisanje.dto.StavkaOtpremniceDto;
import com.aleksandar.fakturisanje.model.Cjenovnik;
import com.aleksandar.fakturisanje.model.Mjesto;
import com.aleksandar.fakturisanje.model.Otpremnica;
import com.aleksandar.fakturisanje.model.PoslovnaGodina;
import com.aleksandar.fakturisanje.model.PoslovniPartner;
import com.aleksandar.fakturisanje.model.Preduzece;
import com.aleksandar.fakturisanje.model.RobaUsluga;
import com.aleksandar.fakturisanje.model.StavkaCjenovnika;
import com.aleksandar.fakturisanje.model.StavkaOtpremnice;
import com.aleksandar.fakturisanje.service.interfaces.IOtpremnicaService;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovnaGodinaService;
import com.aleksandar.fakturisanje.service.interfaces.IPreduzeceService;
import com.aleksandar.fakturisanje.service.interfaces.IStavkaOtpremniceService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@RestController
@RequestMapping("api/otpremnice")
public class OtpremnicaController {
	
    @Autowired
    private IOtpremnicaService otpremnicaService;

    @Autowired
    private IStavkaOtpremniceService stavkaOtpremniceService;
    
    @Autowired
    private IPreduzeceService preduzeceService;

    @Autowired
    private IPoslovnaGodinaService poslovnaGodinaService;

    @Autowired
    private OtpremnicaDtoToOtpremnica otpremnicaDtoToOtpremnica;

    @Autowired
    private OtpremnicaToOtpremnicaDto otpremnicaToOtpremnicaDto;

    @Autowired
    private StavkaOtpremniceToStavkaOtpremniceDto stavkaOtpremniceToStavkaOtpremniceDto;

    @Autowired
    private RobaUslugaToRobaUslugaDto robaUslugaToDto;
    
    
    @GetMapping
    public ResponseEntity getAll(@RequestParam(value = "godina", defaultValue = "0") int godina,
                                 @RequestParam(value = "page",defaultValue = "0") int page,
                                 @RequestParam(value = "num",defaultValue = Integer.MAX_VALUE+"") int num,
                                 @RequestParam(value = "naziv",defaultValue = "") String naziv){

        Page<Otpremnica> otpremnice;

        if(godina==0){
            otpremnice = otpremnicaService.findAllByNazivPartnera(naziv, page, num);
        } else {
            otpremnice = otpremnicaService.findAllByPoslovnaGodinaAndNazivPartnera(naziv, godina, page, num);
        }

        List<OtpremnicaDto> otpremniceDto = otpremnicaToOtpremnicaDto.convert(otpremnice.getContent());

        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(otpremnice.getTotalPages()));
        return ResponseEntity.ok()
                .headers(headers)
                .body(otpremniceDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") long id){

        Otpremnica otpremnica = otpremnicaService.findOne(id);
        if(otpremnica == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        OtpremnicaDto otpremnicaDto = otpremnicaToOtpremnicaDto.convert(otpremnica);
        return new ResponseEntity<>(otpremnicaDto, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity dodajOptremnicu(@Validated @RequestBody OtpremnicaDto dto, Errors errors) {

        if(errors.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        PoslovnaGodina poslednjaPoslovnaGodina = poslovnaGodinaService.findPoslovnaGodinaIsFalse();
        Otpremnica otpremnica = otpremnicaDtoToOtpremnica.convert(dto);
        otpremnica.setBrojOtpremnice(poslednjaPoslovnaGodina.getOtpremnice().size()+1);
        otpremnica.setDatumOtpremnice(new Date());
        otpremnica.setTipOtpremnice(otpremnica.getPoslovniPartner().getVrstaPartnera() == 1);
        otpremnica.setPoslovnaGodina(poslednjaPoslovnaGodina);
        otpremnica .setObrisano(false);

        Otpremnica dbOtpremnica = otpremnicaService.save(otpremnica);
        OtpremnicaDto otpremnicaDto = otpremnicaToOtpremnicaDto.convert(dbOtpremnica);

        return new ResponseEntity<>(otpremnicaDto, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/napraviFakturu")
    public ResponseEntity napraviFakturuOdOtpremnice(@PathVariable("id") long id) {

        PoslovnaGodina poslovnaGodina = poslovnaGodinaService.findPoslovnaGodinaIsFalse();
        int poslednjaPoslovnjaGodina = poslovnaGodina.getFakture().size();

        Otpremnica otpremnica = otpremnicaService.findOne(id);
        otpremnicaService.napraviFakturuOdOtpremnice(otpremnica, poslednjaPoslovnjaGodina);

        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}/stavkeOtpremnice")
    public ResponseEntity getStavke(@PathVariable("id") long id){

        List<StavkaOtpremnice> stavkeOtpremnice = stavkaOtpremniceService.pronadjiStavkeOtpremnice(id);
        List<StavkaOtpremniceDto> stavkeOtpremniceDto = stavkaOtpremniceToStavkaOtpremniceDto.convert(stavkeOtpremnice);
        return new ResponseEntity<>(stavkeOtpremniceDto, HttpStatus.OK);
    }
    
    @GetMapping("/{id}/robaCenovnika")
    public ResponseEntity getCenovnikRoba(@PathVariable("id") long id){
    	Otpremnica otpremnica = otpremnicaService.findOne(id);
        if(otpremnica==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List<Cjenovnik> cenovnici = new ArrayList<Cjenovnik>();
        if (otpremnica.getPoslovniPartner().getVrstaPartnera()==1)
        	cenovnici.addAll(otpremnica.getPoslovniPartner().getCjenovnici());
        else
        	cenovnici.addAll(otpremnica.getPreduzece().getCjenovnici());
        Cjenovnik c = cenovnici.get(cenovnici.size()-1);
        ArrayList<RobaUsluga> roba = new ArrayList();
        for (StavkaCjenovnika s : c.getStavkeCjenovnika()) {
        	roba.add(s.getRobaUsluga());
        }
        return ResponseEntity.ok(robaUslugaToDto.convert(roba));
    }
    
    @GetMapping("/{id}/report")
    public ResponseEntity getReport(@PathVariable("id") long id) {
    	
    
    	Otpremnica otpremnica = otpremnicaService.findOne(id);
    	List<StavkaOtpremnice> stavkeOtpremnice = stavkaOtpremniceService.pronadjiStavkeOtpremnice(id);
    	Preduzece preduzece = otpremnica.getPreduzece();
    	PoslovniPartner poslovniPartner = otpremnica.getPoslovniPartner();
    	Mjesto mjesto = preduzece.getMjesto();
    	
    	if(otpremnica == null) {
    		return new ResponseEntity(HttpStatus.NOT_FOUND);
    	}
    	JRBeanCollectionDataSource stavkeOtpremniceJasper = new JRBeanCollectionDataSource(stavkeOtpremnice);
    	
    	Map<String, Object> params = new HashMap<>();
    	
    	params.put("otpremnica", otpremnica);
    	params.put("stavkeOtpremnice", stavkeOtpremniceJasper);
    	params.put("preduzece", preduzece);
    	params.put("poslovniPartner", poslovniPartner);
    	params.put("mjesto", mjesto);
    	
    	
    	try {
    		
    	InputStream is = new FileInputStream(new File("E:\\PI projekat\\FakturisanjeV2\\FakturisanjePI\\src\\main\\resources\\otpremnica.jrxml"));
    	
    	JasperDesign jasperDesign = JRXmlLoader.load(is);
           
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
   		
    	
    	JasperPrint jp = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
		ByteArrayInputStream bis = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename="+otpremnica.getBrojOtpremnice()+"-"+otpremnica.getPoslovnaGodina().getGodina()+".pdf");
		return ResponseEntity
	       		.ok()
	       		.headers(headers)
	       		.contentType(MediaType.APPLICATION_PDF)
	       		.body(new InputStreamResource(bis));
	}catch (Exception ex) {
			ex.printStackTrace();
		}
	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    	
    	}
}
