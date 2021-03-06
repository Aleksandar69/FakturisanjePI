package com.aleksandar.fakturisanje.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleksandar.fakturisanje.converter.PdvDtoToPdv;
import com.aleksandar.fakturisanje.converter.PdvToPdvDto;
import com.aleksandar.fakturisanje.converter.StopaPdvToStopaPdvDto;
import com.aleksandar.fakturisanje.model.PDV;
import com.aleksandar.fakturisanje.model.StopaPDV;
import com.aleksandar.fakturisanje.service.interfaces.IPDVService;

@RestController
@RequestMapping(value = "api/pdv")
public class PdvController {

    @Autowired
    private IPDVService pdvServiceInterface;

    @Autowired
    private PdvDtoToPdv toPdv;

    @Autowired
    private PdvToPdvDto todto;
    
    @Autowired
    private StopaPdvToStopaPdvDto stopaPDVtoDto;
    
    @GetMapping
    public ResponseEntity getAll(){
    	List<PDV> pdvs = pdvServiceInterface.findAll();
    	if(pdvs != null) {
        return ResponseEntity.ok(todto.convert(pdvs));
    	}
    	else {
    		throw new RuntimeException("Pdv je null");
    	}
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity getOne(@PathVariable long id){
        PDV pdv = pdvServiceInterface.findOne(id);
        if(pdv==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(todto.convert(pdv));
    }
    
    @GetMapping(value = "/{id}/stopa")
    public ResponseEntity getStopa(@PathVariable long id){
        PDV pdv = pdvServiceInterface.findOne(id);
        if(pdv==null || pdv.getStopePdva().isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List<StopaPDV> stope = new ArrayList(pdv.getStopePdva());
        Collections.sort(stope, (o1, o2) -> (o1.getDatumVazenja().compareTo(o2.getDatumVazenja())));
        return ResponseEntity.ok(stopaPDVtoDto.convert(stope.get(stope.size()-1)));
    }
	
}
