package com.aleksandar.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.aleksandar.fakturisanje.model.Cjenovnik;
import com.aleksandar.fakturisanje.model.PoslovniPartner;
import com.aleksandar.fakturisanje.repo.PoslovniPartnerRepository;
import com.aleksandar.fakturisanje.service.interfaces.IPoslovniPartnerService;

public class PoslovniPartnerService implements IPoslovniPartnerService {

	@Autowired
	PoslovniPartnerRepository poslovniPartnerRepo;
	
	@Override
	public List<PoslovniPartner> findAll(String filter) {
		return poslovniPartnerRepo.findAllByNazivPartneraIgnoreCaseContainsOrAdresaIgnoreCaseContainsOrMesto_NazivIgnoreCaseContainsAndObrisano(filter, filter, filter, false);
	}

	@Override
	public List<PoslovniPartner> findAll() {
		return poslovniPartnerRepo.findAllByObrisano(false);
	}

	@Override
	public List<PoslovniPartner> findByPreduzece_id(Long id) {
		return poslovniPartnerRepo.findByPreduzece_id(id);
	}

	@Override
	public PoslovniPartner findOne(Long id) {
		Optional<PoslovniPartner> result = poslovniPartnerRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		else {
			throw new RuntimeException("Poslovni Partner nije pronadjen");
		}
	}

	@Override
	public PoslovniPartner save(PoslovniPartner poslovniPartner) {
		poslovniPartnerRepo.save(poslovniPartner);
		return poslovniPartner;
	}

	@Override
	public Boolean delete(Long id) {
		Optional<PoslovniPartner> result = poslovniPartnerRepo.findById(id);
		if(result.isPresent()) {
			PoslovniPartner pp = result.get();
			pp.setObrisano(true);
			poslovniPartnerRepo.saveAndFlush(pp);
			return true;
		}
		else {
			throw new RuntimeException("Poslovni Partner nije pronadjen");
		}
	}

	@Override
	public PoslovniPartner findPartner(Cjenovnik cjenovnik) {
		List<PoslovniPartner> partneri = poslovniPartnerRepo.findAllByObrisano(false)
                .stream().filter(p -> p.getCjenovnici().contains(cjenovnik)).collect(Collectors.toList());
    	return partneri.get(partneri.size()-1);
	}

	
	
}
