package com.aleksandar.fakturisanje.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleksandar.fakturisanje.converter.CjenovnikToCjenovnikDto;
import com.aleksandar.fakturisanje.converter.FakturaToFakturaDto;
import com.aleksandar.fakturisanje.converter.GrupaRobeToGrupaRobeDto;
import com.aleksandar.fakturisanje.converter.PoslovniPartnerToPoslovniPartnerDto;
import com.aleksandar.fakturisanje.converter.PreduzeceDtoToPreduzece;
import com.aleksandar.fakturisanje.converter.PreduzeceToPreduzeceDto;
import com.aleksandar.fakturisanje.dto.PreduzeceDto;
import com.aleksandar.fakturisanje.model.Cjenovnik;
import com.aleksandar.fakturisanje.model.Preduzece;
import com.aleksandar.fakturisanje.service.interfaces.IGrupaRobeService;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovniPartnerService;
import com.aleksandar.fakturisanje.service.interfaces.IPreduzeceService;

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

}
