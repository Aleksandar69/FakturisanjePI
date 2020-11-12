package com.aleksandar.fakturisanje.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.Faktura;
import com.aleksandar.fakturisanje.model.Narudzbenica;

@Repository
public interface NarudzbenicaRepository extends JpaRepository<Narudzbenica, Long>{

    Page<Narudzbenica> findAllByPoslovniPartner_NazivIgnoreCaseContains(String nazivPartnera , Pageable pageable);

    Page<Narudzbenica> findAllByPoslovnaGodina_IdAndPoslovniPartner_NazivIgnoreCaseContains(long poslovnaGodina_id, String nazivPartnera, Pageable pageable);

    List<Narudzbenica> findAllByObrisano(boolean obrisano);

    Narudzbenica findByObrisanoAndId(boolean obrisano, long id);

    List<Narudzbenica> findByObrisanoAndPreduzece_Id(boolean obrisano, long id);
    
    Page<Narudzbenica> findAllByTipNarudzbeniceAndPoslovniPartner_NazivIgnoreCaseContainsAndObrisanoIsFalse(boolean tipNar,String nazivPartnera,Pageable pageable);

    Page<Narudzbenica>
	findAllByTipNarudzbeniceAndPoslovnaGodina_IdAndPoslovniPartner_NazivIgnoreCaseContainsAndObrisanoIsFalse(
			boolean tipNarudzbenice, long poslovnaGodina_id, String nazivPartnera,Pageable pageable);
	
}
