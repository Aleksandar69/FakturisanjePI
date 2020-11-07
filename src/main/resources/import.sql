INSERT INTO pdv (naziv,obrisano) VALUES ('Opsti',0);
INSERT INTO pdv (naziv,obrisano) VALUES ('Poseban',0);

INSERT INTO stopapdv (datum_vazenja,obrisano,procenat,pdv_id) VALUES ('2018-01-10 00:00:00.000',0,20,1);
INSERT INTO stopapdv (datum_vazenja,obrisano,procenat,pdv_id) VALUES ('2019-02-12 00:00:00.000',0,8,2);

INSERT INTO poslovna_godina (godina,obrisano,zakljucana) VALUES (2015,0,1);
INSERT INTO poslovna_godina (godina,obrisano,zakljucana) VALUES (2016,0,1);
INSERT INTO poslovna_godina (godina,obrisano,zakljucana) VALUES (2017,0,1);
INSERT INTO poslovna_godina (godina,obrisano,zakljucana) VALUES (2018,0,1);
INSERT INTO poslovna_godina (godina,obrisano,zakljucana) VALUES (2019,0,1);
INSERT INTO poslovna_godina (godina,obrisano,zakljucana) VALUES (2020,0,0);

INSERT INTO mjesto (drzava,naziv,obrisano,postanski_broj) VALUES ('Srbija','Novi Sad',0,21000);
INSERT INTO mjesto (drzava,naziv,obrisano,postanski_broj) VALUES ('Srbija','Sombor',0,25000);
INSERT INTO mjesto (drzava,naziv,obrisano,postanski_broj) VALUES ('Srbija','Beograd',0,11000);
INSERT INTO mjesto (drzava,naziv,obrisano,postanski_broj) VALUES ('Srbija','Zrenjanin',0,23000);
INSERT INTO mjesto (drzava,naziv,obrisano,postanski_broj) VALUES ('Srbija','Aleksinac',0,18220);
INSERT INTO mjesto (drzava,naziv,obrisano,postanski_broj) VALUES ('Srbija','Nis',0,18000);
INSERT INTO mjesto (drzava,naziv,obrisano,postanski_broj) VALUES ('Srbija','Pancevo',0,26000);
INSERT INTO mjesto (drzava,naziv,obrisano,postanski_broj) VALUES ('Srbija','Indija',0,22320);
INSERT INTO mjesto (drzava,naziv,obrisano,postanski_broj) VALUES ('Srbija','Sremska Mitrovica',0,22000);
INSERT INTO mjesto (drzava,naziv,obrisano,postanski_broj) VALUES ('Srbija','Ruma',0,22400);
INSERT INTO mjesto (drzava,naziv,obrisano,postanski_broj) VALUES ('Srbija','Sid',0,22240);
INSERT INTO mjesto (drzava,naziv,obrisano,postanski_broj) VALUES ('Srbija','Subotica',0,24000);
INSERT INTO mjesto (drzava,naziv,obrisano,postanski_broj) VALUES ('Srbija','Kragujevac',0,34000);

INSERT INTO preduzece (pib,adresa,email,logo,naziv,obrisano,tekuci_racun,tel,mjesto_id) VALUES ('20207921','Bulvevar Oslobodjenja 102' ,'mercator@podrska.rs','https://www.mercatorgroup.si/assets/Logotip-lezec/mercator-logotip-rdec-negative-lezeci.png','Mercator',0,'574816235917645124','0214870200',1);

INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id) VALUES ('skolski i kancelarijski materijal',0,1,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id) VALUES ('pica',0,2,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id) VALUES ('kucni ljubimci',0,2,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id) VALUES ('njega i higijena',0,2,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id) VALUES ('kucne potrepstine',0,2,1)
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id) VALUES ('ciscenje',0,2,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id) VALUES ('igracke',0,2,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id) VALUES ('slatkisi i grickalice',0,2,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id) VALUES ('smrznuto',0,2,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id) VALUES ('testenina i pirinac',0,2,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id) VALUES ('mlecni proizvodi',0,2,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id) VALUES ('delikatesi',0,2,1);

