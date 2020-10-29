package com.aleksandar.fakturisanje.controller;

import java.util.Optional;

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

import com.aleksandar.fakturisanje.converter.CjenovnikDtoToCjenovnik;
import com.aleksandar.fakturisanje.converter.CjenovnikToCjenovnikDto;
import com.aleksandar.fakturisanje.converter.PoslovniPartnerToPoslovniPartnerDto;
import com.aleksandar.fakturisanje.converter.StavkaCjenovnikaToStavkaCjenovnikaDto;
import com.aleksandar.fakturisanje.dto.CjenovnikDto;
import com.aleksandar.fakturisanje.model.Cjenovnik;
import com.aleksandar.fakturisanje.model.PoslovniPartner;
import com.aleksandar.fakturisanje.model.Preduzece;
import com.aleksandar.fakturisanje.model.StavkaCjenovnika;
import com.aleksandar.fakturisanje.repo.PoslovniPartnerRepository;
import com.aleksandar.fakturisanje.repo.PreduzeceRepository;
import com.aleksandar.fakturisanje.service.interfaces.ICjenovnikService;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovniPartnerService;

@RestController
@RequestMapping("/api/cjenovnik")
public class CjenovnikController {

	@Autowired
	PreduzeceRepository preduzeceRepository;
	
	@Autowired
	PoslovniPartnerRepository poslovniPartnerRepository;
	
	@Autowired
	IPoslovniPartnerService poslovniPartenerServiceInterface;
	
	@Autowired
	PoslovniPartnerToPoslovniPartnerDto poslovniPartnerToDto;
	
	@Autowired
	ICjenovnikService cjenovnikServiceInterface;
	
	@Autowired
	CjenovnikToCjenovnikDto cjenovnikToDto;
	
	@Autowired
	CjenovnikDtoToCjenovnik cjenovnikFromDto;
	
	@Autowired
	StavkaCjenovnikaToStavkaCjenovnikaDto stavkaToDto;
	
	@GetMapping
	public ResponseEntity getAll(@RequestParam(value="page", defaultValue= "0") int page,
								@RequestParam(value="num", defaultValue=Integer.MAX_VALUE+"") int num) {
		Page<Cjenovnik> cjenovnici = cjenovnikServiceInterface.findAll(page, num);
		HttpHeaders header = new HttpHeaders();
		header.set("total", String.valueOf(cjenovnici.getTotalPages()));
		return ResponseEntity.ok().headers(header).body(cjenovnikToDto.convert(cjenovnici.getContent()));
	}
	
	@PostMapping
	public ResponseEntity postOne(@Validated @RequestBody CjenovnikDto dto, @RequestParam("preduzece") boolean preduzece, @RequestParam("id") long id, Errors errors) {
		if(errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
		}
		
		Cjenovnik cjenovnik = cjenovnikServiceInterface.save(cjenovnikFromDto.convert(dto));
		if(cjenovnik != null) {
			if(preduzece == true) {
				Optional<Preduzece> pred = preduzeceRepository.findById(id);
				if(pred.isPresent()) {
				Preduzece ppreduzece = pred.get();
				ppreduzece.getCjenovnici().add(cjenovnik);
				preduzeceRepository.save(ppreduzece);
				}
			}
			else {
				Optional<PoslovniPartner> partner = poslovniPartnerRepository.findById(id);
				if(partner.isPresent()) {
				PoslovniPartner ppartner = partner.get();
				ppartner.getCjenovnici().add(cjenovnik);
				poslovniPartnerRepository.save(ppartner);
				}
				}
			return new ResponseEntity(cjenovnikToDto.convert(cjenovnik),HttpStatus.CREATED);
		}else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		Cjenovnik cenovnik = cjenovnikServiceInterface.findOne(id);
		if (cenovnik != null) {
			cjenovnikServiceInterface.delete(cenovnik.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Cjenovnik cenovnik = cjenovnikServiceInterface.findOne(id);
		if (cenovnik!=null) {
			return new ResponseEntity(cjenovnikToDto.convert(cenovnik),HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody CjenovnikDto dto, Errors errors) {
		
		if(errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
		}
		if(dto.getId()!=id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		Cjenovnik cjenovnik = cjenovnikServiceInterface.save(cjenovnikFromDto.convert(dto));
		if(cjenovnik!=null) {
			return new ResponseEntity(cjenovnikToDto.convert(cjenovnik),HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{id}/stavke_cjenovnika")
	public ResponseEntity getStavkecjenovnika(@PathVariable("id") long id,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "num", defaultValue = Integer.MAX_VALUE+"") int num,
			@RequestParam(value = "naziv", defaultValue = "") String naziv) {
		
		Cjenovnik cjenovnik = cjenovnikServiceInterface.findOne(id);
		if (cjenovnik!=null) {
			Page<StavkaCjenovnika> stavkeCjenovnika = cjenovnikServiceInterface.findAllByCjenovnikId(cjenovnik.getId(), naziv, page, num);
			HttpHeaders headers = new HttpHeaders();
			headers.set("total", String.valueOf(stavkeCjenovnika.getTotalPages()));
			return ResponseEntity.ok().headers(headers).body(stavkaToDto.convert(stavkeCjenovnika.getContent()));
			
		}
		else {
			
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}

}
