package com.aleksandar.fakturisanje.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.FakturaDto;
import com.aleksandar.fakturisanje.model.Faktura;
import com.aleksandar.fakturisanje.model.PoslovnaGodina;
import com.aleksandar.fakturisanje.model.PoslovniPartner;
import com.aleksandar.fakturisanje.model.Preduzece;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovnaGodinaService;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovniPartnerService;
import com.aleksandar.fakturisanje.service.interfaces.IPreduzeceService;

@Component
public class FakturaDtoToFaktura implements Converter<FakturaDto, Faktura>{

    @Autowired
    private IPreduzeceService preduzeceServiceInterface;

    @Autowired
    private IPoslovniPartnerService poslovniPartnerServiceInterface;

    @Autowired
    private IPoslovnaGodinaService poslovnaGodinaServiceInterface;

	@Override
	public Faktura convert(FakturaDto source) {
		Faktura faktura = new Faktura();
		
	      faktura.setId(source.getId());
	      faktura.setBrojFakture(source.getBrFakture());
	      faktura.setDatumFakture(source.getDatumFakture());
	      faktura.setDatumValute(source.getDatumValute());
	      faktura.setOsnovica(source.getOsnovica());
	      faktura.setIznosBezRabata(source.getIznosBezRabata());
	      faktura.setRabat(source.getRabat());
	      faktura.setUkupanPdv(source.getUkupanPdv());
	      faktura.setIznosZaPlacanje(source.getIznosZaPlacanje());
	      faktura.setPlaceno(source.isPlaceno());
	      faktura.setVrstaFakture(source.isVrstaFakture());
	      
	      Preduzece preduzece = preduzeceServiceInterface.findOne(source.getPreduzece());
	      if(preduzece!=null) {
	    	  faktura.setPreduzece(preduzece);
	      }
	      PoslovniPartner poslovniPartner = poslovniPartnerServiceInterface.findOne(source.getPoslovniPartner());
	      if(poslovniPartner !=null) {
	    	  faktura.setPoslovniPartner(poslovniPartner);
	      }
	      PoslovnaGodina poslovnaGodina = poslovnaGodinaServiceInterface.findOne(source.getPoslovnaGodina());
	      if(poslovnaGodina!=null){
	           faktura.setPoslovnaGodina(poslovnaGodina);
	        }
	        return faktura;
	}
	

	
}
