package com.aleksandar.fakturisanje.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.Faktura;

@Repository
public interface FakturaRepository extends JpaRepository<Faktura, Long> {

	Page<Faktura> findAllByVrstaFaktureAndPoslovniPartner_NazivPartneraIgnoreCaseContainsAndObrisanoIsFalse(
			boolean vrstaFakture,String nazivPartnera , Pageable pageable);

	Page<Faktura>
	findAllByVrstaFaktureAndPoslovnaGodina_IdAndPoslovniPartner_NazivPartneraIgnoreCaseContainsAndObrisanoIsFalse(
			boolean vrstaFakture, long poslovnaGodina_id, String nazivPartnera,Pageable pageable);
	

    List<Faktura> findAllByObrisano(boolean obrisano);
    
    Faktura findByObrisanoAndId(boolean obrisano, long id);
	
	List<Faktura> findByObrisanoAndAndVrstaFaktureAndPreduzece_Id(boolean obrisano, boolean vrstaFakture,long id);

	List<Faktura> findByObrisanoAndAndVrstaFaktureAndPreduzece_IdAndPlaceno(
			boolean obrisano, boolean vrstaFakture, long id, boolean placeno);
	
}
