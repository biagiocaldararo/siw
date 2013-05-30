CREATE TABLE cliente (
id integer not null primary key asc autoincrement,
username varchar(30) not null unique,
password varchar not null,
nome varchar(30),
cognome varchar(30),
email varchar(75),
indirizzo varchar,
ruolo varchar(8) not null);

CREATE TABLE ordine (
id integer not null primary key asc autoincrement,
cliente integer not null references cliente(id),
data datetime not null,
stato varchar(6) not null,
importo float not null);

create table rigaOrdine (
id integer not null primary key asc autoincrement,
ordine integer not null references ordine(id),
prodotto integer not null references prodotto(id),
quantita integer not null,
costo float not null);

CREATE TABLE prodotto (
id integer not null primary key asc autoincrement,
nome varchar(30) not null,
descrizione varchar not null,
prezzo float not null);