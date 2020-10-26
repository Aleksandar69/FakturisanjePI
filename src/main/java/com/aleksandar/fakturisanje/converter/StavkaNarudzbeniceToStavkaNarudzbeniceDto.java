package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.StavkaNarudzbeniceDto;
import com.aleksandar.fakturisanje.model.StavkaNarudzbenice;

@Component
public class StavkaNarudzbeniceToStavkaNarudzbeniceDto implements Converter<StavkaNarudzbenice, StavkaNarudzbeniceDto>{

	@Override
	public StavkaNarudzbeniceDto convert(StavkaNarudzbenice source) {
		return new StavkaNarudzbeniceDto(source.getId(), source.getJedinicaMjere(), source.getKolicina(),
				source.getOpis(), source.getRobaUsluga().getId(), source.getNarudzbenica().getId(), source.isObrisano());
	}

    public List<StavkaNarudzbeniceDto> convert(List<StavkaNarudzbenice> source) {

        List<StavkaNarudzbeniceDto> stavkeNarudzbenice = new ArrayList<>();

        for (StavkaNarudzbenice stavka : source) {
            StavkaNarudzbeniceDto stavkaNarudzbeniceDto = convert(stavka);
            stavkeNarudzbenice.add(stavkaNarudzbeniceDto);
        }

        return stavkeNarudzbenice;
    }
	
}
