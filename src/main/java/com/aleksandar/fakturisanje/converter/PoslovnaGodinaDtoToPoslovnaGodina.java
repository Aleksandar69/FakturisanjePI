package com.aleksandar.fakturisanje.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.PoslovnaGodinaDto;
import com.aleksandar.fakturisanje.model.PoslovnaGodina;

@Component
public class PoslovnaGodinaDtoToPoslovnaGodina implements Converter<PoslovnaGodinaDto, PoslovnaGodina> {

	@Override
	public PoslovnaGodina convert(PoslovnaGodinaDto source) {
		PoslovnaGodina poslovnaGodina = new PoslovnaGodina();
		
		poslovnaGodina.setId(source.getId());
		poslovnaGodina.setGodina(source.getGodina());
		poslovnaGodina.setZakljucana(source.isZakljucana());
		
		return poslovnaGodina;
	}

}
