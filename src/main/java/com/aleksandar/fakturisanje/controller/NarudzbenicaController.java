package com.aleksandar.fakturisanje.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aleksandar.fakturisanje.converter.NarudzbenicaDtoToNarudzbenica;
import com.aleksandar.fakturisanje.converter.NarudzbenicaToNarudzbenicaDto;
import com.aleksandar.fakturisanje.converter.RobaUslugaToRobaUslugaDto;
import com.aleksandar.fakturisanje.converter.StavkaNarudzbeniceToStavkaNarudzbeniceDto;
import com.aleksandar.fakturisanje.dto.NarudzbenicaDto;
import com.aleksandar.fakturisanje.dto.StavkaNarudzbeniceDto;
import com.aleksandar.fakturisanje.model.Cjenovnik;
import com.aleksandar.fakturisanje.model.Narudzbenica;
import com.aleksandar.fakturisanje.model.PoslovnaGodina;
import com.aleksandar.fakturisanje.model.RobaUsluga;
import com.aleksandar.fakturisanje.model.StavkaCjenovnika;
import com.aleksandar.fakturisanje.model.StavkaNarudzbenice;
import com.aleksandar.fakturisanje.service.interfaces.INarudzbenicaService;
import com.aleksandar.fakturisanje.service.interfaces.IOtpremnicaService;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovnaGodinaService;
import com.aleksandar.fakturisanje.service.interfaces.IStavkaNarudzbeniceService;

@RestController
@RequestMapping("api/narudzbenice")
public class NarudzbenicaController {

	@Autowired
	private INarudzbenicaService narudzbenicaService;

	@Autowired
	private IStavkaNarudzbeniceService stavkaNarudzbeniceService;

	@Autowired
	private IPoslovnaGodinaService poslovnaGodinaService;

	@Autowired
	private IOtpremnicaService otpremnicaService;

	@Autowired
	private RobaUslugaToRobaUslugaDto robaUslugaToDto;

	@Autowired
	private NarudzbenicaDtoToNarudzbenica narudzbenicaDtoToNarudzbenica;

	@Autowired
	private NarudzbenicaToNarudzbenicaDto narudzbenicaToNarudzbenicaDto;

	@Autowired
	private StavkaNarudzbeniceToStavkaNarudzbeniceDto stavkaNarudzbeniceToStavkaNarudzbeniceDto;

