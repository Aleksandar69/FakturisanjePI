package com.aleksandar.fakturisanje.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aleksandar.fakturisanje.dto.PoslovniPartnerDto;
import com.aleksandar.fakturisanje.model.Mjesto;
import com.aleksandar.fakturisanje.model.PoslovniPartner;
import com.aleksandar.fakturisanje.model.Preduzece;
import com.aleksandar.fakturisanje.service.interfaces.IMjestoService;
import com.aleksandar.fakturisanje.service.interfaces.IPreduzeceService;

@Component
public class PoslovniPartnerDtoToPoslovniPartner implements Converter<PoslovniPartnerDto, PoslovniPartner>{
	
	@Autowired
	IMjestoService mjestoService;
	
	@Autowired
	IPreduzeceService preduzeceService;
	
	@Override
	public PoslovniPartner convert(PoslovniPartnerDto source) {
		Mjesto mesto = mjestoService.findOne(source.getMjesto());
		Preduzece preduzece = preduzeceService.findOne(source.getPreduzece());
		PoslovniPartner poslovniPartner = new PoslovniPartner();
		poslovniPartner.setNaziv(source.getNazivPartnera());
		poslovniPartner.setAdresa(source.getAdresa());
		poslovniPartner.setVrstaPartnera(source.getVrstaPartnera());
		poslovniPartner.setTekuciRacun(source.getTekuciRacun());
		poslovniPartner.setPIB(source.getPIB());
		poslovniPartner.setId(source.getId());
		if (mesto!=null) {
			poslovniPartner.setMjesto(mesto);
		}
		if (preduzece!=null) {
			poslovniPartner.setPreduzece(preduzece);
		}
		return poslovniPartner;
	}
	

}
