package com.aleksandar.fakturisanje.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.Otpremnica;

@Repository
public interface OtpremnicaRepository extends JpaRepository<Otpremnica, Long> {
	
	
    Page<Otpremnica> findAllByPoslovniPartner_NazivIgnoreCaseContains(String nazivPartnera, Pageable pageable);

    Page<Otpremnica> findAllByPoslovnaGodina_IdAndPoslovniPartner_NazivIgnoreCaseContains(long poslovnaGodina_id, String nazivPartnera, Pageable pageable);

	List<Otpremnica> findAllByObrisano(boolean obrisano);
	
    Otpremnica findByObrisanoAndId(boolean obrisano, long id);

    List<Otpremnica> findByObrisanoAndPreduzece_Id(boolean obrisano, long id);
}
