package com.aleksandar.fakturisanje.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.OtpremnicaDto;
import com.aleksandar.fakturisanje.model.Narudzbenica;
import com.aleksandar.fakturisanje.model.Otpremnica;
import com.aleksandar.fakturisanje.model.PoslovnaGodina;
import com.aleksandar.fakturisanje.model.PoslovniPartner;
import com.aleksandar.fakturisanje.model.Preduzece;
import com.aleksandar.fakturisanje.service.interfaces.INarudzbenicaService;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovnaGodinaService;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovniPartnerService;
import com.aleksandar.fakturisanje.service.interfaces.IPreduzeceService;

@Component
public class OtpremnicaDtoToOtpremnica implements Converter<OtpremnicaDto, Otpremnica> {

	@Autowired
	private IPreduzeceService preduzeceService;
	
	@Autowired
	private IPoslovniPartnerService poslovniPartnerService;
	
	@Autowired
	private IPoslovnaGodinaService poslovnaGodinaService;
	
	@Autowired
	private INarudzbenicaService narudzbenicaService;
	
	@Override
	public Otpremnica convert(OtpremnicaDto source) {
		 Otpremnica otpremnica = new Otpremnica();
	        otpremnica.setId(source.getId());
	        otpremnica.setBrojOtpremnice(source.getBrojOtpremnice());
	        otpremnica.setDatumOtpremnice(source.getDatumOtpremnice());
	        otpremnica.setIznosZaPlacanje(source.getIznosZaUplatu());
	        otpremnica.setTipOtpremnice(source.isTipOtpremnice());
	        otpremnica.setObrisano(false);


	        Preduzece preduzece = preduzeceService.findOne(source.getPreduzece());
	        if(preduzece != null){
	            otpremnica.setPreduzece(preduzece);
	        }

	        PoslovniPartner poslovniPartner = poslovniPartnerService.findOne(source.getPoslovniPartner());
	        if(poslovniPartner != null){
	            otpremnica.setPoslovniPartner(poslovniPartner);
	        }

	        PoslovnaGodina poslovnaGodina = poslovnaGodinaService.findOne(source.getPoslovnaGodina());
	        if(poslovnaGodina != null){
	            otpremnica.setPoslovnaGodina(poslovnaGodina);
	        }

	        Narudzbenica narudzbenica = narudzbenicaService.findOne(source.getNarudzbenica());
	        if (narudzbenica != null) {
	            otpremnica.setNarudzbenica(narudzbenica);
	        }

	        return otpremnica;
	}

	
	
}
