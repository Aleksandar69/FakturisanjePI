package com.aleksandar.fakturisanje.service.interfaces;

import java.util.List;

import com.aleksandar.fakturisanje.model.StopaPDV;

public interface IStopaPDVaService {
	
    List<StopaPDV> findAll();
    StopaPDV findOne(Long id);
    StopaPDV save(StopaPDV stopaPDV);
    Boolean delete(Long id);
}
