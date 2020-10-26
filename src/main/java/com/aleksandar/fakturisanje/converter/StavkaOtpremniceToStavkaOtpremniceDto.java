package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.StavkaOtpremniceDto;
import com.aleksandar.fakturisanje.model.StavkaOtpremnice;

@Component
public class StavkaOtpremniceToStavkaOtpremniceDto implements Converter<StavkaOtpremnice, StavkaOtpremniceDto>{

	@Override
	public StavkaOtpremniceDto convert(StavkaOtpremnice source) {
		return new StavkaOtpremniceDto(source.getId(), source.getJedinicaMjere(), source.getKolicina(),
				source.getCijena(), source.getIznos(), source.getOpis(), source.getRobaUsluga().getId(), source.getOtpremnica().getId(), source.isObrisano());
	}

    public List<StavkaOtpremniceDto> convert(List<StavkaOtpremnice> stavkaOtpremnice) {

        List<StavkaOtpremniceDto> stavkeOtpremniceDto = new ArrayList<>();

        for (StavkaOtpremnice stavke : stavkaOtpremnice) {
            StavkaOtpremniceDto stavkaOtpremniceDto = convert(stavke);
            stavkeOtpremniceDto.add(stavkaOtpremniceDto);
        }

        return stavkeOtpremniceDto;
    }
	
}
