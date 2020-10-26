package com.aleksandar.fakturisanje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleksandar.fakturisanje.model.StavkaFakture;
import com.aleksandar.fakturisanje.repo.StavkaFaktureRepository;
import com.aleksandar.fakturisanje.service.interfaces.IFakturaService;
import com.aleksandar.fakturisanje.service.interfaces.IStavkaFaktureService;

@Service
public class StavkaFaktureService implements IStavkaFaktureService{

	@Autowired
	StavkaFaktureRepository stavkaFaktureRepo;
	
	@Autowired
	IFakturaService fakturaService;
	
	@Override
	public List<StavkaFakture> findAll() {
		return stavkaFaktureRepo.findAll();
	}

	@Override
	public List<StavkaFakture> findByFaktura_id(Long id) {
		return stavkaFaktureRepo.findByFaktura_id(id);
	}

	@Override
	public StavkaFakture findOne(Long id) {
		Optional<StavkaFakture> result = stavkaFaktureRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		else {
			throw new RuntimeException("Stavka nije pronadjena");
		}
	}

	@Override
	public StavkaFakture save(StavkaFakture stavkaFakture) {
		stavkaFaktureRepo.save(stavkaFakture);
		fakturaService.update(stavkaFakture.getFaktura());
		return stavkaFakture;
	}

	@Override
	public Boolean delete(Long id) {
		Optional<StavkaFakture> result = stavkaFaktureRepo.findById(id);
		if(result.isPresent()) {
			StavkaFakture sf = result.get();
			sf.setObrisano(true);
			stavkaFaktureRepo.saveAndFlush(sf);
			return true;
		}
		else {
			throw new RuntimeException("Stavka nije pronadjena");
		}
	}

	
	
	
}
