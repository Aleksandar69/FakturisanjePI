package com.aleksandar.fakturisanje.service.interfaces;

import java.util.List;

import com.aleksandar.fakturisanje.model.StavkaOtpremnice;

public interface IStavkaOtpremniceService {

    List<StavkaOtpremnice> findAll();

    StavkaOtpremnice findOne(Long id);

    StavkaOtpremnice save(StavkaOtpremnice stavkaOtpremnice);

    void delete(Long id);

    List<StavkaOtpremnice> pronadjiStavkeOtpremnice(long id);
	
}
