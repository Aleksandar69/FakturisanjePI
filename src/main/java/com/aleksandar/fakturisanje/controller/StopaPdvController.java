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

import com.aleksandar.fakturisanje.converter.StopaPdvDtoToStopaPdv;
import com.aleksandar.fakturisanje.converter.StopaPdvToStopaPdvDto;
import com.aleksandar.fakturisanje.dto.StopaPdvDto;
import com.aleksandar.fakturisanje.model.StopaPDV;
import com.aleksandar.fakturisanje.service.interfaces.IStopaPDVaService;

@RestController
@RequestMapping(value = "api/stopa_pdv")
public class StopaPdvController {

    @Autowired
    private IStopaPDVaService stopaPdvService;

    @Autowired
    private StopaPdvDtoToStopaPdv toStopaPDV;

    @Autowired
    private StopaPdvToStopaPdvDto toDto;
    
    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(toDto.convert(stopaPdvService.findAll()));
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity getOne(@PathVariable long id){
    	StopaPDV stopa = stopaPdvService.findOne(id);
        if(stopa==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(toDto.convert(stopa));
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity editStopaPDV(@PathVariable long id, @Validated @RequestBody StopaPdvDto dto,Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        if(dto.getId()!=id){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        StopaPDV stopaPdv = toStopaPDV.convert(dto);
        stopaPdv.setId(id);
        StopaPDV stopa = stopaPdvService.save(stopaPdv);
        if(stopa==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(toDto.convert(stopa));
    }
    
    @PostMapping
    public ResponseEntity addStopaPDV(@Validated @RequestBody StopaPdvDto dto, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        StopaPDV stopa = stopaPdvService.save(toStopaPDV.convert(dto));
        if( stopa==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(toDto.convert( stopa),HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteStopaPDV(@PathVariable long id){
        StopaPDV stopa = stopaPdvService.findOne(id);
        if(stopa==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        stopaPdvService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
