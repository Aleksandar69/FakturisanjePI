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

INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id, stopapdv_id) VALUES ('skolski i kancelarijski materijal',0,1,1,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id, stopapdv_id) VALUES ('pica',0,2,1,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id, stopapdv_id) VALUES ('kucni ljubimci',0,2,1,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id, stopapdv_id) VALUES ('njega i higijena',0,2,1,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id, stopapdv_id) VALUES ('kucne potrepstine',0,2,1,1)
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id, stopapdv_id) VALUES ('ciscenje',0,2,1,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id, stopapdv_id) VALUES ('igracke',0,2,1,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id, stopapdv_id) VALUES ('slatkisi i grickalice',0,2,1,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id, stopapdv_id) VALUES ('smrznuto',0,2,1,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id, stopapdv_id) VALUES ('testenina i pirinac',0,2,1,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id, stopapdv_id) VALUES ('mlecni proizvodi',0,2,1,1);
INSERT INTO grupa_robe (naziv,obrisano,pdv_id,preduzece_id, stopapdv_id) VALUES ('delikatesi',0,2,1,1);

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

INSERT INTO cjenovnik (datum_vazenja_od, datum_vazenja_do, obrisano, preduzece_id, poslovnipartner_id) VALUES ('2020-11-01 00:00:00.000', '2020-12-10, 00:00:00.000',0, 1, null);
INSERT INTO cjenovnik (datum_vazenja_od,datum_vazenja_do, obrisano, preduzece_id, poslovnipartner_id) VALUES ('2020-11-01 00:00:00.000', '2020-12-10 00:00:00.000',0,null, 1);
INSERT INTO cjenovnik (datum_vazenja_od, datum_vazenja_do, obrisano, preduzece_id, poslovnipartner_id) VALUES ('2020-11-01 00:00:00.000', '2020-12-10 00:00:00.000',0, null, 2);
INSERT INTO cjenovnik (datum_vazenja_od,datum_vazenja_do, obrisano, preduzece_id, poslovnipartner_id) VALUES ('2020-11-01 00:00:00.000', '2020-12-10 00:00:00.000',0, null, 3);
INSERT INTO cjenovnik (datum_vazenja_od, datum_vazenja_do, obrisano, preduzece_id, poslovnipartner_id) VALUES ('2020-11-01 00:00:00.000', '2020-12-10 00:00:00.000',0, null, 4);
INSERT INTO cjenovnik (datum_vazenja_od,datum_vazenja_do, obrisano, preduzece_id, poslovnipartner_id) VALUES ('2020-11-01 00:00:00.000', '2020-12-10 00:00:00.000',0, null, 5);


INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (120,0,1,10);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (150,0,1,11);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (200,0,1,5);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (350,0,1,8);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (100,0,1,9);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (100,0,1,15);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (1000,0,2,3);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (100,0,2,4);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (215,0,2,5);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (150,0,3,6);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (410,0,3,7);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (160,0,3,8);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (200,0,4,9);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (100,0,4,10);

INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (170,0,4,11);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (250,0,5,12);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (500,0,5,13);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (100,0,5,14);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (125,0,6,15);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (150,0,6,16);
INSERT INTO stavka_cjenovnika (cijena,obrisano,cjenovnik_id,roba_usluga_id) VALUES (80,0,6,17);

INSERT INTO narudzbenica (broj_narudzbenice, datum_narudzbenice, obrisano, tip_narudzbenice, poslovna_godina_id, poslovni_partner_id, preduzece_id) VALUES (1, '2020-11-10 12:37:11.874000', 0, 1, 6, 4, 1);
INSERT INTO narudzbenica (broj_narudzbenice, datum_narudzbenice, obrisano, tip_narudzbenice, poslovna_godina_id, poslovni_partner_id, preduzece_id) VALUES (5, '2020-11-10 12:37:52.184000', 0, 1, 6, 5, 1);
INSERT INTO narudzbenica (broj_narudzbenice, datum_narudzbenice, obrisano, tip_narudzbenice, poslovna_godina_id, poslovni_partner_id, preduzece_id) VALUES (5, '2020-11-10 12:38:27.767000', 0, 0, 6, 1, 1);
INSERT INTO narudzbenica (broj_narudzbenice, datum_narudzbenice, obrisano, tip_narudzbenice, poslovna_godina_id, poslovni_partner_id, preduzece_id) VALUES (5, '2020-11-10 12:38:53.884000', 0, 0, 6, 2, 1);
INSERT INTO narudzbenica (broj_narudzbenice, datum_narudzbenice, obrisano, tip_narudzbenice, poslovna_godina_id, poslovni_partner_id, preduzece_id) VALUES (5, '2020-12-06 12:48:09.985000', 0, 1, 6, 4, 1);
INSERT INTO narudzbenica (broj_narudzbenice, datum_narudzbenice, obrisano, tip_narudzbenice, poslovna_godina_id, poslovni_partner_id, preduzece_id) VALUES (5, '2020-12-06 13:07:29.449000', 0, 0, 6, 3, 1);


