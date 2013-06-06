package com.bgsshop.model;

import com.bgsshop.persistence.Column;
import com.bgsshop.persistence.Column.ColumnType;

public class Prodotto {
	@Column(ColumnType.ID) private Long id;
	@Column private String nome;
	@Column private String descrizione;
	@Column private double prezzo;
	
	public Prodotto(Number id) { this.id = id.longValue(); }
	public Prodotto(){}
	
	public Prodotto(long id, String nome, String descrizione, double prezzo){
		this.setId(id);
		this.setNome(nome);
		this.setDescrizione(descrizione);
		this.setPrezzo(prezzo);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
}
