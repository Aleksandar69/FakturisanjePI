package com.aleksandar.fakturisanje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleksandar.fakturisanje.converter.StavkaOtpremniceDtoToStavkaOtpremnice;
import com.aleksandar.fakturisanje.converter.StavkaOtpremniceToStavkaOtpremniceDto;
import com.aleksandar.fakturisanje.dto.StavkaOtpremniceDto;
import com.aleksandar.fakturisanje.model.StavkaOtpremnice;
import com.aleksandar.fakturisanje.service.interfaces.IStavkaOtpremniceService;

@RestController
@RequestMapping("/api/stavkaOtpremnice")
public class StavkaOtpremniceController {

	
	@Autowired
    private IStavkaOtpremniceService stavkaOtpremniceService;

    @Autowired
    private StavkaOtpremniceToStavkaOtpremniceDto stavkaOtpremniceToStavkaOtpremniceDto;

    @Autowired
    private StavkaOtpremniceDtoToStavkaOtpremnice stavkaOtpremniceDtoToStavkaOtpremnice;
    
    @GetMapping
    public ResponseEntity getAll() {

        List<StavkaOtpremnice> stavkeOtpremnice = stavkaOtpremniceService.findAll();
        List<StavkaOtpremniceDto> stavkeOtpremniceDto = stavkaOtpremniceToStavkaOtpremniceDto.convert(stavkeOtpremnice);
        return new ResponseEntity<>(stavkeOtpremniceDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") long id) {

        StavkaOtpremnice stavkaOtpremnice = stavkaOtpremniceService.findOne(id);
        if (stavkaOtpremnice == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        StavkaOtpremniceDto stavkaOtpremniceDto = stavkaOtpremniceToStavkaOtpremniceDto.convert(stavkaOtpremnice);
        return new ResponseEntity<>(stavkaOtpremniceDto, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOne(@PathVariable long id){

        StavkaOtpremnice stavka = stavkaOtpremniceService.findOne(id);
        if(stavka==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        stavkaOtpremniceService.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @PostMapping
    public ResponseEntity postOne(@Validated @RequestBody StavkaOtpremniceDto dto, Errors errors){

        if(errors.hasErrors()){
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        StavkaOtpremnice stavkaOtpremnice = stavkaOtpremniceDtoToStavkaOtpremnice.convert(dto);
        StavkaOtpremnice dbStavkaOtpremnice = stavkaOtpremniceService.save(stavkaOtpremnice);

        if(dbStavkaOtpremnice == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        StavkaOtpremniceDto stavkaOtpremniceDto = stavkaOtpremniceToStavkaOtpremniceDto.convert(dbStavkaOtpremnice);
        return new ResponseEntity<>(stavkaOtpremniceDto, HttpStatus.CREATED);
    }



   
}
