package com.aleksandar.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.aleksandar.fakturisanje.model.Faktura;
import com.aleksandar.fakturisanje.model.Narudzbenica;
import com.aleksandar.fakturisanje.model.StavkaFakture;
import com.aleksandar.fakturisanje.model.StavkaNarudzbenice;
import com.aleksandar.fakturisanje.repo.NarudzbenicaRepository;
import com.aleksandar.fakturisanje.repo.StavkaFaktureRepository;
import com.aleksandar.fakturisanje.service.interfaces.IFakturaService;
import com.aleksandar.fakturisanje.service.interfaces.INarudzbenicaService;

public class NarudzbenicaService implements INarudzbenicaService{

	@Autowired
	private IFakturaService fakturaService;
	
	@Autowired
	private NarudzbenicaRepository narudzbenicaRepo;
	
	@Autowired
	private StavkaFaktureRepository stavkaFaktRepo;

	@Override
	public Narudzbenica findOne(Long id) {
		Optional<Narudzbenica> result = narudzbenicaRepo.findById(id);
		
		if(result.isPresent()) {
			Narudzbenica nar = result.get();
			return nar;
		}
		else {
			throw new RuntimeException("Nije pronadjena narudzbenica");
		}
	}

	@Override
	public Narudzbenica save(Narudzbenica narudzbenica) {
		narudzbenicaRepo.save(narudzbenica);
		return narudzbenica;
	}

	@Override
	public Page<Narudzbenica> findAllByNazivPartnera(String nazivPartnera, int brojStranice, int brojPrikazanih) {
		return narudzbenicaRepo.findAllByPoslovniPartner_NazivPartneraIgnoreCaseContains(nazivPartnera, PageRequest.of(brojStranice, brojPrikazanih));
	}

	@Override
	public Page<Narudzbenica> findAllByPoslovnaGodinaAndNazivPartnera(String nazivPartnera, long poslovnaGodinaId,
			int brojStranice, int brojPrikazanih) {
		return narudzbenicaRepo.findAllByPoslovnaGodina_IdAndPoslovniPartner_NazivPartneraIgnoreCaseContains(poslovnaGodinaId, nazivPartnera, PageRequest.of(brojStranice, brojPrikazanih));
	}

	@Override
	public void napraviFakturuOdNarudzbenice(Narudzbenica narudzbenica, int poslovnaGodina) {
    	narudzbenica.setObrisano(true);
    	save(narudzbenica);

        Date datumValute = DateUtils.addDays(narudzbenica.getDatumNarudzbenice(), 60);

        Faktura faktura = new Faktura();
        faktura.setBrojFakture(poslovnaGodina + 1);
        faktura.setDatumFakture(new Date());
        faktura.setDatumValute(datumValute);
        faktura.setPlaceno(true);
        faktura.setVrstaFakture(true);
        faktura.setPreduzece(narudzbenica.getPreduzece());
        faktura.setPoslovnaGodina(narudzbenica.getPoslovnaGodina());
        faktura.setPoslovniPartner(narudzbenica.getPoslovniPartner());
        
        // naci id robe usluga iz narudzbenice
        Set<StavkaNarudzbenice> stavkeNarudzbenice = narudzbenica.getStavkeNarudzbenice();

        //povuci sve stavke fakture iz baze i naci da se poklapaju idijevi
        List<StavkaFakture> stavkeFakture = stavkaFaktRepo.findDistinctStavkeFakture();

        Set<StavkaFakture> nadjeneStavke = new HashSet<>();

        for (StavkaNarudzbenice stavkaNarudzbenice : stavkeNarudzbenice) {
            for (StavkaFakture stavkaFakture : stavkeFakture) {
                long stavkaNarudzbeniceId = stavkaNarudzbenice.getRobaUsluga().getId();
                long stavkaFaktureId = stavkaFakture.getRobaUsluga().getId();
                if (stavkaFaktureId == stavkaNarudzbeniceId ) {

                    StavkaFakture novaStavkaFakture = new StavkaFakture();
                    novaStavkaFakture.setIznosPdva(stavkaFakture.getIznosPdva());
                    novaStavkaFakture.setIznos(stavkaFakture.getIznos());
                    novaStavkaFakture.setCijena(stavkaFakture.getCijena());
                    novaStavkaFakture.setKolicina(stavkaFakture.getKolicina());
                    novaStavkaFakture.setObrisano(stavkaFakture.isObrisano());
                    novaStavkaFakture.setPdvOsnovica(stavkaFakture.getPdvOsnovica());
                    novaStavkaFakture.setRabat(stavkaFakture.getRabat());
                    novaStavkaFakture.setRobaUsluga(stavkaFakture.getRobaUsluga());
                    novaStavkaFakture.setPdvProcenat(stavkaFakture.getPdvProcenat());
                    novaStavkaFakture.setFaktura(faktura);

                    nadjeneStavke.add(novaStavkaFakture);
                }
            }
        }

        faktura.setStavkeFakture(nadjeneStavke);
        fakturaService.update(faktura);
		
	}
	
}
