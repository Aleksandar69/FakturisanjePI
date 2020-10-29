package com.aleksandar.fakturisanje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleksandar.fakturisanje.converter.StavkaCjenovnikaDtoToStavkaCjenovnika;
import com.aleksandar.fakturisanje.converter.StavkaCjenovnikaToStavkaCjenovnikaDto;
import com.aleksandar.fakturisanje.dto.StavkaCjenovnikaDto;
import com.aleksandar.fakturisanje.model.StavkaCjenovnika;
import com.aleksandar.fakturisanje.service.interfaces.IStavkaCjenovnikaService;

@RestController
@RequestMapping("/api/stavka_cjenovnika")
public class StavkaCjenovnikaController {

  @Autowired
  private IStavkaCjenovnikaService stavkaCjenovnikaService;

  @Autowired
  private StavkaCjenovnikaDtoToStavkaCjenovnika toStavkaCjenovnika;

  @Autowired
  private StavkaCjenovnikaToStavkaCjenovnikaDto toDto;
	  
	  
    @GetMapping(value = "/{id}")
    public ResponseEntity getOne(@PathVariable long id){
    	StavkaCjenovnika stavka = stavkaCjenovnikaService.findOne(id);
        if(stavka==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(toDto.convert(stavka));
    }
    
    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(toDto.convert(stavkaCjenovnikaService.findAll()));
    }
	    
	  
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOne(@PathVariable long id){
        StavkaCjenovnika stavka = stavkaCjenovnikaService.findOne(id);
        if(stavka==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        stavkaCjenovnikaService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @PostMapping
    public ResponseEntity addStavkaCenovnika(@Validated @RequestBody StavkaCjenovnikaDto dto, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        System.out.println("CIJENA DTO: "+ dto.getCijena());
        StavkaCjenovnika stavka = stavkaCjenovnikaService.save(toStavkaCjenovnika.convert(dto));
        System.out.println(stavka.getId());
        System.out.println("KONVERTOVANO CIJENA: " + stavka.getCijena());
		/*
		 * System.out.println(stavka.getCjenovnik().getId());
		 * System.out.println(stavka.getRobaUsluga().getId());
		 */
        
        if(stavka==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(toDto.convert(stavka),HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity editStavkaCenovnika(@PathVariable long id, @Validated @RequestBody StavkaCjenovnikaDto dto,Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        if(dto.getId()!=id){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        StavkaCjenovnika stavka = toStavkaCjenovnika.convert(dto);
        stavka.setId(id);
        
        StavkaCjenovnika saved = stavkaCjenovnikaService.save(stavka);
        if(saved==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(toDto.convert(stavka));
    }

	
}