INSERT INTO poslovni_partner (pib,adresa,naziv,obrisano,tekuci_racun,vrsta_partnera,mjesto_id,preduzece_id) VALUES ('52631987','Bulevar Vojvode Stepe 12','Locale',0,'204370010000927056',1,1,1);
INSERT INTO poslovni_partner (pib,adresa,naziv,obrisano,tekuci_racun,vrsta_partnera,mjesto_id,preduzece_id) VALUES ('22687413','Bulevar Vojvode Stepe 60','Deniverzo d.o.o.',0,'140030612000132048',1,1,1);
INSERT INTO poslovni_partner (pib,adresa,naziv,obrisano,tekuci_racun,vrsta_partnera,mjesto_id,preduzece_id) VALUES ('10002593','Kolo Srpskih Sestrara 1','Smiley',0,'158815673200014898',1,4,1);
INSERT INTO poslovni_partner (pib,adresa,naziv,obrisano,tekuci_racun,vrsta_partnera,mjesto_id,preduzece_id) VALUES ('12002277','Bulevar Evrope 4','Glavismex',0,'781026612000654125',0,1,1);
INSERT INTO poslovni_partner (pib,adresa,naziv,obrisano,tekuci_racun,vrsta_partnera,mjesto_id,preduzece_id) VALUES ('33256900','Pavleka Miskina 20','Donstel',0,'472655528986756333',0,2,1);

INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Blok 5',0,1);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Grafitna Olovka HB',0,1);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Viski Jameson',0,2);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Fanta 1.5l',0,2);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Pedigree hrana za pse',0,3);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Pedigree dentaastix',0,3);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Nivea stick dezdorans',0,4);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Dove cvrsti sapun 100g',0,4);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Toalet Papir',0,5);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Kese za smece 120l',0,5);

INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Sundjer za sudove 6kom',0,6);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Fairy limun 800ml',0,6);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Gumena lopta',0,7);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Papuce',0,7);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Cips 150g',0,8);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Milka cokolada',0,8);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Maline 450g',0,9);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Makarone Barilla 400g',0,10);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Mlijeko 1l',0,11);
INSERT INTO roba_usluga (jedinica_mjere,naziv,obrisano,grupa_robe_id) VALUES ('komad','Sir Edamer 250g',0,12);

INSERT INTO faktura (broj_fakture,datum_fakture,datum_valute,iznos_bez_rabata,iznos_za_placanje,obrisano,osnovica,placeno,rabat,ukupan_pdv,vrsta_fakture,poslovna_godina_id,poslovni_partner_id,preduzece_id) VALUES (1,'2020-04-04 15:11:26.000','2020-04-15 15:11:26.000',1800,1890,0,1750,1,50,140,0,6,4,1)
INSERT INTO faktura (broj_fakture,datum_fakture,datum_valute,iznos_bez_rabata,iznos_za_placanje,obrisano,osnovica,placeno,rabat,ukupan_pdv,vrsta_fakture,poslovna_godina_id,poslovni_partner_id,preduzece_id) VALUES (2,'2020-05-04 15:11:26.000','2020-05-15 15:11:26.000',76500,83820,0,76500,1,0,7320,1,6,1,1);
INSERT INTO faktura (broj_fakture,datum_fakture,datum_valute,iznos_bez_rabata,iznos_za_placanje,obrisano,osnovica,placeno,rabat,ukupan_pdv,vrsta_fakture,poslovna_godina_id,poslovni_partner_id,preduzece_id) VALUES (3,'2020-06-04 15:11:26.000','2020-06-15 15:11:26.000',4650,5022,0,4650,1,0,372,1,6,2,1);
INSERT INTO faktura (broj_fakture,datum_fakture,datum_valute,iznos_bez_rabata,iznos_za_placanje,obrisano,osnovica,placeno,rabat,ukupan_pdv,vrsta_fakture,poslovna_godina_id,poslovni_partner_id,preduzece_id) VALUES (4,'2020-05-04 15:11:26.000','2020-05-15 15:11:26.000',50,54,0,50,1,0,4,1,6,2,1);
INSERT INTO faktura (broj_fakture,datum_fakture,datum_valute,iznos_bez_rabata,iznos_za_placanje,obrisano,osnovica,placeno,rabat,ukupan_pdv,vrsta_fakture,poslovna_godina_id,poslovni_partner_id,preduzece_id) VALUES (5,'2020-04-04 15:11:26.000','2020-04-15 15:11:26.000',112000,120949.2,0,111990,1,10,8959.2,1,6,3,1);

INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (48,648,600,1,0,600,8,0,1,4);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (92,1242,600,2,0,1150,8,50,1,3);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (2000,12000,10000,1,0,10000,20,0,2,1);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (5320,71820,66500,1,0,66500,8,0,2,5);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (12,162,50,3,0,150,8,0,3,19);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (360,4860,4500,1,0,4500,8,0,3,18);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (4,54,50,1,0,50,8,0,4,19);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (8000,108000,50000,2,0,100000,8,0,5,6);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (959.2,12949.2,4000,3,0,11990,8,10,5,12);

INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (58,748,700,1,0,600,8,0,1,2);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (82,1042,500,2,0,1150,8,50,1,7);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (2200,12500,11000,1,0,10000,20,0,2,8);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (5220,71920,66500,1,0,66500,8,0,2,9);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (10,150,60,4,0,150,8,0,3,10);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (560,4960,4700,1,0,4700,8,0,3,11);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (5,56,56,1,0,50,8,0,4,13);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (8200,109000,55000,2,0,110000,8,0,5,14);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (1959.2,12559.2,4500,3,0,11090,8,10,5,15);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (759.2,11149.2,6000,4,0,15550,8,10,5,16);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (659.2,11949.2,5000,2,0,11290,8,10,5,17);
INSERT INTO stavka_fakture (iznos_pdva,iznos,cijena,kolicina,obrisano,pdv_osnovica,pdv_procenat,rabat,faktura_id,roba_usluga_id) VALUES (909.2,10949.2,8000,3,0,12990,8,10,5,20);



INSERT INTO cjenovnik (datum_vazenja,obrisano) VALUES ('2013-11-01 00:00:00.000',0);
INSERT INTO cjenovnik (datum_vazenja,obrisano) VALUES ('2016-07-08 00:00:00.000',0);
INSERT INTO cjenovnik (datum_vazenja,obrisano) VALUES ('2014-06-19 00:00:00.000',0);
INSERT INTO cjenovnik (datum_vazenja,obrisano) VALUES ('2013-01-02 00:00:00.000',0);
INSERT INTO cjenovnik (datum_vazenja,obrisano) VALUES ('2017-11-20 00:00:00.000',0);
INSERT INTO cjenovnik (datum_vazenja,obrisano) VALUES ('2018-01-10 00:00:00.000',0);

INSERT INTO poslovni_partner_cjenovnici (poslovni_partner_id,cjenovnici_id) VALUES (1,2);
INSERT INTO poslovni_partner_cjenovnici (poslovni_partner_id,cjenovnici_id) VALUES (2,3);
INSERT INTO poslovni_partner_cjenovnici (poslovni_partner_id,cjenovnici_id) VALUES (3,4);
INSERT INTO poslovni_partner_cjenovnici (poslovni_partner_id,cjenovnici_id) VALUES (4,5);
INSERT INTO poslovni_partner_cjenovnici (poslovni_partner_id,cjenovnici_id) VALUES (5,6);

INSERT INTO preduzece_cjenovnici (preduzece_id,cjenovnici_id) VALUES (1,1);

INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (120,0,1,1);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (150,0,1,2);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (1000,0,2,3);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (100,0,2,4);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (215,0,2,5);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (150,0,3,6);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (410,0,3,7);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (160,0,3,8);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (200,0,4,9);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (100,0,4,10);

INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (170,0,4,11);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (250,0,12);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (500,0,5,13);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (100,0,5,14);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (125,0,6,15);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (150,0,6,16);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (80,0,6,17);

