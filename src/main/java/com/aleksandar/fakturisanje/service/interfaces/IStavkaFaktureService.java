package com.aleksandar.fakturisanje.service.interfaces;

import java.util.List;

import com.aleksandar.fakturisanje.model.StavkaFakture;

public interface IStavkaFaktureService {

	List<StavkaFakture> findAll();
    List<StavkaFakture> findByFaktura_id(Long id);
    StavkaFakture findOne(Long id);
    StavkaFakture save(StavkaFakture stavkaFakture);
    Boolean delete(Long id);
	
}
