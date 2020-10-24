package com.aleksandar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.aleksandar.fakturisanje.model.PoslovnaGodina;
import com.aleksandar.fakturisanje.repo.PoslovnagodinaRepository;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovnaGodinaService;

public class PoslovnaGodinaService implements IPoslovnaGodinaService {

	@Autowired
	PoslovnagodinaRepository poslovnaGodRepo;
	
	@Override
	public List<PoslovnaGodina> findAll() {
		return poslovnaGodRepo.findAll();
	}

	@Override
	public PoslovnaGodina findOne(Long id) {
		Optional<PoslovnaGodina> result = poslovnaGodRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		else {
			throw new RuntimeException("Poslovna godina nije pronadjena");
		}

	}

	@Override
	public PoslovnaGodina save(PoslovnaGodina poslovnaGodina) {
		poslovnaGodRepo.save(poslovnaGodina);
		return poslovnaGodina;
	}

	@Override
	public Boolean delete(Long id) {
		Optional<PoslovnaGodina> result = poslovnaGodRepo.findById(id);
		if(result.isPresent()) {
			PoslovnaGodina pg = result.get();
			pg.setObrisano(true);
			poslovnaGodRepo.saveAndFlush(pg);
			return true;
		}
		else {
			throw new RuntimeException("Poslovna godina nije pronadjena");
		}
	}

	@Override
	public PoslovnaGodina findPoslovnaGodinaIsFalse() {
		return poslovnaGodRepo.findByZakljucanaIsFalseAndObrisanoIsFalse().get(0);
	}

	
}
