CREATE TABLE prodotto (
id integer not null primary key,
nome varchar(30) not null,
descrizione varchar not null,
prezzo float not null);

CREATE TABLE utente (
id integer not null primary key,
username varchar(30) not null unique,
password varchar not null,
nome varchar(30),
cognome varchar(30),
email varchar(75),
indirizzo varchar,
ruolo varchar(8) not null);

CREATE TABLE ordine (
id integer not null primary key,
utente integer not null references utente(id),
data varchar not null,
stato varchar(6) not null,
importo float not null);

create table rigaOrdine (
id integer not null primary key,
ordine integer not null references ordine(id),
prodotto integer not null references prodotto(id),
quantita integer not null,
costo float not null);

CREATE SEQUENCE prodotto_id_seq 
START WITH 1 INCREMENT BY 1
MINVALUE 0
MAXVALUE 9999999
NO CYCLE;

CREATE SEQUENCE utente_id_seq 
START WITH 1 INCREMENT BY 1
MINVALUE 0
MAXVALUE 9999999
NO CYCLE;

CREATE SEQUENCE ordine_id_seq 
START WITH 1 INCREMENT BY 1
MINVALUE 0
MAXVALUE 9999999
NO CYCLE;

CREATE SEQUENCE riga_ordine_id_seq 
START WITH 1 INCREMENT BY 1
MINVALUE 0
MAXVALUE 9999999
NO CYCLE;