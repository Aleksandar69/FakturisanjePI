package com.aleksandar.fakturisanje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleksandar.fakturisanje.model.StopaPDV;
import com.aleksandar.fakturisanje.repo.StopaPdvRepository;
import com.aleksandar.fakturisanje.service.interfaces.IStopaPDVa;

@Service
public class StopaPdvService implements IStopaPDVa{

	@Autowired
	StopaPdvRepository stopaPdvRepo;
	
	
	@Override
	public List<StopaPDV> findAll() {
        return stopaPdvRepo.findAllByObrisano(false);

	}

	@Override
	public StopaPDV findOne(Long id) {
		Optional<StopaPDV> result = stopaPdvRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		else {
			throw new RuntimeException("StopaPdv nije pronadjena");
		}
	}

	@Override
	public StopaPDV save(StopaPDV stopaPDV) {
		stopaPdvRepo.save(stopaPDV);
		return stopaPDV;
	}

	@Override
	public Boolean delete(Long id) {
		Optional<StopaPDV> result = stopaPdvRepo.findById(id);
		if(result.isPresent()) {
			StopaPDV stopa = result.get();
			stopa.setObrisano(true);
			stopaPdvRepo.saveAndFlush(stopa);
			return true;
		}
		else {
			throw new RuntimeException("StopaPdv nije pronadjena");
		}
	}

	
	
}
