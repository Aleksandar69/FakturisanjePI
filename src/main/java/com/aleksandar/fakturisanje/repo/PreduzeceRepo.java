package com.aleksandar.fakturisanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksandar.fakturisanje.model.Preduzece;

@Repository
public interface PreduzeceRepo extends JpaRepository<Preduzece, Long>{

}
