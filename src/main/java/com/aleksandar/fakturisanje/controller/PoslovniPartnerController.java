package com.aleksandar.fakturisanje.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

import com.aleksandar.fakturisanje.converter.CjenovnikToCjenovnikDto;
import com.aleksandar.fakturisanje.converter.PoslovniPartnerDtoToPoslovniPartner;
import com.aleksandar.fakturisanje.converter.PoslovniPartnerToPoslovniPartnerDto;
import com.aleksandar.fakturisanje.dto.PoslovniPartnerDto;
import com.aleksandar.fakturisanje.model.PoslovniPartner;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovniPartnerService;

@RestController
@RequestMapping(value = "api/poslovni_partneri")
public class PoslovniPartnerController {

    @Autowired
    private IPoslovniPartnerService poslovniPartnerServiceInterface;

    @Autowired
    private PoslovniPartnerDtoToPoslovniPartner toPoslovniPartner;

    @Autowired
    private PoslovniPartnerToPoslovniPartnerDto toDto;
    
    @Autowired
    private CjenovnikToCjenovnikDto toCjenovnikDto;

    
    @GetMapping
    public ResponseEntity getAll(@RequestParam(value = "page",defaultValue = "0") int page,
                                 @RequestParam(value = "num",defaultValue = Integer.MAX_VALUE+"") int num,
                                 @RequestParam(value = "filter",defaultValue = "") String filter,
                                 @RequestParam(value = "tip", defaultValue = "3") int tip){
        List<PoslovniPartner> poslovniPartneri = poslovniPartnerServiceInterface.findAll(filter);
        tip = Integer.valueOf(tip);
        if(tip==3){
            return ResponseEntity.ok(toDto.convert(poslovniPartneri));
        }
        // ne moze drugacije
        int finalTip = tip;
        List<PoslovniPartner> poslovniPartnerFilter = poslovniPartneri.stream()
                .filter(p->p.getVrstaPartnera()== finalTip && !p.isObrisano()).collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(page, num);
        long start = pageRequest.getOffset();
        long end = (start + pageRequest.getPageSize()) > poslovniPartnerFilter.size() ? poslovniPartnerFilter.size() : (start + num);
        Page<PoslovniPartner> poslovniPartnerPage = new PageImpl<>(poslovniPartnerFilter.subList((int) start, (int) end),
                pageRequest, poslovniPartnerFilter.size());

        HttpHeaders headers = new HttpHeaders();
        headers.set("total",String.valueOf(poslovniPartnerPage.getTotalPages()));
        return ResponseEntity.ok()
                .headers(headers)
                .body(toDto.convert(poslovniPartnerPage.getContent()));
    }
    
    @PostMapping
    public ResponseEntity dodajPartnera(@Validated @RequestBody PoslovniPartnerDto dto, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        PoslovniPartner partner = poslovniPartnerServiceInterface.save(toPoslovniPartner.convert(dto));
        if(partner==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(toDto.convert(partner),HttpStatus.CREATED);
    }
	
    @GetMapping(value = "/{id}")
    public ResponseEntity getOne(@PathVariable long id){
        PoslovniPartner partner = poslovniPartnerServiceInterface.findOne(id);
        if(partner==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(toDto.convert(partner));
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity putPartner(@PathVariable long id, @Validated @RequestBody PoslovniPartnerDto dto,Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        if(dto.getId()!=id){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        PoslovniPartner partner = poslovniPartnerServiceInterface.save(toPoslovniPartner.convert(dto));
        if(partner==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(toDto.convert(partner));
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOne(@PathVariable long id){
        PoslovniPartner partner = poslovniPartnerServiceInterface.findOne(id);
        if(partner==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        poslovniPartnerServiceInterface.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
