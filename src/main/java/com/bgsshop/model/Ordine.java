package com.bgsshop.model;

import java.util.Date;
import java.util.List;

import com.bgsshop.persistence.Column;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;
import com.bgsshop.persistence.ModelNotFoundException;
import com.bgsshop.persistence.Column.ColumnType;

public class Ordine extends Model {
	
	public enum Stato {
		APERTO, CHIUSO, EVASO;
	}
	
	/* Dati provenienti dal DB */
	@Column(ColumnType.ID) private Number id;
	@Column private Number utenteId;
	@Column private Long data;
	@Column private String stato;
	@Column private Double totale;
	@Column private Integer numProdotti;

	/* Campi lazy */
	private Utente utente;
	
	public Ordine() {};
	
	public Utente getUtente() {
		if (utenteId != null && (utente == null || utente.getId() != utenteId)) {
			DAO<Utente> dao = DAOFactory.getDAOFactory().getUtenteDAO();
			utente = dao.findOne("id", utenteId);
		}
		return utente;
	}
	
	public void setUtente(Utente utente) {
		this.utente = utente;
		this.utenteId = utente.getId();
	}
	
	public Date getData() {
		if (data != null)
			return new Date(data);
		return null;
	}

	public void setData(Date data) {
		this.data = data.getTime();
	}
	
	public List<RigaOrdine> getRighe() {
		DAO<RigaOrdine> dao = DAOFactory.getDAOFactory().getRigaOrdineDAO();
		return dao.findBy("ordineId", id);
	}
	
	public void add(long prodotto, int quantita) {
		// TODO non settare la quantità negativa
		// a zero rimuovere la riga
		RigaOrdine query = new RigaOrdine(id, prodotto, null);
		DAO<RigaOrdine> dao = DAOFactory.getDAOFactory().getRigaOrdineDAO();
		RigaOrdine riga;
		try {
			riga = dao.get(query);
		} catch (ModelNotFoundException e) {
			riga = query;
		}
		riga.incrementaQuantita(quantita);
		dao.save(riga);
		
		this.numProdotti += quantita;
		this.totale += quantita * riga.getProdotto().getPrezzo();	
	}

	public void del(long prodotto) {
		RigaOrdine query = new RigaOrdine(id, prodotto, null);
		DAO<RigaOrdine> dao = DAOFactory.getDAOFactory().getRigaOrdineDAO();
		RigaOrdine riga = dao.get(query);
		this.numProdotti -= riga.getQuantita();
		this.totale -= riga.getQuantita() * riga.getProdotto().getPrezzo();
		dao.delete(riga);
	}
	
	/* metodo di utilità */
	public void save() {
		DAO<Ordine> dao = DAOFactory.getDAOFactory().getOrdineDAO();
		dao.save(this);
	}

	
	/* getter e setter */

	public Number getId() {
		return id;
	}

	public void setId(Number id) {
		this.id = id;
	}

	public Number getUtenteId() {
		return utenteId;
	}

	public void setUtenteId(Number utenteId) {
		this.utenteId = utenteId;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Double getTotale() {
		return totale;
	}

	public void setTotale(Double importo) {
		this.totale = importo;
	}
	
	public Integer getNumProdotti() {
		return numProdotti;
	}

	public void setNumProdotti(Integer numProdotti) {
		this.numProdotti = numProdotti;
	}

}
