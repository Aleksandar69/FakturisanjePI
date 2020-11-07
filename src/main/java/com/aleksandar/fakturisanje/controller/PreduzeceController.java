package com.aleksandar.fakturisanje.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aleksandar.fakturisanje.converter.CjenovnikToCjenovnikDto;
import com.aleksandar.fakturisanje.converter.FakturaToFakturaDto;
import com.aleksandar.fakturisanje.converter.GrupaRobeToGrupaRobeDto;
import com.aleksandar.fakturisanje.converter.PoslovniPartnerToPoslovniPartnerDto;
import com.aleksandar.fakturisanje.converter.PreduzeceDtoToPreduzece;
import com.aleksandar.fakturisanje.converter.PreduzeceToPreduzeceDto;
import com.aleksandar.fakturisanje.dto.PreduzeceDto;
import com.aleksandar.fakturisanje.model.Cjenovnik;
import com.aleksandar.fakturisanje.model.Faktura;
import com.aleksandar.fakturisanje.model.Preduzece;
import com.aleksandar.fakturisanje.service.interfaces.IGrupaRobeService;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovniPartnerService;
import com.aleksandar.fakturisanje.service.interfaces.IPreduzeceService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@RestController
@RequestMapping("/api/preduzece")
public class PreduzeceController {
	
    @Autowired
    private IPoslovniPartnerService poslovniPartnerServiceInterface;

    @Autowired
    private IPreduzeceService preduzeceService;

    @Autowired
    private IGrupaRobeService grupaRobeService;
    
    @Autowired
    private PreduzeceDtoToPreduzece toPreduzece;

    @Autowired
    private PreduzeceToPreduzeceDto toDto;

    @Autowired
    private PoslovniPartnerToPoslovniPartnerDto poslovniPartnerToDto;

    @Autowired
    private FakturaToFakturaDto toFakturaDto;
    
    @Autowired
    private GrupaRobeToGrupaRobeDto toGrupaRobeDto;
    
    @Autowired
    private CjenovnikToCjenovnikDto toCjenovnikDto;
    
    @GetMapping
    public ResponseEntity getAll() {
    	return ResponseEntity.ok(toDto.convert(preduzeceService.findAll()));
    }
    
    @GetMapping(value= "/{id}")
    public ResponseEntity getOne(@PathVariable long id) {
    	Preduzece preduzece = preduzeceService.findOne(id);
    	if(preduzece == null) {
    		return new ResponseEntity(HttpStatus.NOT_FOUND);
    	}
    	else {
    		return ResponseEntity.ok(toDto.convert(preduzece));
    	}	
    }
    
    @GetMapping("/{id}/partneri")
    public ResponseEntity getPartneri(@PathVariable("id") long id){
        return ResponseEntity.ok(poslovniPartnerToDto.convert(poslovniPartnerServiceInterface.findByPreduzece_id(id)));
    }
    
    @GetMapping("/{id}/cjenovnici")
    public ResponseEntity getCjenovnici(@PathVariable("id") long id) {
    	Preduzece preduzece = preduzeceService.findOne(id);
    	Set<Cjenovnik> c = preduzece.getCjenovnici();
		return ResponseEntity.ok(toCjenovnikDto.convert(c.stream().filter(x -> !x.isDeleted()).collect(Collectors.toList())));
    }
    
    @PutMapping(value= "/{id}")
    public ResponseEntity editPreduzece(@PathVariable long id, @Validated @RequestBody PreduzeceDto dto, Errors errors) {
    	
    	if(errors.hasErrors()) {
    		return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
    	}
    	if(dto.getId() != id) {
    		return new ResponseEntity(HttpStatus.NOT_FOUND);
    	}
    	Preduzece preduzece = preduzeceService.save(toPreduzece.convert(dto));
    	if(preduzece == null) {
    		return new ResponseEntity(HttpStatus.BAD_REQUEST);
    	}
    	return ResponseEntity.ok(toDto.convert(preduzece));
    	
    }
    
    @GetMapping("{id}/reports/{vrsta}")
    public ResponseEntity getReports(@RequestParam("godina") int godina, @PathVariable("id") long id, @PathVariable("vrsta") String vrsta) throws JRException, FileNotFoundException{
    	boolean tipFakture = false;
    	if(vrsta.equalsIgnoreCase("ulazne")) {
    	tipFakture = true;
    	}
    	
    	Preduzece preduzece = preduzeceService.findOne(id);
        List<Faktura> faktureFinal = new ArrayList<Faktura>();
        List<Faktura> fakture = preduzeceService.findAllByPreduzeceAndVrstaFaktureAndPlaceno(tipFakture, id, true);

        if(fakture==null){
        	return new ResponseEntity(HttpStatus.NOT_FOUND);
        	}
    	
        if(fakture.isEmpty()){
        	return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
          
        Map<String, Object> params  = new HashMap<String, Object>();
        for(Faktura i : fakture){
        	if(i.getPoslovnaGodina().getGodina() == godina){
            	faktureFinal.add(i);      		
        	}        	
        }
        params.put("godina", godina);
        params.put("fakture", faktureFinal);
        params.put("preduzece", preduzece);     
        
       
        InputStream is = null;
        String tip = null;
        if(tipFakture) {
        	is = new FileInputStream(new File("E:\\PI projekat\\FakturisanjeV2\\FakturisanjePI\\src\\main\\resources\\ulazne-fakture.jrxml"));
        	tip = "ulazne-fakture";
        } else {
            is = new FileInputStream(new File("E:\\PI projekat\\FakturisanjeV2\\FakturisanjePI\\src\\main\\resources\\izlazne-fakture.jrxml"));
            tip = "izlazne-fakture";
        }
	
        JasperDesign jasperDesign = JRXmlLoader.load(is);
        
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
         
        try{ 	
    		JasperPrint jp = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
    		ByteArrayInputStream bis = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
    		HttpHeaders headers = new HttpHeaders();
    		headers.add("Content-Disposition", "inline; filename="+preduzece.getNaziv()+"-"+tip+".pdf");
    		return ResponseEntity
    	       		.ok()
    	       		.headers(headers)
    	       		.contentType(MediaType.APPLICATION_PDF)
    	       		.body(new InputStreamResource(bis));
        }catch(Exception ex){
        	ex.printStackTrace();

        }
    	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    

}
