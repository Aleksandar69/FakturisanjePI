package com.aleksandar.fakturisanje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.aleksandar.fakturisanje.model.GrupaRobe;
import com.aleksandar.fakturisanje.repo.GrupaRobeRepository;
import com.aleksandar.fakturisanje.service.interfaces.IGrupaRobeService;

@Service
public class GrupaRobeService implements IGrupaRobeService{
	
	@Autowired
	GrupaRobeRepository grupaRobeRepo;

	@Override
	public Page<GrupaRobe> finadAll(String naziv, int brStranice, int brPrikazanih) {

		return grupaRobeRepo.findAllByObrisanoAndNazivIgnoreCaseContains(false, naziv, 
				PageRequest.of(brStranice, brPrikazanih));
	}

	@Override
	public GrupaRobe findOne(Long id) {
		Optional<GrupaRobe> result = grupaRobeRepo.findById(id);
		if(result.isPresent()) {
			GrupaRobe gr = result.get();
			return gr;
		}
		else {
			throw new RuntimeException("GrupaRobe sa tim idom ne postoji");
		}
	}

	@Override
	public List<GrupaRobe> findByPreduzece_id(Long id) {
		return grupaRobeRepo.findAllByPreduzece_idAndObrisano(id, false);
	}

	@Override
	public GrupaRobe save(GrupaRobe grupaRobe) {
		grupaRobeRepo.save(grupaRobe);
		return grupaRobe;
	}

	@Override
	public Boolean delete(Long id) {
		Optional<GrupaRobe> result = grupaRobeRepo.findById(id);
		if(result.isPresent()) {
			GrupaRobe gr = result.get();
			gr.setObrisano(true);
			grupaRobeRepo.saveAndFlush(gr);
			return true;
		}
		else {
			throw new RuntimeException("GrupaRobe sa tim idom ne postoji");
		}
	}

	
	
}
