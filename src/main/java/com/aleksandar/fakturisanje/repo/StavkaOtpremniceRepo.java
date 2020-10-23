package com.aleksandar.fakturisanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.StavkaOtpremnice;

@Repository
public interface StavkaOtpremniceRepo extends JpaRepository<StavkaOtpremnice, Long> {

}
