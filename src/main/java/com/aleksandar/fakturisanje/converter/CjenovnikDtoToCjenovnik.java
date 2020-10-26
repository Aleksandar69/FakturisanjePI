package com.aleksandar.fakturisanje.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.CjenovnikDto;
import com.aleksandar.fakturisanje.model.Cjenovnik;

@Component
public class CjenovnikDtoToCjenovnik  implements Converter<CjenovnikDto, Cjenovnik>{

	@Override
	public Cjenovnik convert(CjenovnikDto source) {
		Cjenovnik cjenovnik = new Cjenovnik();
		cjenovnik.setId(source.getId());
		cjenovnik.setDatumVazenja(source.getDatumVazenja());
		
		return cjenovnik;
	}

	
	
}
