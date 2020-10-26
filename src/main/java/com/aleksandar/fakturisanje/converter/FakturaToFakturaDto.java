package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.FakturaDto;
import com.aleksandar.fakturisanje.model.Faktura;

@Component
public class FakturaToFakturaDto implements Converter<Faktura, FakturaDto>{

	@Override
	public FakturaDto convert(Faktura source) {
		FakturaDto fakturaDto = new FakturaDto();
		
		fakturaDto.setId(source.getId());
		fakturaDto.setBrFakture(source.getBrojFakture());
		fakturaDto.setDatumFakture(source.getDatumFakture());
		fakturaDto.setDatumValute(source.getDatumValute());
		fakturaDto.setOsnovica(source.getOsnovica());
		fakturaDto.setRabat(source.getRabat());
		fakturaDto.setIznosBezRabata(source.getIznosBezRabata());
		fakturaDto.setUkupanPdv(source.getUkupanPdv());
		fakturaDto.setIznosZaPlacanje(source.getIznosZaPlacanje());
		fakturaDto.setPlaceno(source.isPlaceno());
		fakturaDto.setVrstaFakture(source.isVrstaFakture());
		fakturaDto.setPreduzece(source.getPreduzece().getId());
		fakturaDto.setPoslovniPartner(source.getPoslovniPartner().getId());
		fakturaDto.setPoslovnaGodina(source.getPoslovnaGodina().getId());
		
		return fakturaDto;
	}


    public List<FakturaDto> convert(List<Faktura> fakture){
        List<FakturaDto> fakturaDtos = new ArrayList<>();
        for (Faktura f:fakture) {
            fakturaDtos.add(convert(f));
        }
        return fakturaDtos;
    }
	
}
