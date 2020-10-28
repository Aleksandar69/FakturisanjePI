package com.aleksandar.fakturisanje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aleksandar.fakturisanje.converter.MjestoDtoToMjesto;
import com.aleksandar.fakturisanje.converter.MjestoToMjestoDto;
import com.aleksandar.fakturisanje.model.Mjesto;
import com.aleksandar.fakturisanje.service.interfaces.IMjestoService;

@RestController
@RequestMapping("api/mjesto")
public class MjestoController {

	@Autowired
	IMjestoService mjestoServiceInterface;
	
	@Autowired
	MjestoDtoToMjesto toMjesto;
	
	@Autowired
	MjestoToMjestoDto toDto;
	
    @GetMapping
    public ResponseEntity getAll(@RequestParam(value = "page",defaultValue = "0") int page,
                                 @RequestParam(value = "num",defaultValue = Integer.MAX_VALUE+"") int num,
                                 @RequestParam(value = "naziv",defaultValue = "") String naziv) {

        Page<Mjesto> mesta = mjestoServiceInterface.findAll(naziv,page,num);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total",String.valueOf(mesta.getTotalPages()));
        return ResponseEntity.ok()
                .headers(headers)
                .body(toDto.convert(mesta.getContent()));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") long id){
        Mjesto mesto = mjestoServiceInterface.findOne(id);
        if(mesto==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(toDto.convert(mesto));
    }
    

}
