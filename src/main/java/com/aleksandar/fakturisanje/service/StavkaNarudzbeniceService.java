package com.aleksandar.fakturisanje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleksandar.fakturisanje.model.Narudzbenica;
import com.aleksandar.fakturisanje.model.StavkaNarudzbenice;
import com.aleksandar.fakturisanje.repo.StavkaNarudzbeniceRepository;
import com.aleksandar.fakturisanje.service.interfaces.INarudzbenicaService;
import com.aleksandar.fakturisanje.service.interfaces.IStavkaNarudzbeniceService;

@Service
public class StavkaNarudzbeniceService implements IStavkaNarudzbeniceService {

	@Autowired
	private StavkaNarudzbeniceRepository stavkaNarudzbeniceRepo;
	
	@Autowired
	private INarudzbenicaService narudzbenicaInterface;

	@Override
	public List<StavkaNarudzbenice> findAll() {
		return stavkaNarudzbeniceRepo.findAll();
	}

	@Override
	public List<StavkaNarudzbenice> pronadjiStavkeNarudzbenice(long id) {
		return stavkaNarudzbeniceRepo.findByNarudzbenica_id(id);
	}

	@Override
	public StavkaNarudzbenice findOne(Long id) {
		Optional<StavkaNarudzbenice> result = stavkaNarudzbeniceRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		else {
			throw new RuntimeException("Nije pronadjena StavkaNarudzbenice");
		}
	}

	@Override
	public StavkaNarudzbenice save(StavkaNarudzbenice stavkaNarudzbenice) {
        StavkaNarudzbenice dbStavkaNarudzbenice = stavkaNarudzbeniceRepo.save(stavkaNarudzbenice);
        narudzbenicaInterface.save(dbStavkaNarudzbenice.getNarudzbenica());
        return dbStavkaNarudzbenice;
	}

	@Override
	public void delete(Long id) {
		Optional<StavkaNarudzbenice> result = stavkaNarudzbeniceRepo.findById(id);
		if(result.isPresent()) {
			StavkaNarudzbenice sn =result.get();
			stavkaNarudzbeniceRepo.delete(sn);
//			sn.setObrisano(true);
//			stavkaNarudzbeniceRepo.saveAndFlush(sn);
		}
		else {
			throw new RuntimeException("Nije pronadjena StavkaNarudzbenice");
		}
	}


	

}
