package com.aleksandar.fakturisanje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleksandar.fakturisanje.model.Otpremnica;
import com.aleksandar.fakturisanje.model.StavkaOtpremnice;
import com.aleksandar.fakturisanje.repo.StavkaOtpremniceRepository;
import com.aleksandar.fakturisanje.service.interfaces.IOtpremnicaService;
import com.aleksandar.fakturisanje.service.interfaces.IStavkaOtpremniceService;

@Service
public class StavkaOtpremniceService implements IStavkaOtpremniceService {
	
    @Autowired
    private StavkaOtpremniceRepository stavkaOtpremniceRepo;

    @Autowired
    private IOtpremnicaService otpremnicaService;

	@Override
	public List<StavkaOtpremnice> findAll() {
        return stavkaOtpremniceRepo.findAll();

	}

	@Override
	public StavkaOtpremnice findOne(Long id) {
		Optional<StavkaOtpremnice> result = stavkaOtpremniceRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		else {
			throw new RuntimeException("StavkeOtpremnice nije pronadjena");
		}
	}

	@Override
	public StavkaOtpremnice save(StavkaOtpremnice stavkaOtpremnice) {
		StavkaOtpremnice dbStavkaOtpremnice = stavkaOtpremniceRepo.save(stavkaOtpremnice);
        otpremnicaService.update(dbStavkaOtpremnice.getOtpremnica());
        return dbStavkaOtpremnice;
	}

	@Override
	public void delete(Long id) {
		Optional<StavkaOtpremnice> result = stavkaOtpremniceRepo.findById(id);
		if(result.isPresent()) {
			StavkaOtpremnice so = result.get();
			so.setObrisano(true);
			
			stavkaOtpremniceRepo.delete(so);
			
//			Otpremnica otpremnica = so.getOtpremnica();
//			otpremnicaService.save(otpremnica);
			
//			stavkaOtpremniceRepo.saveAndFlush(so);
		}
		else {
			throw new RuntimeException("StavkeOtpremnice nije pronadjena");
		}		
	}

	@Override
	public List<StavkaOtpremnice> pronadjiStavkeOtpremnice(long id) {
        return stavkaOtpremniceRepo.findByOtpremnica_id(id);
	}

}
