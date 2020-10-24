package com.aleksandar.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.aleksandar.fakturisanje.model.Cjenovnik;
import com.aleksandar.fakturisanje.model.StavkaCjenovnika;
import com.aleksandar.fakturisanje.repo.CjenovnikRepository;
import com.aleksandar.fakturisanje.repo.StavkaCjenovnikaRepository;
import com.aleksandar.fakturisanje.service.interfaces.ICjenovnikService;

public class CjenovnikService implements ICjenovnikService{
	
	@Autowired
	private CjenovnikRepository cjenovnikRepo;
	
	@Autowired
	private StavkaCjenovnikaRepository stavkaCjenovnikaRepo;
	

	@Override
	public Page<Cjenovnik> findAll(int brStranice, int brPrikazanih) {
		return cjenovnikRepo.findAllByObrisano(false, PageRequest.of(brStranice, brPrikazanih));
	}

	@Override
	public Page<StavkaCjenovnika> findAllByCjenovnikId(long id, String nazivRobeUsluge, int brStranice,
			int brPrikazanih) {
		return stavkaCjenovnikaRepo.findAllByObrisanoAndCenovnik_IdAndRobaUsluga_NazivRobeUslugeIgnoreCaseContains(false, id, nazivRobeUsluge, PageRequest.of(brStranice, brPrikazanih));
	}

	@Override
	public List<Cjenovnik> findAllById(long id) {
		return cjenovnikRepo.findByIdNot(id);
	}

	@Override
	public Cjenovnik findOne(Long id) {
		return cjenovnikRepo.findByObrisanoAndId(false, id);
	}

	@Override
	public Cjenovnik save(Cjenovnik cjenovnik) {
		cjenovnikRepo.save(cjenovnik);
		return cjenovnik;
	}

	@Override
	public Boolean delete(long id) {
		Optional<Cjenovnik> result  = cjenovnikRepo.findById(id);
		Cjenovnik cjenovnik =  null;
		if(result.isPresent()) {
			cjenovnik = result.get();
		}
		else {
			throw new RuntimeException("Cjenovnik nije pronadjen");
		}
		
		cjenovnik.setDeleted(true);
		cjenovnikRepo.saveAndFlush(cjenovnik);
		return true;
	}

	@Override
	public void kopirajCjenovnik(Cjenovnik ciljani, Cjenovnik izvorni) {

		Set<StavkaCjenovnika> stavkeCjenovnika = izvorni.getStavkeCjenovnika();
		
		for(StavkaCjenovnika sc : stavkeCjenovnika) {
			StavkaCjenovnika novaStavka = new StavkaCjenovnika();
			novaStavka.setCijena(sc.getCijena());
			novaStavka.setObrisano(sc.isObrisano());
			novaStavka.setRobaUsluga(sc.getRobaUsluga());
			novaStavka.setCjenovnik(ciljani);
			
			ciljani.getStavkeCjenovnika().add(novaStavka);
			stavkaCjenovnikaRepo.save(novaStavka);
		}
		
		cjenovnikRepo.save(ciljani);
	}
	
	

}
