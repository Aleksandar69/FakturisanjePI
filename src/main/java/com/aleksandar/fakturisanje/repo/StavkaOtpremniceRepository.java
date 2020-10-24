package com.aleksandar.fakturisanje.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.StavkaOtpremnice;

@Repository
public interface StavkaOtpremniceRepository extends JpaRepository<StavkaOtpremnice, Long> {

    List<StavkaOtpremnice> findByOtpremnica_id(Long id);

    List<StavkaOtpremnice> findAllByObrisano(boolean obrisano);

    StavkaOtpremnice findByObrisanoAndId(boolean obrisano, long id);
	
}
