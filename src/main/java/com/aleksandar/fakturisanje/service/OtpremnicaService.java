package com.aleksandar.fakturisanje.service;

import java.util.ArrayList;
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

import com.aleksandar.fakturisanje.model.Cjenovnik;
import com.aleksandar.fakturisanje.model.Faktura;
import com.aleksandar.fakturisanje.model.Narudzbenica;
import com.aleksandar.fakturisanje.model.Otpremnica;
import com.aleksandar.fakturisanje.model.StavkaCjenovnika;
import com.aleksandar.fakturisanje.model.StavkaFakture;
import com.aleksandar.fakturisanje.model.StavkaNarudzbenice;
import com.aleksandar.fakturisanje.model.StavkaOtpremnice;
import com.aleksandar.fakturisanje.repo.FakturaRepository;
import com.aleksandar.fakturisanje.repo.OtpremnicaRepository;
import com.aleksandar.fakturisanje.repo.StavkaCjenovnikaRepository;
import com.aleksandar.fakturisanje.repo.StavkaFaktureRepository;
import com.aleksandar.fakturisanje.service.interfaces.ICjenovnikService;
import com.aleksandar.fakturisanje.service.interfaces.IFakturaService;
import com.aleksandar.fakturisanje.service.interfaces.IOtpremnicaService;
import com.aleksandar.fakturisanje.service.interfaces.IStavkaCjenovnikaService;

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

	@Autowired
	private IStavkaCjenovnikaService stavkaCjenovnikaService;
	
	@Autowired
	private ICjenovnikService cjenovnikService;
	
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
            
        Set<StavkaFakture> nadjeneStavke = new HashSet<>();
        
        for(StavkaOtpremnice so : stavkeOtpremnice) {
    			StavkaFakture novaStavkaFakture = new StavkaFakture();
                novaStavkaFakture.setIznosPdva(so.getIznos()* (so.getRobaUsluga().getGrupaRobe().getStopapdva().getProcenat() / 100));
                novaStavkaFakture.setIznos(so.getIznos() * (1+(so.getRobaUsluga().getGrupaRobe().getStopapdva().getProcenat() / 100)));
                novaStavkaFakture.setCijena(so.getCijena());
                novaStavkaFakture.setKolicina(so.getKolicina());
                novaStavkaFakture.setPdvOsnovica(so.getIznos());
                novaStavkaFakture.setRabat(0);
                novaStavkaFakture.setObrisano(false);
                novaStavkaFakture.setRobaUsluga(so.getRobaUsluga());
                novaStavkaFakture.setPdvProcenat(so.getRobaUsluga().getGrupaRobe().getStopapdva().getProcenat());
                novaStavkaFakture.setFaktura(faktura);

                nadjeneStavke.add(novaStavkaFakture);
        }

        faktura.setStavkeFakture(nadjeneStavke);
        fakturaService.update(faktura);
	}

	@Override
	public void napraviOtpremnicuOdNarudzbenice(Narudzbenica narudzbenica) {

        List<Otpremnica> listaOtpremnica = otpremnicaRepo.findAll();
        
        //narudzbenica.setTipNarudzbenice(false);
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
   
        
        List<Cjenovnik> cjenovnik = cjenovnikService.findAllByPreduzeceId(1);
        
        Set<StavkaNarudzbenice> stavkeNarudzbenice = narudzbenica.getStavkeNarudzbenice();
        Set<StavkaOtpremnice> stavkeOtpremnice = new HashSet<>();
        List<StavkaCjenovnika> stavkeCjenovnika = new ArrayList<StavkaCjenovnika>();
        
        for (Cjenovnik c : cjenovnik) {
        	for(StavkaCjenovnika s : c.getStavkeCjenovnika()) {
        		stavkeCjenovnika.add(s);
        	}
		}




        for (StavkaNarudzbenice stavkaNarudzbenice : stavkeNarudzbenice) {
        	
        	
        	for(StavkaCjenovnika sc : stavkeCjenovnika) {
        		
        		if(sc.getRobaUsluga().getId() == stavkaNarudzbenice.getRobaUsluga().getId()) {
        	
                    StavkaOtpremnice stavkaOtpremnice = new StavkaOtpremnice();
                    stavkaOtpremnice.setJedinicaMjere(stavkaNarudzbenice.getJedinicaMjere());
                    stavkaOtpremnice.setKolicina(stavkaNarudzbenice.getKolicina());
                    stavkaOtpremnice.setRobaUsluga(stavkaNarudzbenice.getRobaUsluga());
                    stavkaOtpremnice.setCijena(sc.getCijena());
                    stavkaOtpremnice.setIznos(stavkaNarudzbenice.getKolicina() * sc.getCijena());
                    stavkaOtpremnice.setOtpremnica(otpremnica);
                    stavkaOtpremnice.setObrisano(false);
                    stavkaOtpremnice.setOpis(stavkaNarudzbenice.getOpis());

                    stavkeOtpremnice.add(stavkaOtpremnice);
        			}
        		}
        }

        otpremnica.setStavkeOtpremnice(stavkeOtpremnice);
        update(otpremnica);
		
	}

	
	
}
