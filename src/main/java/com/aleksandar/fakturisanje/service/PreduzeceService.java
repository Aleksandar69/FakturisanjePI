package com.aleksandar.fakturisanje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleksandar.fakturisanje.model.Faktura;
import com.aleksandar.fakturisanje.model.Preduzece;
import com.aleksandar.fakturisanje.repo.FakturaRepository;
import com.aleksandar.fakturisanje.repo.PreduzeceRepository;
import com.aleksandar.fakturisanje.service.interfaces.IPreduzeceService;

@Service
public class PreduzeceService implements IPreduzeceService{
	
	@Autowired
	PreduzeceRepository preduzeceRepo;

	@Autowired
	FakturaRepository fakturaRepo;

	@Override
	public List<Preduzece> findAll() {
		return preduzeceRepo.findAll();
	}

	@Override
	public List<Faktura> findAllByPreduzeceAndVrstaFakture(boolean vrstaFakture, long id) {
		return fakturaRepo.findByObrisanoAndAndVrstaFaktureAndPreduzece_Id(false, vrstaFakture, id);
	}

	@Override
	public List<Faktura> findAllByPreduzeceAndVrstaFaktureAndPlaceno(boolean vrstaFakture, long id, boolean placeno) {
		return fakturaRepo.findByObrisanoAndAndVrstaFaktureAndPreduzece_IdAndPlaceno(false, vrstaFakture, id, placeno);
	}

	@Override
	public Preduzece findOne(Long id) {
		Optional<Preduzece> result = preduzeceRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		else {
			throw new RuntimeException("Preduzece nije pronadjeno");
		}
	}

	@Override
	public Preduzece save(Preduzece preduzece) {
		preduzeceRepo.save(preduzece);
		return preduzece;
	}

	@Override
	public Boolean delete(Long id) {
		Optional<Preduzece> result = preduzeceRepo.findById(id);
		if(result.isPresent()) {
			Preduzece p = result.get();
			p.setObrisano(true);
			preduzeceRepo.saveAndFlush(p);
			return true;
		}
		else {
			throw new RuntimeException("Preduzece nije pronadjeno");
		}
	}

	
	
	
}
