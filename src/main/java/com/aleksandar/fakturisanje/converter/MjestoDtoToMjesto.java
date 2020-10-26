package com.aleksandar.fakturisanje.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.MjestoDto;
import com.aleksandar.fakturisanje.model.Mjesto;

@Component
public class MjestoDtoToMjesto implements Converter<MjestoDto, Mjesto>{

	@Override
	public Mjesto convert(MjestoDto source) {
        Mjesto mjesto = new Mjesto();
        
        mjesto.setId(source.getId());
        mjesto.setNaziv(source.getNaziv());
        mjesto.setPostanskiBroj(source.getPostanskiBroj());
        mjesto.setDrzava(source.getDrzava());
        
        return mjesto;
	}

	
	
}
