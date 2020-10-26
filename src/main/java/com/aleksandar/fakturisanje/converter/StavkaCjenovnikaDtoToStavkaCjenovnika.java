package com.aleksandar.fakturisanje.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.StavkaCjenovnikaDto;
import com.aleksandar.fakturisanje.model.Cjenovnik;
import com.aleksandar.fakturisanje.model.RobaUsluga;
import com.aleksandar.fakturisanje.model.StavkaCjenovnika;
import com.aleksandar.fakturisanje.service.interfaces.ICjenovnikService;
import com.aleksandar.fakturisanje.service.interfaces.IRobaUslugaService;

@Component
public class StavkaCjenovnikaDtoToStavkaCjenovnika implements Converter<StavkaCjenovnikaDto, StavkaCjenovnika>{

	@Autowired
	ICjenovnikService cjenovnikService;
	
	@Autowired
	IRobaUslugaService robaUslugaService;
	
	@Override
	public StavkaCjenovnika convert(StavkaCjenovnikaDto source) {
		Cjenovnik cjenovnik = cjenovnikService.findOne(source.getCjenovnik());
		RobaUsluga robaUsluga = robaUslugaService.findOne(source.getRobaUsluga());
		StavkaCjenovnika stavka = new StavkaCjenovnika();
		stavka.setCijena(source.getCijena());
		if (robaUsluga!=null) {
			stavka.setRobaUsluga(robaUsluga);
		}
		if (cjenovnik!=null) {
			stavka.setCjenovnik(cjenovnik);
		}
		return stavka;
	}

	
	
}
