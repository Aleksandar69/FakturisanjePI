package com.aleksandar.fakturisanje.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.RobaUslugaDto;
import com.aleksandar.fakturisanje.model.GrupaRobe;
import com.aleksandar.fakturisanje.model.RobaUsluga;
import com.aleksandar.fakturisanje.service.interfaces.IGrupaRobeService;

@Component
public class RobaUslugaDtoToRobaUsluga implements Converter<RobaUslugaDto, RobaUsluga>{

	@Autowired
	IGrupaRobeService grupaRobeService;
	
	@Override
	public RobaUsluga convert(RobaUslugaDto source) {
		GrupaRobe grupaRobe = grupaRobeService.findOne(source.getGrupaRobe());
		
		RobaUsluga robaUsluga = new RobaUsluga();
		if (source.getId()!=null) robaUsluga.setId(source.getId());
		robaUsluga.setNaziv(source.getNazivRobeUsluge());
		robaUsluga.setJedinicaMjere(source.getJedinicaMjere());
		if (grupaRobe!=null) {
			robaUsluga.setGrupaRobe(grupaRobe);
		}
		return robaUsluga;
	}


	
	
}
