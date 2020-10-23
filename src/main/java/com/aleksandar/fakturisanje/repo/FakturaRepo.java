package com.aleksandar.fakturisanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.Faktura;

@Repository
public interface FakturaRepo extends JpaRepository<Faktura, Long> {

}
