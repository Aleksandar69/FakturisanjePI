package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.StavkaFaktureDto;
import com.aleksandar.fakturisanje.model.StavkaFakture;

@Component
public class StavkaFaktureToStavkaFaktureDto implements Converter<StavkaFakture, StavkaFaktureDto>{

	@Override
	public StavkaFaktureDto convert(StavkaFakture source) {
		return new StavkaFaktureDto(source.getId(), source.getKolicina(), source.getCijena(), source.getRabat(), source.getPdvOsnovica(), source.getPdvProcenat(), source.getIznosPdva(), source.getIznos(), source.getFaktura().getId(), source.getRobaUsluga().getId());
	}

	public List<StavkaFaktureDto> convert(List<StavkaFakture> stavke){
		
		List<StavkaFaktureDto> retVal = new ArrayList<StavkaFaktureDto>();
		
		for (StavkaFakture stavka : stavke) {
			retVal.add(convert(stavka));
		}
		
		return retVal;	
	}
	
	
}
