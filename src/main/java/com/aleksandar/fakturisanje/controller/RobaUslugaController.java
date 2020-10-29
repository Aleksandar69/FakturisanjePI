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

import com.aleksandar.fakturisanje.converter.PdvToPdvDto;
import com.aleksandar.fakturisanje.converter.RobaUslugaDtoToRobaUsluga;
import com.aleksandar.fakturisanje.converter.RobaUslugaToRobaUslugaDto;
import com.aleksandar.fakturisanje.converter.StavkaCjenovnikaToStavkaCjenovnikaDto;
import com.aleksandar.fakturisanje.model.RobaUsluga;
import com.aleksandar.fakturisanje.service.interfaces.IRobaUslugaService;
import com.aleksandar.fakturisanje.service.interfaces.IStavkaCjenovnikaService;

@RestController
@RequestMapping("/api/robausluga")	
public class RobaUslugaController {

	
	@Autowired
	IRobaUslugaService robaUslugaService;
	
	@Autowired
	IStavkaCjenovnikaService stavkaCjenovnikaService;
	
	@Autowired
	StavkaCjenovnikaToStavkaCjenovnikaDto stavkaCjenovnikaToDtoConv;
	
	@Autowired
	RobaUslugaToRobaUslugaDto toDtoConv;
	
	@Autowired
	RobaUslugaDtoToRobaUsluga fromDtoConv;
	
	@Autowired
	PdvToPdvDto pdvToDto;
	
	@GetMapping
	public ResponseEntity getAll(@RequestParam(value="grupa", defaultValue="0") Long grupa,
								@RequestParam(value="page", defaultValue="0") int page,
								@RequestParam (value="num", defaultValue= Integer.MAX_VALUE+"") int num,
								@RequestParam(value = "naziv", defaultValue="") String naziv) {
		
		if(grupa == 0) {
			Page<RobaUsluga> robeUsluge = robaUslugaService.findAll(naziv, page, num);
			HttpHeaders headers = new HttpHeaders();
			headers.set("total", String.valueOf( robeUsluge.getTotalPages()));
			return ResponseEntity.ok().headers(headers).body(toDtoConv.convert(robeUsluge.getContent()));
			
		}
		else {
			Page<RobaUsluga> robeUsluge = robaUslugaService.findAllByGrupaRobe_id(grupa,naziv,page,num);
			HttpHeaders headers = new HttpHeaders();
			headers.set("total", String.valueOf(robeUsluge.getTotalPages()));
			return ResponseEntity.ok()
					.headers(headers)
					.body(toDtoConv.convert(robeUsluge.getContent()));
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		RobaUsluga robaUsluga = robaUslugaService.findOne(id);
		if (robaUsluga!=null) {
			return new ResponseEntity(toDtoConv.convert(robaUsluga),HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
    }
}
