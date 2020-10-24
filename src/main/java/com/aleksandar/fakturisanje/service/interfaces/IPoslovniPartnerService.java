package com.aleksandar.fakturisanje.service.interfaces;

import java.util.List;

import com.aleksandar.fakturisanje.model.Cjenovnik;
import com.aleksandar.fakturisanje.model.PoslovniPartner;

public interface IPoslovniPartnerService {

	List<PoslovniPartner> findAll(String filter);
    List<PoslovniPartner> findAll();
    List<PoslovniPartner> findByPreduzece_id(Long id);
    PoslovniPartner findOne(Long id);
    PoslovniPartner save(PoslovniPartner poslovniPartner);
    Boolean delete(Long id);
    PoslovniPartner findPartner(Cjenovnik cjenovnik);
	
}
