package com.aleksandar.fakturisanje.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.StavkaFaktureDto;
import com.aleksandar.fakturisanje.model.Faktura;
import com.aleksandar.fakturisanje.model.RobaUsluga;
import com.aleksandar.fakturisanje.model.StavkaFakture;
import com.aleksandar.fakturisanje.service.interfaces.IFakturaService;
import com.aleksandar.fakturisanje.service.interfaces.IRobaUslugaService;

@Component
public class StavkaFaktureDtoToStavkaFakture implements Converter<StavkaFaktureDto, StavkaFakture> {

	@Autowired
	private IFakturaService fakturaService;
	
	@Autowired
	private IRobaUslugaService robaUslugaService;

	@Override
	public StavkaFakture convert(StavkaFaktureDto source) {
		Faktura f = fakturaService.findOne(source.getFaktura());
		RobaUsluga r = robaUslugaService.findOne(source.getRobaUsluga());
		
		StavkaFakture stavka = new StavkaFakture();
		stavka.setKolicina(source.getKolicina());
		stavka.setCijena(source.getCijena());
		stavka.setRabat(source.getRabat());
		stavka.setPdvOsnovica(source.getOsnovicaZaPdv());
		stavka.setPdvProcenat(source.getProcenatPdv());
		stavka.setIznosPdva(source.getIznosPdv());
		stavka.setIznos(source.getInosStavka());
		if (f!=null) {
			stavka.setFaktura(f);
		}
		if (r!=null) {
			stavka.setRobaUsluga(r);
		}
		return stavka;
	}

}
