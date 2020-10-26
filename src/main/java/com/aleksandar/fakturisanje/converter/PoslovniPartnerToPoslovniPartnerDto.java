package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.PoslovniPartnerDto;
import com.aleksandar.fakturisanje.model.PoslovniPartner;

@Component
public class PoslovniPartnerToPoslovniPartnerDto implements Converter<PoslovniPartner, PoslovniPartnerDto>{

	@Override
	public PoslovniPartnerDto convert(PoslovniPartner source) {
		return new PoslovniPartnerDto(source.getId(), source.getNaziv(), source.getAdresa(), source.getVrstaPartnera(), source.getTekuciRacun(), source.getPIB(), source.getPreduzece().getId(), source.getMjesto().getId());
	}

	public List<PoslovniPartnerDto> convert(List<PoslovniPartner> partneri){
		List<PoslovniPartnerDto> retVal = new ArrayList<PoslovniPartnerDto>();
		for (PoslovniPartner partner : partneri) {
			retVal.add(convert(partner));
		}
		return retVal;	
	}
}
