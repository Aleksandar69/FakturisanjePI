package com.aleksandar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.aleksandar.fakturisanje.model.Faktura;
import com.aleksandar.fakturisanje.model.StavkaFakture;
import com.aleksandar.fakturisanje.repo.FakturaRepository;
import com.aleksandar.fakturisanje.service.interfaces.IFakturaService;

public class FakturaService implements IFakturaService{
	
	@Autowired
	FakturaRepository fakturaRepo;

	@Override
	public List<Faktura> findAll() {
		return fakturaRepo.findAll();
	}

	@Override
	public Faktura findOne(Long id) {
		Optional<Faktura> result =  fakturaRepo.findById(id);
		if(result.isPresent()) {
			Faktura faktura = result.get();
			return faktura;
		}
		else {
			throw new RuntimeException("Faktura nije pronadjena");
		}
	}

	@Override
	public Faktura save(Faktura faktura) {
		fakturaRepo.save(faktura);
		return faktura;
	}

	@Override
	public Boolean delete(Long id) {
		Optional<Faktura> result = fakturaRepo.findById(id);
		if(result.isPresent()) {
			Faktura faktura = result.get();
			faktura.setObrisano(true);
			fakturaRepo.saveAndFlush(faktura);
			return true;
		}
		else {
			throw new RuntimeException("Faktura nije pronadjena");
		}
		
	}

	@Override
	public void update(Faktura faktura) {
		double osnovica = 0;
		double ukupanPdv = 0;
		double iznosZaPlacanje = 0;
		double iznosBezRabata = 0;
		double  rabat = 0;
		for(StavkaFakture sf : faktura.getStavkeFakture()) {
			rabat += sf.getRabat();
			iznosBezRabata += sf.getKolicina() * sf.getCijena();
			osnovica += sf.getPdvOsnovica();
			ukupanPdv += sf.getIznosPdva();
			iznosZaPlacanje += sf.getIznos();
		}
		
		faktura.setIznosZaPlacanje(iznosZaPlacanje);
		faktura.setOsnovica(osnovica);
		faktura.setUkupanPdv(ukupanPdv);
		faktura.setIznosBezRabata(iznosBezRabata);
		faktura.setRabat(rabat);
		save(faktura);
		
	}

	@Override
	public Page<Faktura> findAllByVrstaFaktureAndNazivPartnera(boolean vrstaFakture, String nazivPartnera,
			int brojStranice, int brojPrikazanih) {
		return fakturaRepo.findAllByVrstaFaktureAndPoslovniPartner_NazivPartneraIgnoreCaseContainsAndObrisanoIsFalse(vrstaFakture, nazivPartnera, 
				PageRequest.of(brojStranice, brojPrikazanih));
	}

	@Override
	public Page<Faktura> findAllByVrstaFaktureAndPoslovnaGodinaAndNazivPartnera(boolean vrstaFakture,
			String nazivPartnera, long poslovnaGodinaId, int brojStranice, int brojPrikazanih) {
		return fakturaRepo.findAllByVrstaFaktureAndPoslovnaGodina_IdAndPoslovniPartner_NazivPartneraIgnoreCaseContainsAndObrisanoIsFalse(vrstaFakture, 
				poslovnaGodinaId, nazivPartnera, PageRequest.of(brojStranice, brojPrikazanih));
	}

}
