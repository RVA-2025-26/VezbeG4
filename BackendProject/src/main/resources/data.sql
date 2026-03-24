-- Artikl podaci
insert into artikl (id, naziv, proizvodjac)
values (nextval('artikl_seq'), 'Moja Kravica dugotrajno mleko', 'AD Imlek'),
       (nextval('artikl_seq'), 'Moja Kravica jogurt', 'AD Imlek'),
       (nextval('artikl_seq'), 'Persil Regular prasak', 'Henkel Srbija d.o.o.'),
       (nextval('artikl_seq'), 'Jabuka Gala', 'Delhaize Serbia d.o.o.'),
       (nextval('artikl_seq'), 'Puding vanila Dr. Oetker', 'Dr. Oetker d.o.o.'),
       (nextval('artikl_seq'), 'Puding cokolada Dr. Oetker', 'Dr. Oetker d.o.o.');

-- Dobavljac podaci
insert into dobavljac
values (nextval('dobavljac_seq'), 'AD Imlek', 'Industrijsko naselje bb, Padinska skela, Beograd', '+381113050505'),
       (nextval('dobavljac_seq'), 'Henkel Srbija d.o.o.', 'Bulevar Oslobodjenja 383, Beograd', '+381112072200'),
       (nextval('dobavljac_seq'), 'Delhaize Serbia d.o.o.', 'Jurija Gagarina 14, Beograd', '08003537030'),
       (nextval('dobavljac_seq'), 'Dr. Oetker d.o.o.', 'Vuka Karadzica 13, Simanovci', '+38122800300');

-- Porudzbina podaci
insert into porudzbina (id, datum_porudzbine, datum_isporuke, dobavljac, iznos, placeno)
values (nextval('porudzbina_seq'), to_date('12.01.2026.', 'dd.mm.yyyy.'),
        to_date('14.01.2026.', 'dd.mm.yyyy.'), 1, 6300, TRUE),
       (nextval('porudzbina_seq'), to_date('03.02.2026.', 'dd.mm.yyyy.'),
        to_date('06.02.2026.', 'dd.mm.yyyy.'), 2, 13000, FALSE),
       (nextval('porudzbina_seq'), to_date('18.02.2026.', 'dd.mm.yyyy.'),
        to_date('19.02.2026.', 'dd.mm.yyyy.'), 3, 12000, TRUE),
       (nextval('porudzbina_seq'), to_date('05.03.2026.', 'dd.mm.yyyy.'),
        to_date('07.03.2026.', 'dd.mm.yyyy.'), 4, 4600, TRUE);

-- Stavka porudzbine podaci
insert into stavka_porudzbine (id, porudzbina, redni_broj, artikl, kolicina, jedinica_mere, cena)
values (nextval('stavka_porudzbine_seq'), 1, 1, 1, 18, 'komad', 160),
       (nextval('stavka_porudzbine_seq'), 1, 2, 2, 12, 'komad', 200),
       (nextval('stavka_porudzbine_seq'), 2, 1, 3, 6, 'komad', 600),
       (nextval('stavka_porudzbine_seq'), 2, 2, 3, 4, 'komad', 700),
       (nextval('stavka_porudzbine_seq'), 3, 1, 4, 25, 'kg', 180),
       (nextval('stavka_porudzbine_seq'), 4, 1, 5, 40, 'komad', 40),
       (nextval('stavka_porudzbine_seq'), 4, 2, 6, 35, 'komad', 50);