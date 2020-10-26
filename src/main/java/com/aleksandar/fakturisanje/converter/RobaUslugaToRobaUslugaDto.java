package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.RobaUslugaDto;
import com.aleksandar.fakturisanje.model.RobaUsluga;

@Component
public class RobaUslugaToRobaUslugaDto implements Converter<RobaUsluga, RobaUslugaDto> {

	@Override
	public RobaUslugaDto convert(RobaUsluga source) {
		return new RobaUslugaDto(source.getId(), source.getNaziv(), source.getJedinicaMjere(), source.getGrupaRobe().getId());
	}

	public List<RobaUslugaDto> convert(List<RobaUsluga> robaUsluge){
		List<RobaUslugaDto> retVal = new ArrayList<RobaUslugaDto>();
		for (RobaUsluga robaUsluga : robaUsluge) {
			retVal.add(convert(robaUsluga));
		}
		return retVal;	
	}
	
}
