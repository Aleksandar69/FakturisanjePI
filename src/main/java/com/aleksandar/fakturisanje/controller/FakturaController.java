package com.aleksandar.fakturisanje.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aleksandar.fakturisanje.converter.FakturaDtoToFaktura;
import com.aleksandar.fakturisanje.converter.FakturaToFakturaDto;
import com.aleksandar.fakturisanje.converter.RobaUslugaToRobaUslugaDto;
import com.aleksandar.fakturisanje.converter.StavkaFaktureToStavkaFaktureDto;
import com.aleksandar.fakturisanje.model.Cjenovnik;
import com.aleksandar.fakturisanje.model.Faktura;
import com.aleksandar.fakturisanje.model.RobaUsluga;
import com.aleksandar.fakturisanje.model.StavkaCjenovnika;
import com.aleksandar.fakturisanje.model.StavkaFakture;
import com.aleksandar.fakturisanje.service.interfaces.IFakturaService;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovnaGodinaService;
import com.aleksandar.fakturisanje.service.interfaces.IStavkaFaktureService;

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
@RequestMapping("api/fakture")
public class FakturaController {

	@Autowired
    private IStavkaFaktureService stavkaFaktureServiceInterface;
	
    @Autowired
    private IFakturaService fakturaServiceInterface;

    @Autowired
    private IPoslovnaGodinaService poslovnaGodinaServiceInterface;

    @Autowired
    private FakturaDtoToFaktura toFaktura;

    @Autowired
    private FakturaToFakturaDto toDto;
    
    @Autowired
    private StavkaFaktureToStavkaFaktureDto stavkaFaktureToDto;
    
    @Autowired
    private RobaUslugaToRobaUslugaDto robaUslugaToDto;
    
    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(toDto.convert(fakturaServiceInterface.findAll()));
    }

    
    @GetMapping(value = "/ulazne")
    public ResponseEntity getUlazne(@RequestParam(value = "godina", defaultValue = "0") int godina,
                                    @RequestParam(value = "page",defaultValue = "0") int page,
                                    @RequestParam(value = "num",defaultValue = Integer.MAX_VALUE+"") int num,
                                    @RequestParam(value = "naziv",defaultValue = "") String naziv){
        if(godina==0){
            Page<Faktura> fakture = fakturaServiceInterface.findAllByVrstaFaktureAndNazivPartnera(
                    true,naziv,page,num);
            HttpHeaders headers = new HttpHeaders();
            headers.set("total",String.valueOf(fakture.getTotalPages()));
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(toDto.convert(fakture.getContent()));
        }
        Page<Faktura> fakture = fakturaServiceInterface.findAllByVrstaFaktureAndPoslovnaGodinaAndNazivPartnera(
                true,naziv,godina,page,num);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total",String.valueOf(fakture.getTotalPages()));
        return ResponseEntity.ok()
                .headers(headers)
                .body(toDto.convert(fakture.getContent()));
    }

    @GetMapping(value = "/izlazne")
    public ResponseEntity getIzlazne(@RequestParam(value = "godina", defaultValue = "0") int godina,
                                     @RequestParam(value = "page",defaultValue = "1") int page,
                                     @RequestParam(value = "num",defaultValue = Integer.MAX_VALUE+"") int num,
                                     @RequestParam(value = "naziv",defaultValue = "") String naziv){
        if(godina==0){
            Page<Faktura> fakture = fakturaServiceInterface.findAllByVrstaFaktureAndNazivPartnera(
                    false, naziv, page,num);
            HttpHeaders headers = new HttpHeaders();
            headers.set("total",String.valueOf(fakture.getTotalPages()));
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(toDto.convert(fakture.getContent()));
        }
        Page<Faktura> fakture = fakturaServiceInterface.findAllByVrstaFaktureAndPoslovnaGodinaAndNazivPartnera(
                false, naziv,godina,page,num);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total",String.valueOf(fakture.getTotalPages()));
        return ResponseEntity.ok()
                .headers(headers)
                .body(toDto.convert(fakture.getContent()));
    }

    @GetMapping("/{id}/report")
    public ResponseEntity getReport(@PathVariable("id") long id){
        Faktura faktura = fakturaServiceInterface.findOne(id);
        if(faktura==null){
        	return new ResponseEntity(HttpStatus.NOT_FOUND);
        	}
    	
    	List<StavkaFakture> stavkeFaktureOrig = stavkaFaktureServiceInterface.findByFaktura_id(faktura.getId());
    	
    	JRBeanCollectionDataSource stavkeFakture = new JRBeanCollectionDataSource(stavkeFaktureOrig);
    	
        Map<String, Object> params  = new HashMap<>();

    	params.put("faktura", faktura);
    	params.put("stavkeFakture", stavkeFakture);
    	
    	for (StavkaFakture stavkaFakture : stavkeFaktureOrig) {
			System.out.println("id fakture: " + stavkaFakture.getId());
		}
    	
    	try {
    		
    		 InputStream is = null;
    	     String tip = null;
    	        
    		if(faktura.isVrstaFakture()) {
            	is = new FileInputStream(new File("E:\\PI projekat\\FakturisanjeV2\\FakturisanjePI\\src\\main\\resources\\ulazna-faktura-fix.jrxml"));
            	tip = "ulazna-faktura";
            } else {
                is = new FileInputStream(new File("E:\\PI projekat\\FakturisanjeV2\\FakturisanjePI\\src\\main\\resources\\izlazna-faktura-fix.jrxml"));
                tip = "izlazna-faktura";
            }
    	
            JasperDesign jasperDesign = JRXmlLoader.load(is);
            
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
    		
    		JasperPrint jp = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
    		ByteArrayInputStream bis = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
    		HttpHeaders headers = new HttpHeaders();
    		headers.add("Content-Disposition", "inline; filename="+faktura.getBrojFakture()+"-"+faktura.getPoslovnaGodina().getGodina()+".pdf");
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
    
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") long id){
        Faktura faktura = fakturaServiceInterface.findOne(id);
        if(faktura==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(toDto.convert(faktura));
    }
    
    @GetMapping("/{id}/robaCenovnika")
    public ResponseEntity getCenovnikRoba(@PathVariable("id") long id){
    	Faktura faktura = fakturaServiceInterface.findOne(id);
        if(faktura==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List<Cjenovnik> cenovnici = new ArrayList<Cjenovnik>();
        if (faktura.getPoslovniPartner().getVrstaPartnera()==1)
        	cenovnici.addAll(faktura.getPoslovniPartner().getCjenovnici());
        else
        	cenovnici.addAll(faktura.getPreduzece().getCjenovnici());
        Cjenovnik cj = cenovnici.get(cenovnici.size()-1);
        ArrayList<RobaUsluga> roba = new ArrayList();
        for (StavkaCjenovnika st : cj.getStavkeCjenovnika()) {
        	roba.add(st.getRobaUsluga());
        }
        return ResponseEntity.ok(robaUslugaToDto.convert(roba));
    }
    
    @GetMapping("/{id}/stavke")
    public ResponseEntity getStavke(@PathVariable("id") long id){

        List<StavkaFakture> stavka_fakture = stavkaFaktureServiceInterface.findByFaktura_id(id);
        return ResponseEntity.ok(stavkaFaktureToDto.convert(stavka_fakture));
    }
   
}
