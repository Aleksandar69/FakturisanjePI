package com.aleksandar.fakturisanje.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.StavkaNarudzbeniceDto;
import com.aleksandar.fakturisanje.model.Narudzbenica;
import com.aleksandar.fakturisanje.model.RobaUsluga;
import com.aleksandar.fakturisanje.model.StavkaNarudzbenice;
import com.aleksandar.fakturisanje.service.interfaces.INarudzbenicaService;
import com.aleksandar.fakturisanje.service.interfaces.IRobaUslugaService;

@Component
public class StavkaNarudzbeniceDtoToStavkaNarudzbenice implements Converter<StavkaNarudzbeniceDto, StavkaNarudzbenice> {

	@Autowired
	INarudzbenicaService narudzbenicaService;

	@Autowired
	IRobaUslugaService robaUslugaService;

	@Override
	public StavkaNarudzbenice convert(StavkaNarudzbeniceDto source) {

		Narudzbenica narudzbenica = narudzbenicaService.findOne(source.getNarudzbenica());
		RobaUsluga robaUsluga = robaUslugaService.findOne(source.getRobaUsluga());

		StavkaNarudzbenice stavkaNarudzbenice = new StavkaNarudzbenice();
		stavkaNarudzbenice.setOpis(source.getOpisRobe());
		stavkaNarudzbenice.setJedinicaMjere(source.getJedinicaMjere());
		stavkaNarudzbenice.setKolicina(source.getKolicina());

		if (narudzbenica != null) {
			stavkaNarudzbenice.setNarudzbenica(narudzbenica);
		}

		if (robaUsluga != null) {
			stavkaNarudzbenice.setRobaUsluga(robaUsluga);
		}

		return stavkaNarudzbenice;
	}

}
