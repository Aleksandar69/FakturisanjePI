package com.aleksandar.fakturisanje.service.interfaces;

import org.springframework.data.domain.Page;

import com.aleksandar.fakturisanje.model.Narudzbenica;
import com.aleksandar.fakturisanje.model.Otpremnica;

public interface IOtpremnicaService {
	
    Otpremnica findOne(Long id);

    Otpremnica save(Otpremnica otpremnica);
    
    void update(Otpremnica otpremnica);
    
    Page<Otpremnica> findAllByNazivPartnera(String nazivPartnera, int brojStranice, int brojPrikazanih);

    Page<Otpremnica> findAllByPoslovnaGodinaAndNazivPartnera(String nazivPartnera, long poslovnaGodinaId, int brojStranice, int brojPrikazanih);

    void napraviFakturuOdOtpremnice(Otpremnica otpremnica,int poslednjaPoslovnjaGodina);

    void napraviOtpremnicuOdNarudzbenice(Narudzbenica narudzbenica);
}
