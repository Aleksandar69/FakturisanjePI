package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.NarudzbenicaDto;
import com.aleksandar.fakturisanje.model.Narudzbenica;

@Component
public class NarudzbenicaToNarudzbenicaDto implements Converter<Narudzbenica, NarudzbenicaDto>{

	@Override
	public NarudzbenicaDto convert(Narudzbenica source) {
		NarudzbenicaDto narudzbenicaDto = new NarudzbenicaDto();
		
		narudzbenicaDto.setId(source.getId());
		narudzbenicaDto.setBrojNarudzbenice(source.getBrojNarudzbenice());
		narudzbenicaDto.setDatumNarudzbenice(source.getDatumNarudzbenice());
		narudzbenicaDto.setTipNarudzbenice(source.isTipNarudzbenice());
		narudzbenicaDto.setObrisano(source.isObrisano());
		narudzbenicaDto.setPoslovnaGodina(source.getPoslovnaGodina().getId());
		narudzbenicaDto.setPoslovniPartner(source.getPoslovniPartner().getId());
		narudzbenicaDto.setPreduzece(source.getPreduzece().getId());
		
		return narudzbenicaDto;
	}

    public List<NarudzbenicaDto> convert(List<Narudzbenica> narudzbenice) {

        List<NarudzbenicaDto> narudzbeniceDto = new ArrayList<>();

        for (Narudzbenica narudzbenica : narudzbenice) {
            NarudzbenicaDto narudzbenicaDto = convert(narudzbenica);
            narudzbeniceDto.add(narudzbenicaDto);
        }

        return narudzbeniceDto;
    }
	
}
