package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.CjenovnikDto;
import com.aleksandar.fakturisanje.model.Cjenovnik;

@Component
public class CjenovnikToCjenovnikDto implements Converter<Cjenovnik, CjenovnikDto>{

	@Override
	public CjenovnikDto convert(Cjenovnik source) {
		CjenovnikDto cjenovnikDto = new CjenovnikDto();
		
		cjenovnikDto.setId(source.getId());
		cjenovnikDto.setDatumVazenja(source.getDatumVazenja());
		return cjenovnikDto;
	}

	public List<CjenovnikDto> convert(List<Cjenovnik> cjenovnici){
		List<CjenovnikDto> cjenovnikDto = new ArrayList<>();
		
		for(Cjenovnik c : cjenovnici) {
			cjenovnikDto.add(convert(c));
		}
		return cjenovnikDto;
	}
	
	
}
