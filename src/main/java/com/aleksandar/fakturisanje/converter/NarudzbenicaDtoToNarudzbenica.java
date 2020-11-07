package com.aleksandar.fakturisanje.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.NarudzbenicaDto;
import com.aleksandar.fakturisanje.model.Narudzbenica;
import com.aleksandar.fakturisanje.model.PoslovnaGodina;
import com.aleksandar.fakturisanje.model.PoslovniPartner;
import com.aleksandar.fakturisanje.model.Preduzece;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovnaGodinaService;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovniPartnerService;
import com.aleksandar.fakturisanje.service.interfaces.IPreduzeceService;

@Component
public class NarudzbenicaDtoToNarudzbenica implements Converter<NarudzbenicaDto, Narudzbenica> {

	
	@Autowired
	private IPreduzeceService preduzeceServiceIf;

	@Autowired
	private IPoslovniPartnerService poslovniPartnerIf;
	
	@Autowired
	private IPoslovnaGodinaService poslovnaGodIf;
	
	@Override
	public Narudzbenica convert(NarudzbenicaDto source) {
		Narudzbenica narudzbenica = new Narudzbenica();

        narudzbenica.setId(source.getId());
        narudzbenica.setBrojNarudzbenice(source.getBrojNarudzbenice());
        narudzbenica.setDatumNarudzbenice(source.getDatumNarudzbenice());
        narudzbenica.setTipNarudzbenice(source.isTipNarudzbenice());
        narudzbenica.setObrisano(false);

        Preduzece preduzece = preduzeceServiceIf.findOne(source.getPreduzece());
        if(preduzece != null){
            narudzbenica.setPreduzece(preduzece);
        }

        PoslovniPartner poslovniPartner = poslovniPartnerIf.findOne(source.getPoslovniPartner());
        if(poslovniPartner != null){
            narudzbenica.setPoslovniPartner(poslovniPartner);
        }

        System.out.println("POSLOVNA GODINA ID: " + source.getPoslovnaGodina());
        PoslovnaGodina poslovnaGodina = poslovnaGodIf.findOne(source.getPoslovnaGodina());
        if(poslovnaGodina != null){
            narudzbenica.setPoslovnaGodina(poslovnaGodina);
        }

        return narudzbenica;
	}

}