INSERT INTO stavka_narudzbenice (jedinica_mjere, kolicina, obrisano, opis, narudzbenica_id, roba_usluga_id) VALUES ('komad', 200, 0, 'Cips 150g', 1, 15);
INSERT INTO stavka_narudzbenice (jedinica_mjere, kolicina, obrisano, opis, narudzbenica_id, roba_usluga_id) VALUES ('komad', 30, 0, 'Dove cvrsti sapun 100g', 1, 8);
INSERT INTO stavka_narudzbenice (jedinica_mjere, kolicina, obrisano, opis, narudzbenica_id, roba_usluga_id) VALUES ('komad', 20, 0, 'Kese za smece 120l', 1, 10); 	
INSERT INTO stavka_narudzbenice (jedinica_mjere, kolicina, obrisano, opis, narudzbenica_id, roba_usluga_id) VALUES ('komad', 30, 0, 'Sundjer za sudove 6kom', 1, 11);
INSERT INTO stavka_narudzbenice (jedinica_mjere, kolicina, obrisano, opis, narudzbenica_id, roba_usluga_id) VALUES ('komad', 30, 0, 'Toalet Papir', 2, 9);
INSERT INTO stavka_narudzbenice (jedinica_mjere, kolicina, obrisano, opis, narudzbenica_id, roba_usluga_id) VALUES ('komad', 100, 0, 'Kese za smece 120l', 2, 10);
INSERT INTO stavka_narudzbenice (jedinica_mjere, kolicina, obrisano, opis, narudzbenica_id, roba_usluga_id) VALUES ('komad', 40, 0, 'Pedigree hrana za pse', 3, 5);
INSERT INTO stavka_narudzbenice (jedinica_mjere, kolicina, obrisano, opis, narudzbenica_id, roba_usluga_id) VALUES ('komad', 40, 0, 'Viski Jameson', 3, 3);
INSERT INTO stavka_narudzbenice (jedinica_mjere, kolicina, obrisano, opis, narudzbenica_id, roba_usluga_id) VALUES ('komad', 100, 0, 'Fanta 1.5l', 4, 4);
INSERT INTO stavka_narudzbenice (jedinica_mjere, kolicina, obrisano, opis, narudzbenica_id, roba_usluga_id) VALUES ('komad', 200, 0, 'Pedigree dentaastix', 4, 6);
INSERT INTO stavka_narudzbenice (jedinica_mjere, kolicina, obrisano, opis, narudzbenica_id, roba_usluga_id) VALUES ('komad', 100, 0, 'Dove cvrsti sapun 100g', 5, 8);
INSERT INTO stavka_narudzbenice (jedinica_mjere, kolicina, obrisano, opis, narudzbenica_id, roba_usluga_id) VALUES ('komad', 50, 0, 'Sundjer za sudove 6kom', 5, 11);
INSERT INTO stavka_narudzbenice (jedinica_mjere, kolicina, obrisano, opis, narudzbenica_id, roba_usluga_id) VALUES ('komad', 50, 0, 'Cips 150g', 5, 15);
INSERT INTO stavka_narudzbenice (jedinica_mjere, kolicina, obrisano, opis, narudzbenica_id, roba_usluga_id) VALUES ('komad', 50, 0, 'Sundjer za sudove 6kom', 6, 11);
INSERT INTO stavka_narudzbenice (jedinica_mjere, kolicina, obrisano, opis, narudzbenica_id, roba_usluga_id) VALUES ('komad', 50, 0, 'Toalet Papir', 6, 9);


