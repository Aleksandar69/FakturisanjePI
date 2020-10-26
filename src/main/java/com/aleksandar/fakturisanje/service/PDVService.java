package com.aleksandar.fakturisanje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleksandar.fakturisanje.model.PDV;
import com.aleksandar.fakturisanje.repo.PdvRepository;
import com.aleksandar.fakturisanje.service.interfaces.IPDVService;

@Service
public class PDVService implements IPDVService {
	
	@Autowired
	PdvRepository pdvRepo;

	@Override
	public List<PDV> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PDV findOne(Long id) {
		Optional<PDV> result = pdvRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		else {
			throw new RuntimeException("Pdv nije pronadjen");
		}
	}

	@Override
	public PDV save(PDV pdv) {
		pdvRepo.save(pdv);
		return pdv;
	}

	@Override
	public Boolean delete(Long id) {
		Optional<PDV> result = pdvRepo.findById(id);
		if(result.isPresent()) {
			PDV pdv = result.get();
			pdv.setObrisano(true);
			pdvRepo.saveAndFlush(pdv);
			return true;
		}
		else {
			throw new RuntimeException("Pdv nije pronadjen");
		}
	}

	
}
