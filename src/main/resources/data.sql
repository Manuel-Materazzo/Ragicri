INSERT INTO indirizzo(via, provincia, cap, civico) VALUES ('via bae', 'PD', '35030', '5');

INSERT INTO utente (nome, ruolo, user_name, password, indirizzo) VALUES ('Radu', 'Cuoco', 'radu01', 'radu', 1);



INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Ravioli di carne al vapore', 1, "Antipasti", 4.50, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Ravioli di carne alla griglia', 2, "Antipasti", 4.50, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Ravioli di verdura al vapore', 3, "Antipasti", 4.00, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Edamame', 4, "Antipasti", 4.00, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Wakame', 5, "Antipasti", 4.50, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Chele di granchio fritto', 6, "Antipasti", 4.00, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Nuvolette di gambero', 7, "Antipasti", 1.50, '', '');

INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Salmone', 31, "Nigiri", 3.00, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Gambero cotto', 32, "Nigiri", 3.50, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Branzino', 33, "Nigiri", 3.00, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Granchio', 34, "Nigiri", 2.50, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Tonno', 35, "Nigiri", 3.50, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Orata', 36, "Nigiri", 3.00, '', '');

INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Salmone', 41, "Sashimi", 8.00, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Tonno', 42, "Sashimi", 7.00, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Branzino', 43, "Sashimi", 9.00, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Misto', 44, "Sashimi", 10.00, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Speciale', 45, "Sashimi", 16.00, '', '');

INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Sake maki', 51, "Hosomaki", 3.00, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Avocado maki', 52, "Hosomaki", 3.50, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Tekka maki', 53, "Hosomaki", 3.00, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Ebi maki', 54, "Hosomaki", 2.50, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Hosomaki fritto', 55, "Hosomaki", 3.50, '', '');
INSERT INTO piatto (nome, numero, tipologia, prezzo, allergeni, img)
    VALUES ('Misto maki', 56, "Hosomaki", 3.00, '', '');


INSERT INTO ordinazione (tipologia, tavolo, persone, pagato) VALUES ('Ristorante', 1, 2, false);


INSERT INTO piatto_ordinato (ordinazione_id_ordinazione, piatto_id_piatto, quantita, consegnato) VALUES (1, 7, 1, false);
INSERT INTO piatto_ordinato (ordinazione_id_ordinazione, piatto_id_piatto, quantita, consegnato) VALUES (1, 8, 3, false);