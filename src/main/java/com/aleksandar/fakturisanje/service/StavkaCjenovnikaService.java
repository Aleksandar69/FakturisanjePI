package com.aleksandar.fakturisanje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleksandar.fakturisanje.model.StavkaCjenovnika;
import com.aleksandar.fakturisanje.repo.StavkaCjenovnikaRepository;
import com.aleksandar.fakturisanje.service.interfaces.IStavkaCjenovnikaService;

@Service
public class StavkaCjenovnikaService implements IStavkaCjenovnikaService {

	@Autowired
	private StavkaCjenovnikaRepository stavkaCjRepo;
	
	@Override
	public List<StavkaCjenovnika> findAll() {
		return stavkaCjRepo.findAll();
	}

	@Override
	public StavkaCjenovnika findOne(Long id) {
		Optional<StavkaCjenovnika> result = stavkaCjRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		else {
			throw new RuntimeException("Stavka Cjenovnika nije pronadjena");
		}
	}

	@Override
	public StavkaCjenovnika save(StavkaCjenovnika stavkaCenovnika) {
		stavkaCjRepo.save(stavkaCenovnika);
		return stavkaCenovnika;
	}

	@Override
	public List<StavkaCjenovnika> findAllByRoba_usluga_id(Long id) {
		return stavkaCjRepo.findAllByObrisanoAndRobaUsluga_Id(false, id);
	}

	@Override
	public Boolean delete(Long id) {
		Optional<StavkaCjenovnika> result = stavkaCjRepo.findById(id);
		if(result.isPresent()) {
			StavkaCjenovnika sc = result.get();
			sc.setObrisano(true);
			stavkaCjRepo.saveAndFlush(sc);
			return true;
		}
		else {
			throw new RuntimeException("Stavka Cjenovnika nije pronadjena");
		}
	}

	@Override
	public List<StavkaCjenovnika> findAllByCjenovnikId(long id) {
		return stavkaCjRepo.findAllByCjenovnik_Id(id);
	}

	
	
}
