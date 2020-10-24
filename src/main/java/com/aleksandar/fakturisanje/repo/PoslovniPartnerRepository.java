package com.aleksandar.fakturisanje.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aleksandar.fakturisanje.model.PoslovniPartner;

public interface PoslovniPartnerRepository extends JpaRepository<PoslovniPartner, Long>{
	
    List<PoslovniPartner> findAllByObrisano(boolean obrisano);

    PoslovniPartner findByObrisanoAndId(boolean obrisano, long id);

    List<PoslovniPartner> findByPreduzece_id(Long id);

    List<PoslovniPartner>
    findAllByNazivPartneraIgnoreCaseContainsOrAdresaIgnoreCaseContainsOrMesto_NazivIgnoreCaseContainsAndObrisano(
            String nazivPartnera, String adresa, String naziv,
            boolean obrisano);
	
}
