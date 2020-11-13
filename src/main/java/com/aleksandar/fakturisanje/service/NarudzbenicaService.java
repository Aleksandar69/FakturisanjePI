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
import com.aleksandar.fakturisanje.model.PoslovniPartner;
import com.aleksandar.fakturisanje.model.Preduzece;
import com.aleksandar.fakturisanje.model.StavkaCjenovnika;
import com.aleksandar.fakturisanje.model.StavkaFakture;
import com.aleksandar.fakturisanje.model.StavkaNarudzbenice;
import com.aleksandar.fakturisanje.repo.NarudzbenicaRepository;
import com.aleksandar.fakturisanje.repo.StavkaFaktureRepository;
import com.aleksandar.fakturisanje.service.interfaces.ICjenovnikService;
import com.aleksandar.fakturisanje.service.interfaces.IFakturaService;
import com.aleksandar.fakturisanje.service.interfaces.INarudzbenicaService;
import com.aleksandar.fakturisanje.service.interfaces.IStavkaCjenovnikaService;

@Service
public class NarudzbenicaService implements INarudzbenicaService{

	@Autowired
	private IFakturaService fakturaService;
	
	@Autowired
	private NarudzbenicaRepository narudzbenicaRepo;
	
	@Autowired
	private StavkaFaktureRepository stavkaFaktRepo;
	
	@Autowired
	private IStavkaCjenovnikaService stavkaCjenovnikaService;
	
	@Autowired
	private ICjenovnikService cjenovnikService;


	@Override
	public Narudzbenica findOne(Long id) {
		Optional<Narudzbenica> result = narudzbenicaRepo.findById(id);
		
		if(result.isPresent()) {
			Narudzbenica nar = result.get();
			return nar;
		}
		else {
			return null;
		}
	}

	@Override
	public Narudzbenica save(Narudzbenica narudzbenica) {
		narudzbenicaRepo.save(narudzbenica);
		return narudzbenica;
	}

	@Override
	public Page<Narudzbenica> findAllByNazivPartnera(String nazivPartnera, int brojStranice, int brojPrikazanih) {
		return narudzbenicaRepo.findAllByPoslovniPartner_NazivIgnoreCaseContains(nazivPartnera, PageRequest.of(brojStranice, brojPrikazanih));
	}

	@Override
	public Page<Narudzbenica> findAllByPoslovnaGodinaAndNazivPartnera(String nazivPartnera, long poslovnaGodinaId,
			int brojStranice, int brojPrikazanih) {
		return narudzbenicaRepo.findAllByPoslovnaGodina_IdAndPoslovniPartner_NazivIgnoreCaseContains(poslovnaGodinaId, nazivPartnera, PageRequest.of(brojStranice, brojPrikazanih));
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
        if(narudzbenica.getPoslovniPartner().getVrstaPartnera() == 0) {
           faktura.setVrstaFakture(true);
        }
        else {
            faktura.setVrstaFakture(false);
        }
        faktura.setPreduzece(narudzbenica.getPreduzece());
        faktura.setPoslovnaGodina(narudzbenica.getPoslovnaGodina());
        faktura.setPoslovniPartner(narudzbenica.getPoslovniPartner());
      
        
        Set<StavkaNarudzbenice> stavkeNarudzbenice = narudzbenica.getStavkeNarudzbenice();
        
        
        List<Cjenovnik> cjenovnici = new ArrayList<Cjenovnik>();
        
        System.out.println("Narudzbenica je: " + narudzbenica.isTipNarudzbenice());
        
        if(!(narudzbenica.isTipNarudzbenice())) {
        	List<Cjenovnik >cjenovnici2 = cjenovnikService.findAllByPoslParnterId(narudzbenica.getPoslovniPartner().getId());
        	
        	for (Cjenovnik cjenovnik : cjenovnici2) {
				cjenovnici.add(cjenovnik);
			}
        }
        else {
        	List<Cjenovnik> cjenovnici2 = cjenovnikService.findAllByPreduzeceId(narudzbenica.getPreduzece().getId());	
        	for (Cjenovnik cjenovnik : cjenovnici2) {
				cjenovnici.add(cjenovnik);
			}
        }
        
    //    List<Cjenovnik> cjenovnici = cjenovnikService.findAllByPreduzeceId(narudzbenica.getPreduzece().getId());
        
        List<StavkaCjenovnika> stavkeCjenovnika = new ArrayList<StavkaCjenovnika>();
        
        for (Cjenovnik c : cjenovnici) {
        	for(StavkaCjenovnika s : c.getStavkeCjenovnika()) {
        		stavkeCjenovnika.add(s);
        	}
		}


        Set<StavkaFakture> nadjeneStavke = new HashSet<>();

        for (StavkaNarudzbenice sn : stavkeNarudzbenice) {
        	        	
        	for(StavkaCjenovnika sc : stavkeCjenovnika) {
        		
        		if(sc.getRobaUsluga().getId() == sn.getRobaUsluga().getId()) {

                    StavkaFakture novaStavkaFakture = new StavkaFakture();
                    novaStavkaFakture.setIznosPdva(sc.getCijena() * sn.getKolicina() * (sn.getRobaUsluga().getGrupaRobe().getStopapdva().getProcenat() / 100));
                    novaStavkaFakture.setIznos(sc.getCijena() * sn.getKolicina() * (1+(sn.getRobaUsluga().getGrupaRobe().getStopapdva().getProcenat() / 100)));
                    novaStavkaFakture.setCijena(sc.getCijena());
                    novaStavkaFakture.setKolicina(sn.getKolicina());
                    novaStavkaFakture.setPdvOsnovica(sc.getCijena() * sn.getKolicina());
                    novaStavkaFakture.setObrisano(false);
                    novaStavkaFakture.setRabat(0);
                    novaStavkaFakture.setRobaUsluga(sn.getRobaUsluga());
                    novaStavkaFakture.setPdvProcenat(sn.getRobaUsluga().getGrupaRobe().getStopapdva().getProcenat());
                    novaStavkaFakture.setFaktura(faktura);


                    nadjeneStavke.add(novaStavkaFakture);
        		}
        	}
        }

        faktura.setStavkeFakture(nadjeneStavke);
        fakturaService.update(faktura);
		
	}

	@Override
	public Page<Narudzbenica> findAllByTipAndNazivParntera(boolean tip, String naziv, int brStranice,
			int brPrikazanih) {
		return narudzbenicaRepo.findAllByTipNarudzbeniceAndPoslovniPartner_NazivIgnoreCaseContainsAndObrisanoIsFalse(tip, naziv, PageRequest.of(brStranice, brPrikazanih));

	}

	@Override
	public Page<Narudzbenica> findAllByTipAndNazivParnteraAndGodina(boolean tip, String naziv, int brStranice,
			int brPrikazanih, int godina) {
		return narudzbenicaRepo.findAllByTipNarudzbeniceAndPoslovnaGodina_IdAndPoslovniPartner_NazivIgnoreCaseContainsAndObrisanoIsFalse(tip, godina, naziv,PageRequest.of(brStranice, brPrikazanih));
	}


	
}
