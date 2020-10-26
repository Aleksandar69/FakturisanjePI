package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.PoslovnaGodinaDto;
import com.aleksandar.fakturisanje.model.PoslovnaGodina;

@Component
public class PoslovnaGodinatoPoslovnaGodinaDto implements Converter<PoslovnaGodina, PoslovnaGodinaDto>{

	@Override
	public PoslovnaGodinaDto convert(PoslovnaGodina source) {
		return new PoslovnaGodinaDto(source.getId(), source.getGodina(), source.isZakljucana());
		
		
	}

    public List<PoslovnaGodinaDto> convert(List<PoslovnaGodina> poslovneGodine){
        List<PoslovnaGodinaDto> poslovnaGodinaDtoList = new ArrayList<>();
        for (PoslovnaGodina pg:poslovneGodine) {
            poslovnaGodinaDtoList.add(convert(pg));
        }
        return poslovnaGodinaDtoList;
    }
	
}
