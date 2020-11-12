package com.aleksandar.fakturisanje.service.interfaces;

import org.springframework.data.domain.Page;

import com.aleksandar.fakturisanje.model.Narudzbenica;

public interface INarudzbenicaService {
	
	Narudzbenica findOne(Long id);

	Narudzbenica save(Narudzbenica narudzbenica);
	
    Page<Narudzbenica> findAllByNazivPartnera(String nazivPartnera, int brojStranice, int brojPrikazanih);

    Page<Narudzbenica> findAllByPoslovnaGodinaAndNazivPartnera(String nazivPartnera, long poslovnaGodinaId, int brojStranice, int brojPrikazanih);

    void napraviFakturuOdNarudzbenice(Narudzbenica narudzbenica, int poslednjaPoslovnaGodina);
    
    Page<Narudzbenica> findAllByTipAndNazivParntera(boolean tip, String naziv, int brStranice, int brPrikazanih);
    
    Page<Narudzbenica> findAllByTipAndNazivParnteraAndGodina(boolean tip, String naziv, int brStranice, int brPrikazanih, int godina);


	 
	 
	
}
