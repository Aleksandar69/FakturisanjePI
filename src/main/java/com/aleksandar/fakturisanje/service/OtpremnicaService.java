package com.aleksandar.fakturisanje.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.aleksandar.fakturisanje.model.Faktura;
import com.aleksandar.fakturisanje.model.Narudzbenica;
import com.aleksandar.fakturisanje.model.Otpremnica;
import com.aleksandar.fakturisanje.model.StavkaFakture;
import com.aleksandar.fakturisanje.model.StavkaNarudzbenice;
import com.aleksandar.fakturisanje.model.StavkaOtpremnice;
import com.aleksandar.fakturisanje.repo.FakturaRepository;
import com.aleksandar.fakturisanje.repo.OtpremnicaRepository;
import com.aleksandar.fakturisanje.repo.StavkaFaktureRepository;
import com.aleksandar.fakturisanje.service.interfaces.IFakturaService;
import com.aleksandar.fakturisanje.service.interfaces.IOtpremnicaService;

@Service
public class OtpremnicaService implements IOtpremnicaService {

	@Autowired
	private OtpremnicaRepository otpremnicaRepo;
	
	@Autowired
	private NarudzbenicaService narudzbenicaService;
	
	@Autowired
	private FakturaRepository fakturaRepo;
	
	@Autowired
	private IFakturaService fakturaService;
	
	@Autowired
	private StavkaFaktureRepository stavkaFaktureRepo;
	
	@Override
	public Otpremnica findOne(Long id) {
		Optional<Otpremnica> result = otpremnicaRepo.findById(id);
		if(result.isPresent()) {
			Otpremnica o = result.get();
			return o;
		}
		else {
			throw new RuntimeException("Nije pronadjena otpremnica");
		}
	}

	@Override
	public Otpremnica save(Otpremnica otpremnica) {
		otpremnicaRepo.save(otpremnica);
		return otpremnica;
	}

	@Override
	public void update(Otpremnica otpremnica) {
		double iznosZaPlacanje = 0;
		
		for(StavkaOtpremnice so : otpremnica.getStavkeOtpremnice()) {
			if(!so.isObrisano()) {
				iznosZaPlacanje += so.getIznos();
			}
		}
		otpremnica.setIznosZaPlacanje(iznosZaPlacanje);
		save(otpremnica);
	}

	@Override
	public Page<Otpremnica> findAllByNazivPartnera(String nazivPartnera, int brojStranice, int brojPrikazanih) {
		return otpremnicaRepo.findAllByPoslovniPartner_NazivIgnoreCaseContains(nazivPartnera, PageRequest.of(brojStranice, brojPrikazanih));
	}

	@Override
	public Page<Otpremnica> findAllByPoslovnaGodinaAndNazivPartnera(String nazivPartnera, long poslovnaGodinaId,
			int brojStranice, int brojPrikazanih) {
		return otpremnicaRepo.findAllByPoslovnaGodina_IdAndPoslovniPartner_NazivIgnoreCaseContains(poslovnaGodinaId, nazivPartnera, PageRequest.of(brojStranice, brojPrikazanih));
	}

	@Override
	public void napraviFakturuOdOtpremnice(Otpremnica otpremnica, int poslednjaPoslovnjaGodina) {
	 	otpremnica.setObrisano(true);
    	save(otpremnica);

        Date datumValute = DateUtils.addDays(otpremnica.getDatumOtpremnice(), 60);

        Faktura faktura = new Faktura();
        faktura.setBrojFakture(poslednjaPoslovnjaGodina + 1);
        faktura.setDatumFakture(new Date());
        faktura.setDatumValute(datumValute);
        faktura.setPlaceno(true);
        faktura.setVrstaFakture(false);
        faktura.setPreduzece(otpremnica.getPreduzece());
        faktura.setPoslovnaGodina(otpremnica.getPoslovnaGodina());
        faktura.setPoslovniPartner(otpremnica.getPoslovniPartner());
        
        // naci id robe usluga iz narudzbenice
        Set<StavkaOtpremnice> stavkeOtpremnice = otpremnica.getStavkeOtpremnice();

        //povuci sve stavke fakture iz baze i naci da se poklapaju idijevi
        List<StavkaFakture> stavkeFakture = stavkaFaktureRepo.findDistinctStavkeFakture();

        Set<StavkaFakture> nadjeneStavke = new HashSet<>();

        for (StavkaOtpremnice stavkaOtpremnice : stavkeOtpremnice) {
            for (StavkaFakture stavkaFakture : stavkeFakture) {
                long stavkaNarudzbeniceId = stavkaOtpremnice.getRobaUsluga().getId();
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

	@Override
	public void napraviOtpremnicuOdNarudzbenice(Narudzbenica narudzbenica) {

        List<Otpremnica> listaOtpremnica = otpremnicaRepo.findAll();
        
        narudzbenica.setTipNarudzbenice(false);
        narudzbenica.setObrisano(true);
        narudzbenicaService.save(narudzbenica);

        Otpremnica otpremnica = new Otpremnica();
        otpremnica.setBrojOtpremnice(listaOtpremnica.size() + 1);
        otpremnica.setDatumOtpremnice(new Date());
        otpremnica.setPreduzece(narudzbenica.getPreduzece());
        otpremnica.setPoslovnaGodina(narudzbenica.getPoslovnaGodina());
        otpremnica.setPoslovniPartner(narudzbenica.getPoslovniPartner());
        otpremnica.setTipOtpremnice(false);
        otpremnica.setObrisano(false);

        Set<StavkaNarudzbenice> stavkeNarudzbenice = narudzbenica.getStavkeNarudzbenice();
        List<StavkaFakture> stavkeFakture = stavkaFaktureRepo.findDistinctStavkeFakture();
        Set<StavkaOtpremnice> stavkeOtpremnice = new HashSet<>();

        for (StavkaNarudzbenice stavkaNarudzbenice : stavkeNarudzbenice) {
            for (StavkaFakture stavkaFakture : stavkeFakture) {

                long stavkaNarudzbeniceId = stavkaNarudzbenice.getRobaUsluga().getId();
                long stavkaFaktureId = stavkaFakture.getRobaUsluga().getId();
                if (stavkaFaktureId == stavkaNarudzbeniceId ) {

                    StavkaOtpremnice stavkaOtpremnice = new StavkaOtpremnice();
                    stavkaOtpremnice.setJedinicaMjere(stavkaNarudzbenice.getJedinicaMjere());
                    stavkaOtpremnice.setKolicina(stavkaNarudzbenice.getKolicina());
                    stavkaOtpremnice.setRobaUsluga(stavkaNarudzbenice.getRobaUsluga());
                    stavkaOtpremnice.setCijena(stavkaFakture.getCijena());
                    stavkaOtpremnice.setIznos(stavkaNarudzbenice.getKolicina() * stavkaFakture.getCijena());
                    stavkaOtpremnice.setOtpremnica(otpremnica);
                    stavkaOtpremnice.setObrisano(false);

                    stavkeOtpremnice.add(stavkaOtpremnice);
                }
            }
        }

        otpremnica.setStavkeOtpremnice(stavkeOtpremnice);
        update(otpremnica);
		
	}

	
	
}
