package com.aleksandar.fakturisanje.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.CjenovnikDto;
import com.aleksandar.fakturisanje.model.Cjenovnik;
import com.aleksandar.fakturisanje.model.PoslovniPartner;
import com.aleksandar.fakturisanje.model.Preduzece;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovniPartnerService;
import com.aleksandar.fakturisanje.service.interfaces.IPreduzeceService;

@Component
public class CjenovnikDtoToCjenovnik  implements Converter<CjenovnikDto, Cjenovnik>{

    @Autowired
    private IPreduzeceService preduzeceServiceInterface;

    @Autowired
    private IPoslovniPartnerService poslovniPartnerServiceInterface;
    
	@Override
	public Cjenovnik convert(CjenovnikDto source) {
		Cjenovnik cjenovnik = new Cjenovnik();
		cjenovnik.setId(source.getId());
		cjenovnik.setDatumVazenjaOd(source.getDatumVazenjaOd());
		cjenovnik.setDatumVazenjaDo(source.getDatumVazenjaDo());
		
		Preduzece preduzece = preduzeceServiceInterface.findOne(source.getPreduzeceId());
	      if(preduzece!=null) {
	    	  cjenovnik.setPreduzece(preduzece);
	      }
	      
	     PoslovniPartner poslovniPartner = poslovniPartnerServiceInterface.findOne(source.getPoslovniPartnerId());
	     if(poslovniPartner!=null) {
	    	  cjenovnik.setPoslovniPartner(poslovniPartner);
	      }
		
		return cjenovnik;
	}

	
	
}
