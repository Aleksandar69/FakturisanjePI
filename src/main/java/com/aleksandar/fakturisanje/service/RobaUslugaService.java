package com.aleksandar.fakturisanje.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.aleksandar.fakturisanje.model.RobaUsluga;
import com.aleksandar.fakturisanje.repo.RobaUslugaRepository;
import com.aleksandar.fakturisanje.service.interfaces.IRobaUslugaService;

@Service
public class RobaUslugaService implements IRobaUslugaService {

	@Autowired
	RobaUslugaRepository robaUslugaRepo;
	
	@Override
	public Page<RobaUsluga> findAll(String naziv, int brojStanice, int brojPrikazanih) {
		return robaUslugaRepo.findAllByObrisanoAndNazivIgnoreCaseContains(false, naziv, PageRequest.of(brojStanice, brojPrikazanih));
	}

	@Override
	public RobaUsluga findOne(Long id) {
		Optional<RobaUsluga> result = robaUslugaRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		else {
			throw new RuntimeException("Nije Pronadjena RopbaUsluga");
		}
		
	}

	@Override
	public RobaUsluga save(RobaUsluga robaUsluga) {
		robaUslugaRepo.save(robaUsluga);
		return robaUsluga;
	}

	@Override
	public Boolean delete(Long id) {
	
		Optional<RobaUsluga> result = robaUslugaRepo.findById(id);
		if(result.isPresent()) {
			RobaUsluga ru = result.get();
			ru.setObrisano(true);
			robaUslugaRepo.saveAndFlush(ru);
			return true;
		}
		else {
			throw new RuntimeException("Nije Pronadjena RopbaUsluga");
		}
	}

	@Override
	public Page<RobaUsluga> findAllByGrupaRobe_id(Long id, String naziv, int brojStanice, int brojPrikazanih) {
		return robaUslugaRepo.findAllByGrupaRobe_idAndObrisanoAndNazivIgnoreCaseContains(id, false, naziv, PageRequest.of(brojStanice, brojPrikazanih));
	}

}
