package com.aleksandar.fakturisanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.Otpremnica;

@Repository
public interface OtpremnicaRepo extends JpaRepository<Otpremnica, Long> {

}
