package com.aleksandar.fakturisanje.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.RobaUsluga;

@Repository
public interface RobaUslugaRepository extends JpaRepository<RobaUsluga, Long>{
	
    Page<RobaUsluga> findAllByObrisanoAndNazivRobeUslugeIgnoreCaseContains(boolean obrisano,
            String nazivRobeUsluge, Pageable pageable);
	Page<RobaUsluga> findAllByGrupaRobe_idAndObrisanoAndNazivRobeUslugeIgnoreCaseContains(Long id,
	boolean obrisano, String nazivRobeUsluge, Pageable pageable);
	RobaUsluga findByObrisanoAndId(boolean obrisano, long id);
	
	
}
