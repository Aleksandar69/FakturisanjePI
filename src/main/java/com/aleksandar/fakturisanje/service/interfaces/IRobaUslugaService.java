package com.aleksandar.fakturisanje.service.interfaces;

import org.springframework.data.domain.Page;

import com.aleksandar.fakturisanje.model.RobaUsluga;

public interface IRobaUslugaService {

	Page<RobaUsluga> findAll(String naziv, int brojStanice, int brojPrikazanih);
    RobaUsluga findOne(Long id);
    RobaUsluga save(RobaUsluga robaUsluga);
    Boolean delete(Long id);
    Page<RobaUsluga> findAllByGrupaRobe_id(Long id, String naziv, int brojStanice, int brojPrikazanih);
	
}
