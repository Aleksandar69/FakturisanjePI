package com.aleksandar.fakturisanje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleksandar.fakturisanje.converter.StavkaFaktureDtoToStavkaFakture;
import com.aleksandar.fakturisanje.converter.StavkaFaktureToStavkaFaktureDto;
import com.aleksandar.fakturisanje.dto.StavkaFaktureDto;
import com.aleksandar.fakturisanje.model.StavkaFakture;
import com.aleksandar.fakturisanje.service.interfaces.IStavkaFaktureService;

@RestController
@RequestMapping("/api/stavkafakture")
public class StavkaFaktureController {
	
	@Autowired
	IStavkaFaktureService stavkaFaktureService;
	
	@Autowired
	StavkaFaktureToStavkaFaktureDto toDtoConv;
	
	@Autowired
	StavkaFaktureDtoToStavkaFakture fromDtoConv;
	
	@PostMapping
	public ResponseEntity dodajStavkuFakture(@Validated @RequestBody StavkaFaktureDto dto, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        StavkaFakture stavkaFakture = stavkaFaktureService.save(fromDtoConv.convert(dto));
        if(stavkaFakture != null){
        	return new ResponseEntity(toDtoConv.convert(stavkaFakture),HttpStatus.CREATED);
        }else {
        	return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
