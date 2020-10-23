package com.aleksandar.fakturisanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.StavkaFakture;

@Repository
public interface StavkaFaktureRepo extends JpaRepository<StavkaFakture, Long>{

}
