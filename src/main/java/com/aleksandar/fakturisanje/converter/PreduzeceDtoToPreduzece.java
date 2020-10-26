package com.aleksandar.fakturisanje.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.PreduzeceDto;
import com.aleksandar.fakturisanje.model.Mjesto;
import com.aleksandar.fakturisanje.model.Preduzece;
import com.aleksandar.fakturisanje.service.interfaces.IMjestoService;

@Component
public class PreduzeceDtoToPreduzece implements Converter<PreduzeceDto, Preduzece> {

	@Autowired
	IMjestoService mjestoService;

	@Override
	public Preduzece convert(PreduzeceDto source) {
		Mjesto mjesto = mjestoService.findOne(source.getMjesto());
		Preduzece preduzece = new Preduzece();
		preduzece.setNaziv(source.getNaziv());
		preduzece.setAdresa(source.getAdresaPreduzeca());
		preduzece.setPIB(source.getPIB());
		preduzece.setTel(source.getTelefon());
		preduzece.setEmail(source.getEmail());
		preduzece.setTekuciRacun(source.getTekuciRacun());
		preduzece.setLogo(source.getLogo());
		preduzece.setId(source.getId());
		if (mjesto!=null) {
			preduzece.setMjesto(mjesto);
		}
		return preduzece;
	}
	
	
}
