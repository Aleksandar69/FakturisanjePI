package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.PreduzeceDto;
import com.aleksandar.fakturisanje.model.Preduzece;

@Component
public class PreduzeceToPreduzeceDto implements Converter<Preduzece, PreduzeceDto>{

	@Override
	public PreduzeceDto convert(Preduzece source) {
		return new PreduzeceDto(source.getId(), source.getNaziv(), source.getAdresa(), source.getPIB(), source.getTel(),
				source.getEmail(), source.getTekuciRacun(), source.getLogo(), source.getMjesto().getId());
	}

	public List<PreduzeceDto> convert(List<Preduzece> preduzeca){
		List<PreduzeceDto> retVal = new ArrayList<PreduzeceDto>();
		for (Preduzece preduzece : preduzeca) {
			retVal.add(convert(preduzece));
		}
		return retVal;	
	}
	
}
