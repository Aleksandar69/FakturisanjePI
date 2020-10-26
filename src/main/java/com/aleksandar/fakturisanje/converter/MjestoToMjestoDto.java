package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.MjestoDto;
import com.aleksandar.fakturisanje.model.Mjesto;

@Component
public class MjestoToMjestoDto implements Converter<Mjesto, MjestoDto>{

	@Override
	public MjestoDto convert(Mjesto source) {
		MjestoDto mjestoDto = new MjestoDto();
		
		mjestoDto.setId(source.getId());
		mjestoDto.setNaziv(source.getNaziv());
		mjestoDto.setPostanskiBroj(source.getPostanskiBroj());
		mjestoDto.setDrzava(source.getDrzava());
		
		return mjestoDto;
	}

	public List<MjestoDto> convert(List<Mjesto> mjesta){
        List<MjestoDto> mjestoDtos = new ArrayList<>();
        for (Mjesto m:mjesta) {
            mjestoDtos.add(convert(m));
        }
        return mjestoDtos;
    }
	
}
