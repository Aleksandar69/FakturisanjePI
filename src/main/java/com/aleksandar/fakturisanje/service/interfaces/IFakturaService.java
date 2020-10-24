package com.aleksandar.fakturisanje.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.aleksandar.fakturisanje.model.Faktura;

public interface IFakturaService {
	
	List<Faktura> findAll();
    Faktura findOne(Long id);
    Faktura save(Faktura faktura);
    Boolean delete(Long id);
    void update(Faktura faktura);
	
    Page<Faktura> findAllByVrstaFaktureAndNazivPartnera(
            boolean vrstaFakture, String nazivPartnera, int brojStranice, int brojPrikazanih);
    Page<Faktura> findAllByVrstaFaktureAndPoslovnaGodinaAndNazivPartnera(
            boolean vrstaFakture, String nazivPartnera, long poslovnaGodinaId, int brojStranice, int brojPrikazanih);
	

}
