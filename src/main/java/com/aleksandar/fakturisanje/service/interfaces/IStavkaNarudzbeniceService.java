package com.aleksandar.fakturisanje.service.interfaces;

import java.util.List;

import com.aleksandar.fakturisanje.model.StavkaNarudzbenice;

public interface IStavkaNarudzbeniceService {

    List<StavkaNarudzbenice> findAll();

    List<StavkaNarudzbenice> pronadjiStavkeNarudzbenice(long id);

    StavkaNarudzbenice findOne(Long id);

    StavkaNarudzbenice save(StavkaNarudzbenice stavkaNarudzbenice);

    void delete(Long id);
	
}
