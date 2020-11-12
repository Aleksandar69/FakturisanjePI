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

import com.aleksandar.fakturisanje.converter.StavkaNarudzbeniceDtoToStavkaNarudzbenice;
import com.aleksandar.fakturisanje.converter.StavkaNarudzbeniceToStavkaNarudzbeniceDto;
import com.aleksandar.fakturisanje.dto.StavkaNarudzbeniceDto;
import com.aleksandar.fakturisanje.model.StavkaNarudzbenice;
import com.aleksandar.fakturisanje.service.interfaces.IStavkaNarudzbeniceService;

@RestController
@RequestMapping("/api/stavkanarudzbenice")
public class StavkaNarudzbeniceController {

    @Autowired
    private IStavkaNarudzbeniceService stavkaNarudzbeniceService;

    @Autowired
    private StavkaNarudzbeniceDtoToStavkaNarudzbenice stavkaNarudzbeniceDtoToStavkaNarudzbenice;

    @Autowired
    private StavkaNarudzbeniceToStavkaNarudzbeniceDto stavkaNarudzbeniceToStavkaNarudzbeniceDto;


    @GetMapping
    public ResponseEntity getAll() {

        List<StavkaNarudzbenice> stavkaNarudzbenice = stavkaNarudzbeniceService.findAll();
        List<StavkaNarudzbeniceDto> stavkeNarudzbeniceDto = stavkaNarudzbeniceToStavkaNarudzbeniceDto.convert(stavkaNarudzbenice);
        return new ResponseEntity<>(stavkeNarudzbeniceDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") long id) {

        StavkaNarudzbenice stavkaNarudzbenice = stavkaNarudzbeniceService.findOne(id);
        if (stavkaNarudzbenice == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stavkaNarudzbenice, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteStavkaNarudzbenice(@PathVariable long id){

    	System.out.println("stavka id: " + id);
        StavkaNarudzbenice stavka = stavkaNarudzbeniceService.findOne(id);
        if(stavka==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        stavkaNarudzbeniceService.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @PostMapping
    public ResponseEntity addStavkaNarudzbenice(@Validated @RequestBody StavkaNarudzbeniceDto dto, Errors errors){

    	
        if(errors.hasErrors()){
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        StavkaNarudzbenice stavkaNarudzbenice = stavkaNarudzbeniceDtoToStavkaNarudzbenice.convert(dto);
        StavkaNarudzbenice dbStavkaNarudzbenice = stavkaNarudzbeniceService.save(stavkaNarudzbenice);

        if (dbStavkaNarudzbenice == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        StavkaNarudzbeniceDto stavkaNarudzbeniceDto = stavkaNarudzbeniceToStavkaNarudzbeniceDto.convert(dbStavkaNarudzbenice);
        return new ResponseEntity<>(stavkaNarudzbeniceDto, HttpStatus.CREATED);
    }


}
