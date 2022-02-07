-- INDIRIZZI
INSERT INTO indirizzo(via, provincia, cap, civico) VALUES ('via bae', 'PD', 35030, 5);
INSERT INTO indirizzo(via, provincia, cap, civico) VALUES ('via Roma', 'GG', 156548, 2);
INSERT INTO indirizzo(via, provincia, cap, civico) VALUES ('via bob', 'GG', 156548, 3);
INSERT INTO indirizzo(via, provincia, cap, civico) VALUES ('via utente', 'UT', 36047, 33);
INSERT INTO indirizzo(via, provincia, cap, civico) VALUES ('via dipendente', 'DP', 69033, 87);

-- PRIVILEGI
INSERT INTO privilege(name) VALUES ('ADMIN_PRIVILEGE');
    INSERT INTO privilege(name) VALUES ('DIPENDENTE_PRIVILEGE');
INSERT INTO privilege(name) VALUES ('UTENTE_PRIVILEGE');

-- RUOLI
INSERT INTO ruolo (name) VALUES('ROLE_ADMIN');
INSERT INTO ruolo (name) VALUES('ROLE_DIPENDENTE');
INSERT INTO ruolo (name) VALUES('ROLE_UTENTE');

--COLLEGAMENTO RUOLI PRIVILEGI
INSERT INTO roles_privileges(roledao_id,privilege_id) VALUES (1,1);
INSERT INTO roles_privileges(roledao_id,privilege_id) VALUES (1,2);
INSERT INTO roles_privileges(roledao_id,privilege_id) VALUES (1,3);
INSERT INTO roles_privileges(roledao_id,privilege_id) VALUES (2,2);
INSERT INTO roles_privileges(roledao_id,privilege_id) VALUES (2,3);
INSERT INTO roles_privileges(roledao_id,privilege_id) VALUES (3,3);


-- UTENTI
INSERT INTO utente (nome, username, password, email, indirizzo, ruolo) VALUES ('Radu', 'radu01', '$2a$10$sHZGHYyjUx9jlIuzbHU06ehsfu0eIct9FRxiOUS96Z5jioih1kOZK', "radusclifos@gmail.com", 1, 1);
INSERT INTO utente (nome, username, password, email, indirizzo, ruolo) VALUES ('Giacomo', 'Jeck01', '$2a$10$sHZGHYyjUx9jlIuzbHU06ehsfu0eIct9FRxiOUS96Z5jioih1kOZK', "giacomocortese@gmail.com", 2, 1);
INSERT INTO utente (nome, username, password, email, indirizzo, ruolo) VALUES ('Cristian', 'Cristian_tomasiPd', '$2a$10$sHZGHYyjUx9jlIuzbHU06ehsfu0eIct9FRxiOUS96Z5jioih1kOZK', "cristiantommasi@gmail.com", 3, 1);
INSERT INTO utente (nome, username, password, email, indirizzo, ruolo) VALUES ('Test Utente', 'utente', '$2a$10$sHZGHYyjUx9jlIuzbHU06ehsfu0eIct9FRxiOUS96Z5jioih1kOZK', "utente@gmail.com", 4, 3);
INSERT INTO utente (nome, username, password, email, indirizzo, ruolo) VALUES ('Test Dipendente', 'dipendente', '$2a$10$sHZGHYyjUx9jlIuzbHU06ehsfu0eIct9FRxiOUS96Z5jioih1kOZK', "dipendente@gmail.com", 5, 2);

-- PIATTI
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Ravioli di carne al vapore', 1, "Antipasti", 4.50, 'Glutine', '../../../../assets/images/piatti/ravioliCarne.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Ravioli di pollo brasati', 2, "Antipasti", 4.50, 'Glutine', '../../../../assets/images/piatti/ravioliPollo.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Edamame', 3, "Antipasti", 2.00, 'Verdura Surgelati', '../../../../assets/images/piatti/edamame.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Nuvolette di gambero', 4, "Antipasti", 1.50, 'Glutine Crostacei', '../../../../assets/images/piatti/nuvoleDiGambero.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Pane orientale al vapore', 5, "Antipasti", 2.00, 'Glutine', '../../../../assets/images/piatti/paneOrientaleVapore.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Pane orientale fritto', 6, "Antipasti", 2.00, 'Glutine Crostacei', '../../../../assets/images/piatti/paneOrientaleFritto.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Pane orientale dolce', 7, "Antipasti", 2.00, 'Glutine Crostacei', '../../../../assets/images/piatti/paneOrientaleDolce.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Stick di gamberi', 8, "Antipasti", 4.00, 'Glutine Crostacei', '../../../../assets/images/piatti/stickGamberi.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Zuppa di miso', 9, "Antipasti", 4.00, 'Soia', '../../../../assets/images/piatti/zuppaMiso.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Zuppa agropiccante', 10, "Antipasti", 4.00, 'Soia Uova/Derivati', '../../../../assets/images/piatti/zuppaAgropiccante.jpg');

INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Salmone', 31, "Nigiri", 3.00, 'Pesce Glutine', '../../../../assets/images/piatti/nigiriSalmone.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Gambero cotto', 32, "Nigiri", 3.50, 'Crostacei Glutine', '../../../../assets/images/piatti/nigiriGambero.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Branzino', 33, "Nigiri", 3.00, 'Pesce Glutine', '../../../../assets/images/piatti/nigiriBranzino.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Tonno', 34, "Nigiri", 3.00, 'Pesce Glutine', '../../../../assets/images/piatti/nigiriTonno.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Salmone Scottato', 35, "Nigiri", 3.50, 'Crostacei Pesce Glutine', '../../../../assets/images/piatti/nigiriScottato.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Anguilla', 36, "Nigiri", 2.50, 'Pesce Glutine', '../../../../assets/images/piatti/nigiriAnguilla.jpg');

INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Salmone', 41, "Sashimi", 8.00, 'Pesce', '../../../../assets/images/piatti/sashimiSalmone.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Tonno', 42, "Sashimi", 7.00, 'Pesce', '../../../../assets/images/piatti/sashimiTonno.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Branzino', 43, "Sashimi", 9.00, 'Pesce', '../../../../assets/images/piatti/sashimiBranzino.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Misto', 44, "Sashimi", 10.00, 'Pesce', '../../../../assets/images/piatti/sashimiMisto.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Speciale', 45, "Sashimi", 16.00, 'Pesce', '../../../../assets/images/piatti/sashimiSpeciale.jpg');

INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Sake maki', 51, "Hosomaki", 3.00, 'Pesce Glutine', '../../../../assets/images/piatti/hosomakiSake.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Avocado maki', 52, "Hosomaki", 3.50, 'Glutine Verdura', '../../../../assets/images/piatti/hosomakiAvocado.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Tekka maki', 53, "Hosomaki", 3.00, 'Pesce Glutine', '../../../../assets/images/piatti/hosomakiTekka.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Ebi maki', 54, "Hosomaki", 2.50, 'Crostacei Glutine', '../../../../assets/images/piatti/hosomakiEbiten.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Kappa maki', 55, "Hosomaki", 2.50, 'Glutine Verdura', '../../../../assets/images/piatti/hosomakiKappa.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Tempura maki', 56, "Hosomaki", 3.50, 'Pesce Glutine', '../../../../assets/images/piatti/hosomakiTempura.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Misto maki', 57, "Hosomaki", 3.00, 'Pesce Crostacei Glutine Verdura', '../../../../assets/images/piatti/mistoMaki.jpg');

INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Sake', 61, "Temaki", 4.00, 'Pesce Sesamo Glutine', '../../../../assets/images/piatti/temakiSake.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Maguro', 62, "Temaki", 4.00, 'Pesce Sesamo Glutine Uova/Derivati', '../../../../assets/images/piatti/temakiMaguro.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Ebiten', 63, "Temaki", 4.00, 'Sesamo Glutine Crostacei Uova/Derivati', '../../../../assets/images/piatti/temakiEbiten.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Veggie', 64, "Temaki", 4.00, 'Sesamo Glutine Uova/Derivati', '../../../../assets/images/piatti/temakiVerdura.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Spicy Sake', 65, "Temaki", 5.00, 'Pesce Sesamo Glutine', '../../../../assets/images/piatti/temakiSpicySake.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Spicy Tuna', 66, "Temaki", 5.00, 'Pesce Sesamo Glutine', '../../../../assets/images/piatti/temakiSpicyTuna.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Miura', 67, "Temaki", 5.00, 'Pesce Sesamo Glutine', '../../../../assets/images/piatti/temakiMiura.jpg');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Tempura', 68, "Temaki", 5.00, 'Pesce Sesamo Glutine', '../../../../assets/images/piatti/temakiTempura.jpg');

-- ORDINAZIONI:
-- 1
INSERT INTO ordinazione (tipologia, tavolo, persone, pagato, preparato, consegnato) VALUES ('Ristorante', 1, 2, false, false, false);
INSERT INTO piatto_ordinato (ordinazione_id_ordinazione, piatto_id_piatto, quantita, consegnato) VALUES (1, 7, 1, false);
INSERT INTO piatto_ordinato (ordinazione_id_ordinazione, piatto_id_piatto, quantita, consegnato) VALUES (1, 8, 3, false);

-- 2
INSERT INTO ordinazione (tipologia, tavolo, persone, pagato, preparato, consegnato) VALUES ('Ristorante', 2, 3, false, false, false);
INSERT INTO piatto_ordinato (ordinazione_id_ordinazione, piatto_id_piatto, quantita, consegnato) VALUES (2, 2, 2, false);
INSERT INTO piatto_ordinato (ordinazione_id_ordinazione, piatto_id_piatto, quantita, consegnato) VALUES (2, 6, 1, false);
INSERT INTO piatto_ordinato (ordinazione_id_ordinazione, piatto_id_piatto, quantita, consegnato) VALUES (2, 8, 4, false);
INSERT INTO piatto_ordinato (ordinazione_id_ordinazione, piatto_id_piatto, quantita, consegnato) VALUES (2, 9, 2, false);
INSERT INTO piatto_ordinato (ordinazione_id_ordinazione, piatto_id_piatto, quantita, consegnato) VALUES (2, 17, 2, false);
INSERT INTO piatto_ordinato (ordinazione_id_ordinazione, piatto_id_piatto, quantita, consegnato) VALUES (2, 24, 3, false);

-- 3
INSERT INTO ordinazione (tipologia, tavolo, persone, pagato, orario_consegna, indirizzo, preparato, consegnato) VALUES ('Domicilio', 0, 4, true, '19:30', 1, false, false);
INSERT INTO piatto_ordinato (ordinazione_id_ordinazione, piatto_id_piatto, quantita, consegnato) VALUES (3, 2, 2, false);
