package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.OtpremnicaDto;
import com.aleksandar.fakturisanje.model.Otpremnica;

@Component
public class OtpremnicaToOtpremnicaDto  implements Converter<Otpremnica, OtpremnicaDto>{

	@Override
	public OtpremnicaDto convert(Otpremnica source) {
		OtpremnicaDto optremnicaDto = new OtpremnicaDto();
		
		optremnicaDto.setId(source.getId());
		optremnicaDto.setBrojOtpremnice(source.getBrojOtpremnice());
		optremnicaDto.setDatumOtpremnice(source.getDatumOtpremnice());
		optremnicaDto.setIznosZaUplatu(source.getIznosZaPlacanje());
		optremnicaDto.setTipOtpremnice(source.isTipOtpremnice());
		optremnicaDto.setObrisano(source.isObrisano());
		optremnicaDto.setPoslovnaGodina(source.getPoslovnaGodina().getId());
		optremnicaDto.setPoslovniPartner(source.getPoslovniPartner().getId());
        optremnicaDto.setPreduzece(source.getPreduzece().getId());
		
		return optremnicaDto;
	}
	
    public List<OtpremnicaDto> convert(List<Otpremnica> otpremnice) {

        List<OtpremnicaDto> otpremniceDto = new ArrayList<>();

        for (Otpremnica otpremnica: otpremnice) {
            OtpremnicaDto otpremnicaDto = convert(otpremnica);
            otpremniceDto.add(otpremnicaDto);
        }

        return otpremniceDto;
    }

}
