package com.aleksandar.fakturisanje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aleksandar.fakturisanje.converter.GrupaRobeDtoToGrupaRobe;
import com.aleksandar.fakturisanje.converter.GrupaRobeToGrupaRobeDto;
import com.aleksandar.fakturisanje.converter.RobaUslugaToRobaUslugaDto;
import com.aleksandar.fakturisanje.dto.GrupaRobeDto;
import com.aleksandar.fakturisanje.model.GrupaRobe;
import com.aleksandar.fakturisanje.service.interfaces.IGrupaRobeService;
import com.aleksandar.fakturisanje.service.interfaces.IRobaUslugaService;

@RestController
@RequestMapping("api/grupa_robe")
public class GrupaRobeController {

	@Autowired
    private IGrupaRobeService grupaRobeServiceInterface;

    @Autowired
    private GrupaRobeDtoToGrupaRobe toGrupaRobe;

    @Autowired
    private GrupaRobeToGrupaRobeDto toDto;

    @Autowired
    private IRobaUslugaService robaUslugaService;
    
    @Autowired
    private RobaUslugaToRobaUslugaDto toRobaUslugaDto;
    
    @GetMapping
    public ResponseEntity getAll(@RequestParam(value = "page",defaultValue = "0") int page,
                                 @RequestParam(value = "num",defaultValue = Integer.MAX_VALUE+"") int num,
                                 @RequestParam(value = "naziv",defaultValue = "") String naziv){
        Page<GrupaRobe> grupaRobePage = grupaRobeServiceInterface.finadAll(naziv,page,num);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total",String.valueOf(grupaRobePage.getTotalPages()));
        return ResponseEntity.ok()
                .headers(headers)
                .body(toDto.convert(grupaRobePage.getContent()));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") long id){
        GrupaRobe grupaRobe = grupaRobeServiceInterface.findOne(id);
        if(grupaRobe == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(toDto.convert(grupaRobe));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity editGrupaRobe(@PathVariable("id") long id,@Validated @RequestBody GrupaRobeDto dto, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(dto.getId() !=id){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        GrupaRobe grupaRobe = grupaRobeServiceInterface.save(toGrupaRobe.convert(dto));
        if(grupaRobe==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(toDto.convert(grupaRobe));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteGrupaRobe(@PathVariable("id") long id){
        GrupaRobe grupaRobe = grupaRobeServiceInterface.findOne(id);
        if(grupaRobe==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        grupaRobeServiceInterface.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @PostMapping
    public ResponseEntity addGrupaRobe(@Validated @RequestBody GrupaRobeDto dto, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        GrupaRobe grupaRobe = grupaRobeServiceInterface.save(toGrupaRobe.convert(dto));
        if(grupaRobe==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(toDto.convert(grupaRobe),HttpStatus.CREATED);
    }
	
}
