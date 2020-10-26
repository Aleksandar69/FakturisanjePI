package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.StopaPdvDto;
import com.aleksandar.fakturisanje.model.StopaPDV;

@Component
public class StopaPdvToStopaPdvDto implements Converter<StopaPDV, StopaPdvDto>{

	@Override
	public StopaPdvDto convert(StopaPDV source) {
		return new StopaPdvDto(source.getId(), source.getProcenat(), source.getDatumVazenja(), source.getPdv().getId());
	}

	public List<StopaPdvDto> convert(List<StopaPDV> stope){
		
		List<StopaPdvDto> retVal = new ArrayList<StopaPdvDto>();
		
		for (StopaPDV stopa : stope) {
			retVal.add(convert(stopa));
		}
		
		return retVal;	
	}
	
}
