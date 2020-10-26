package com.aleksandar.fakturisanje.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.StavkaOtpremniceDto;
import com.aleksandar.fakturisanje.model.Otpremnica;
import com.aleksandar.fakturisanje.model.RobaUsluga;
import com.aleksandar.fakturisanje.model.StavkaOtpremnice;
import com.aleksandar.fakturisanje.service.interfaces.IOtpremnicaService;
import com.aleksandar.fakturisanje.service.interfaces.IRobaUslugaService;

@Component
public class StavkaOtpremniceDtoToStavkaOtpremnice implements Converter<StavkaOtpremniceDto, StavkaOtpremnice> {

	@Autowired
	private IOtpremnicaService otpremnicaService;
	
	@Autowired
	IRobaUslugaService robaUslugaService;
	
	@Override
	public StavkaOtpremnice convert(StavkaOtpremniceDto source) {
        Otpremnica otpremnica = otpremnicaService.findOne(source.getOtpremnica());
        RobaUsluga robaUsluga = robaUslugaService.findOne(source.getRobaUsluga());

        StavkaOtpremnice stavkaOtpremnice = new StavkaOtpremnice();
        stavkaOtpremnice.setOpis(robaUsluga.getNaziv());
        stavkaOtpremnice.setJedinicaMjere(source.getJedinicaMjere());
        stavkaOtpremnice.setKolicina(source.getKolicina());
        stavkaOtpremnice.setCijena(source.getCijena());
        stavkaOtpremnice.setIznos(source.getIznos());
        stavkaOtpremnice.setObrisano(false);

        if (otpremnica != null) {
            stavkaOtpremnice.setOtpremnica(otpremnica);
        }

        if (robaUsluga != null) {
            stavkaOtpremnice.setRobaUsluga(robaUsluga);
        }

        return stavkaOtpremnice;
    }

}
