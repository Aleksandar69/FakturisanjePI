package com.aleksandar.fakturisanje.service.interfaces;

import java.util.List;

import com.aleksandar.fakturisanje.model.Faktura;
import com.aleksandar.fakturisanje.model.Preduzece;


public interface IPreduzeceService {

	List<Preduzece> findAll();
    List<Faktura> findAllByPreduzeceAndVrstaFakture(boolean vrstaFakture,long id);
    List<Faktura> findAllByPreduzeceAndVrstaFaktureAndPlaceno(boolean vrstaFakture,long id, boolean placeno);
    Preduzece findOne(Long id);
    Preduzece save(Preduzece preduzece);
    Boolean delete(Long id);
}
