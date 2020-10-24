package com.aleksandar.fakturisanje.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.StopaPDV;


@Repository
public interface StopaPdvRepository extends JpaRepository<StopaPDV, Long>{
	
    List<StopaPDV> findAllByObrisano(boolean obrisano);

    StopaPDV findByObrisanoAndId(boolean obrisano, long id);
}
