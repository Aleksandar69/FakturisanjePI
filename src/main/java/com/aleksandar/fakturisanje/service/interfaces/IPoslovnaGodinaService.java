package com.aleksandar.fakturisanje.service.interfaces;

import java.util.List;

import com.aleksandar.fakturisanje.model.PoslovnaGodina;

public interface IPoslovnaGodinaService {

    List<PoslovnaGodina> findAll();
	PoslovnaGodina findOne(Long id);
    PoslovnaGodina save(PoslovnaGodina poslovnaGodina);
    Boolean delete(Long id);
    
    PoslovnaGodina findPoslovnaGodinaIsNotObrisanoIsNotZakljucana();
	
}
