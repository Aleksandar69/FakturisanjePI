package com.aleksandar.fakturisanje.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.StopaPdvDto;
import com.aleksandar.fakturisanje.model.PDV;
import com.aleksandar.fakturisanje.model.StopaPDV;
import com.aleksandar.fakturisanje.service.interfaces.IPDVService;

@Component
public class StopaPdvDtoToStopaPdv implements Converter<StopaPdvDto, StopaPDV> {

	@Autowired
	IPDVService pdvService;
	
	@Override
	public StopaPDV convert(StopaPdvDto source) {
		PDV pdv = pdvService.findOne(source.getPdv());
		StopaPDV stopa = new StopaPDV();
		stopa.setProcenat(source.getProcenat());
		stopa.setDatumVazenja(source.getDatumVazenja());
		if (pdv!=null) {
			stopa.setPdv(pdv);
		}
		return stopa;
	}

}
