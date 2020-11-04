package com.aleksandar.fakturisanje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleksandar.fakturisanje.converter.PoslovnaGodinaDtoToPoslovnaGodina;
import com.aleksandar.fakturisanje.converter.PoslovnaGodinatoPoslovnaGodinaDto;
import com.aleksandar.fakturisanje.model.PoslovnaGodina;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovnaGodinaService;

@RestController
@RequestMapping(value = "api/poslovne_godine")
public class PoslovnaGodinaController {

    @Autowired
    private IPoslovnaGodinaService poslovnaGodinaServiceInterface;

    @Autowired
    private PoslovnaGodinatoPoslovnaGodinaDto toDto;

    @Autowired
    private PoslovnaGodinaDtoToPoslovnaGodina toPoslovnaGodina;
    
    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(toDto.convert(poslovnaGodinaServiceInterface.findAll()));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity getOne(@PathVariable long id){
        PoslovnaGodina poslovnaGodina = poslovnaGodinaServiceInterface.findOne(id);
        if(poslovnaGodina==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(toDto.convert(poslovnaGodina));
    }
	
}
