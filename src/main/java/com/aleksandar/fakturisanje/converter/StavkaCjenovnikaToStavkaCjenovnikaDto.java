package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.StavkaCjenovnikaDto;
import com.aleksandar.fakturisanje.model.StavkaCjenovnika;

@Component
public class StavkaCjenovnikaToStavkaCjenovnikaDto implements Converter<StavkaCjenovnika, StavkaCjenovnikaDto> {

	@Override
	public StavkaCjenovnikaDto convert(StavkaCjenovnika source) {
		return new StavkaCjenovnikaDto(source.getId(), source.getCijena(), source.getCjenovnik().getId(), source.getRobaUsluga().getId());
	}

	public List<StavkaCjenovnikaDto> convert(List<StavkaCjenovnika> stavke){
		List<StavkaCjenovnikaDto> retVal = new ArrayList<StavkaCjenovnikaDto>();
		for (StavkaCjenovnika stavka : stavke) {
			retVal.add(convert(stavka));
		}
		return retVal;	
	}
	
	
}
