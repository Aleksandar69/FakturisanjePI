package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.PdvDto;
import com.aleksandar.fakturisanje.model.PDV;

@Component
public class PdvToPdvDto implements Converter<PDV, PdvDto> {

	@Override
	public PdvDto convert(PDV source) {
		PdvDto pdvDto = new PdvDto();
		
		pdvDto.setId(source.getId());
		pdvDto.setNazivPdva(source.getNaziv());
		
		return pdvDto;
	}

    public List<PdvDto> convert(List<PDV> pdvList){
        List<PdvDto> PdvDtoList = new ArrayList<>();
        for (PDV p:pdvList) {
            PdvDtoList.add(convert(p));
        }
        return PdvDtoList;
    }
}
