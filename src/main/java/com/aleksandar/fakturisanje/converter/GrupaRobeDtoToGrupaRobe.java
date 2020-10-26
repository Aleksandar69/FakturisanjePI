package com.aleksandar.fakturisanje.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.GrupaRobeDto;
import com.aleksandar.fakturisanje.model.GrupaRobe;
import com.aleksandar.fakturisanje.model.PDV;
import com.aleksandar.fakturisanje.model.Preduzece;
import com.aleksandar.fakturisanje.service.interfaces.IPDVService;
import com.aleksandar.fakturisanje.service.interfaces.IPreduzeceService;

@Component
public class GrupaRobeDtoToGrupaRobe implements Converter<GrupaRobeDto, GrupaRobe>{

	@Autowired
	private IPreduzeceService preduzeceServiceIf;
	
	@Autowired
	private IPDVService pdvServiceIf;

	@Override
	public GrupaRobe convert(GrupaRobeDto source) {
		
        GrupaRobe grupaRobe = new GrupaRobe();
        
        grupaRobe.setId(source.getId());
        grupaRobe.setGrupaNaziv(source.getNazivGrupe());
        Preduzece preduzece = preduzeceServiceIf.findOne(source.getPreduzece());
        if(preduzece!=null){
            grupaRobe.setPreduzece(preduzece);
        }
        PDV pdv = pdvServiceIf.findOne(source.getPdv());
        if(pdv!=null){
            grupaRobe.setPdv(pdv);
        }
        return grupaRobe;
	}
	
	
}