	@GetMapping
	public ResponseEntity getAll(@RequestParam(value = "godina", defaultValue = "0") int godina,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "num", defaultValue = Integer.MAX_VALUE + "") int num,
			@RequestParam(value = "naziv", defaultValue = "") String naziv) {

		Page<Narudzbenica> narudzbenice;

		if (godina == 0) {
			narudzbenice = narudzbenicaService.findAllByNazivPartnera(naziv, page, num);
		} else {
			narudzbenice = narudzbenicaService.findAllByPoslovnaGodinaAndNazivPartnera(naziv, godina, page, num);
		}

		List<NarudzbenicaDto> narudzbeniceDto = narudzbenicaToNarudzbenicaDto.convert(narudzbenice.getContent());

		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(narudzbenice.getTotalPages()));
		return ResponseEntity.ok().headers(headers).body(narudzbeniceDto);
	}

	@GetMapping(value = "/ulazne")
	public ResponseEntity getAllUlazne(@RequestParam(value = "godina", defaultValue = "0") int godina,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "num", defaultValue = Integer.MAX_VALUE + "") int num,
			@RequestParam(value = "naziv", defaultValue = "") String naziv) {

		Page<Narudzbenica> narudzbenice;

		if (godina == 0) {
			// narudzbenice = narudzbenicaService.findAllByNazivPartnera(naziv, page, num);
			narudzbenice = narudzbenicaService.findAllByTipAndNazivParntera(true, naziv, page, num);
		} else {
			// narudzbenice =
			// narudzbenicaService.findAllByPoslovnaGodinaAndNazivPartnera(naziv, godina,
			// page, num);
			narudzbenice = narudzbenicaService.findAllByTipAndNazivParnteraAndGodina(true, naziv, page, num, godina);
		}

		List<NarudzbenicaDto> narudzbeniceDto = narudzbenicaToNarudzbenicaDto.convert(narudzbenice.getContent());

		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(narudzbenice.getTotalPages()));
		return ResponseEntity.ok().headers(headers).body(narudzbeniceDto);
	}

	@GetMapping(value = "/izlazne")
	public ResponseEntity getAllIzlazne(@RequestParam(value = "godina", defaultValue = "0") int godina,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "num", defaultValue = Integer.MAX_VALUE + "") int num,
			@RequestParam(value = "naziv", defaultValue = "") String naziv) {

		Page<Narudzbenica> narudzbenice;

		if (godina == 0) {
			// narudzbenice = narudzbenicaService.findAllByNazivPartnera(naziv, page, num);
			narudzbenice = narudzbenicaService.findAllByTipAndNazivParntera(false, naziv, page, num);
		} else {
			// narudzbenice =
			// narudzbenicaService.findAllByPoslovnaGodinaAndNazivPartnera(naziv, godina,
			// page, num);
			narudzbenice = narudzbenicaService.findAllByTipAndNazivParnteraAndGodina(false, naziv, page, num, godina);
		}

		List<NarudzbenicaDto> narudzbeniceDto = narudzbenicaToNarudzbenicaDto.convert(narudzbenice.getContent());

		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(narudzbenice.getTotalPages()));
		return ResponseEntity.ok().headers(headers).body(narudzbeniceDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {

		Narudzbenica narudzbenica = narudzbenicaService.findOne(id);
		if (narudzbenica == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		NarudzbenicaDto narudzbenicaDto = narudzbenicaToNarudzbenicaDto.convert(narudzbenica);
		return new ResponseEntity<>(narudzbenicaDto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity addNarudzbenica(@Validated @RequestBody NarudzbenicaDto dto, Errors errors) {

		if (errors.hasErrors()) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

		PoslovnaGodina poslednjaPoslovnaGodina = poslovnaGodinaService.findPoslovnaGodinaIsNotObrisanoIsNotZakljucana();
		Narudzbenica narudzbenica = narudzbenicaDtoToNarudzbenica.convert(dto);

		narudzbenica.setBrojNarudzbenice(poslednjaPoslovnaGodina.getNarudzbenice().size() + 1);
		narudzbenica.setDatumNarudzbenice(new Date());
		narudzbenica.setTipNarudzbenice(narudzbenica.getPoslovniPartner().getVrstaPartnera() == 0);
		narudzbenica.setPoslovnaGodina(poslednjaPoslovnaGodina);
		narudzbenica.setObrisano(false);

		Narudzbenica dbNarudzbenica = narudzbenicaService.save(narudzbenica);
		NarudzbenicaDto narudzbenicaDto = narudzbenicaToNarudzbenicaDto.convert(dbNarudzbenica);

		return new ResponseEntity<>(narudzbenicaDto, HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}/napraviOtpremnicu")
	public ResponseEntity napraviOtpremnicuOdNarudzbenice(@PathVariable("id") long id) {

		Narudzbenica narudzbenica = narudzbenicaService.findOne(id);
		otpremnicaService.napraviOtpremnicuOdNarudzbenice(narudzbenica);

		return new ResponseEntity(HttpStatus.CREATED);
	}

	@PostMapping("/{id}/napraviFakturu")
	public ResponseEntity napraviFakturuOdNarudzbenice(@PathVariable("id") long id) {
		PoslovnaGodina poslovnaGodina = poslovnaGodinaService.findPoslovnaGodinaIsNotObrisanoIsNotZakljucana();
		int poslednjaPoslovnjaGodina = poslovnaGodina.getFakture().size();

		Narudzbenica narudzbenica = narudzbenicaService.findOne(id);
		narudzbenicaService.napraviFakturuOdNarudzbenice(narudzbenica, poslednjaPoslovnjaGodina);

		return new ResponseEntity(HttpStatus.CREATED);
	}

	@GetMapping("/{id}/stavkeNarudzbenice")
	public ResponseEntity getStavke(@PathVariable("id") long id) {

		List<StavkaNarudzbenice> stavkeNarudzbenice = stavkaNarudzbeniceService.pronadjiStavkeNarudzbenice(id);
		
		for (StavkaNarudzbenice stavkaNarudzbenice : stavkeNarudzbenice) {
			System.out.println(stavkaNarudzbenice.getOpis());
		}
		
		List<StavkaNarudzbeniceDto> stavkeNarudzbeniceDto = stavkaNarudzbeniceToStavkaNarudzbeniceDto
				.convert(stavkeNarudzbenice);
		return new ResponseEntity<>(stavkeNarudzbeniceDto, HttpStatus.OK);
	}

	@GetMapping("/{id}/robaCjenovnika")
	public ResponseEntity getCenovnikRoba(@PathVariable("id") long id) {
		Narudzbenica narudzbenica = narudzbenicaService.findOne(id);
		if (narudzbenica == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		List<Cjenovnik> cjenovnici = new ArrayList<Cjenovnik>();
		if (narudzbenica.getPoslovniPartner().getVrstaPartnera() == 1)
			cjenovnici.addAll(narudzbenica.getPoslovniPartner().getCjenovnici());
		else
			cjenovnici.addAll(narudzbenica.getPreduzece().getCjenovnici());

		ArrayList<RobaUsluga> roba = new ArrayList();

		Date date = new Date();

		for (Cjenovnik cjenovnik : cjenovnici) {
			boolean isafter = cjenovnik.getDatumVazenjaOd().before(date);
			boolean isbefore = cjenovnik.getDatumVazenjaDo().after(date);
			
			System.out.println(isafter);
			System.out.println(isbefore);
			
			if (isafter && isbefore && !(cjenovnik.isObrisano())) {
				for (StavkaCjenovnika s : cjenovnik.getStavkeCjenovnika()) {
					roba.add(s.getRobaUsluga());
				}
			}
		}

//        for (Cjenovnik cjenovnik : cjenovnici) {
//			if(cjenovnik.isActive()) {
//				 for (StavkaCjenovnika s : cjenovnik.getStavkeCjenovnika()) {
//			        	roba.add(s.getRobaUsluga());
//			        }
//			}
//		}
//        Cjenovnik c = cjenovnici.get(cjenovnici.size()-1);
//        ArrayList<RobaUsluga> roba = new ArrayList();
//        for (StavkaCjenovnika s : c.getStavkeCjenovnika()) {
//        	roba.add(s.getRobaUsluga());
//        }
		return ResponseEntity.ok(robaUslugaToDto.convert(roba));
	}

}
