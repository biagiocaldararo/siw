CREATE TABLE utente (
	id integer not null primary key asc autoincrement,
	username varchar(30) not null unique,
	password varchar not null,
	ruolo varchar(8) not null,
	carrelloId integer references ordine(id)
);

CREATE TABLE ordine (
	id integer not null primary key asc autoincrement,
	utenteId integer not null references utente(id),
	data datetime,
	stato varchar(6) not null default "aperto",
	totale float not null default 0,
	numProdotti integer not null default 0
);

create table rigaOrdine (
	id integer not null primary key asc autoincrement,
	ordineId integer not null references ordine(id),
	prodottoId integer not null references prodotto(id),
	quantita integer not null,
	costo float not null default 0,
	UNIQUE (ordineId, prodottoId)
);

CREATE TABLE prodotto (
	id integer not null primary key asc autoincrement,
	nome varchar(30) not null,
	descrizione varchar not null,
	prezzo float not null
);

vSJbCvv/YVMsFrqigmfmHLn6fPc4/zq6HbIOQIedTDY=$e7J3s1DPl91c/5ntRuVb9/0HvKZ3wEv/CogNKaaNCEg=