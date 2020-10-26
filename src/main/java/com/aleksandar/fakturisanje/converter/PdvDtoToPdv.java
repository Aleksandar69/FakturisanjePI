package com.aleksandar.fakturisanje.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.PdvDto;
import com.aleksandar.fakturisanje.model.PDV;

@Component
public class PdvDtoToPdv implements Converter<PdvDto, PDV>{

	@Override
	public PDV convert(PdvDto source) {
		PDV pdv = new PDV();
		
		pdv.setId(source.getId());
		pdv.setNaziv(source.getNazivPdva());
		
		return pdv;
	}

	
}
