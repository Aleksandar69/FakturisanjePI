package com.aleksandar.fakturisanje.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.aleksandar.fakturisanje.model.Mjesto;
import com.aleksandar.fakturisanje.repo.MjestoRepository;
import com.aleksandar.fakturisanje.service.interfaces.IMjestoService;

@Service
public class MjestoService implements IMjestoService{
	
	@Autowired
	MjestoRepository mjestoRepo;

	@Override
	public Page<Mjesto> findAll(String naziv, int brStranice, int brPrikazanih) {
		return mjestoRepo.findAllByNazivIgnoreCaseContainsAndObrisano(naziv, false,
				PageRequest.of(brStranice, brPrikazanih));
	}

	@Override
	public Mjesto findOne(Long id) {
		Optional<Mjesto> result = mjestoRepo.findById(id);
		if(result.isPresent()) {
			Mjesto mjesto = result.get();
			return mjesto;
		}
		else {
			throw new RuntimeException("Nije pronadjeno mjesto");
		}
	}

	@Override
	public Mjesto save(Mjesto mjesto) {
		mjestoRepo.save(mjesto);
		return mjesto;
	}

	

}
