package com.aleksandar.fakturisanje.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.StavkaCjenovnika;

@Repository
public interface StavkaCjenovnikaRepository extends JpaRepository<StavkaCjenovnika, Long>{

	List<StavkaCjenovnika> findAllByObrisano(boolean obrisano);

    StavkaCjenovnika findByObrisanoAndId(boolean obrisano, long id);
    List<StavkaCjenovnika> findAllByObrisanoAndRobaUsluga_Id(boolean obrisano, Long id);

    Page<StavkaCjenovnika> findAllByObrisanoAndCjenovnik_IdAndRobaUsluga_NazivIgnoreCaseContains(
            boolean obrisano, long cenovnik_id, String nazivRobeUsluge,Pageable pageable);
	
}
