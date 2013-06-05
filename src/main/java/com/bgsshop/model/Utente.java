package com.bgsshop.model;

import com.bgsshop.persistence.AutomaticDAO;
import com.bgsshop.persistence.Column;
import com.bgsshop.persistence.Column.ColumnType;
import com.bgsshop.persistence.DAO;

public class Utente {
	
	@Column(ColumnType.ID) private Long id;
	@Column private String username;
	@Column private String password;
	private String nome;
	private String cognome;
	private String email;
	private String indirizzo;
	@Column private String ruolo;
	
	public static DAO<Utente> dao = new AutomaticDAO<Utente>(Utente.class);
	
	public Utente() {};
	
	// TODO: l'id non dovrebbe essere settato manualmente, ma in maniera automatica dal DAO.
	public Utente(Long id, String username, String password, String ruolo) {
		this.setId(id);
		this.setUsername(username);
		this.setPassword(password);
		this.setRuolo(ruolo);
	}
	public Utente(String username, String password, String ruolo) {
		this.setUsername(username);
		this.setPassword(password);
		this.setRuolo(ruolo);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	@Override
	public String toString() {
		return String.format("<Utente(%s, %s):%d>", username, ruolo, id);
	}
}
