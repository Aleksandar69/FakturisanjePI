package com.aleksandar.fakturisanje.service.interfaces;

import java.util.List;

import com.aleksandar.fakturisanje.model.PDV;

public interface IPDVService {

	List<PDV> findAll();
    PDV findOne(Long id);
    PDV save(PDV pdv);
    Boolean delete(Long id);
    
}
