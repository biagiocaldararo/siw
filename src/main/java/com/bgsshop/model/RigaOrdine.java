package com.bgsshop.model;

import com.bgsshop.persistence.Column;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;
import com.bgsshop.persistence.Column.ColumnType;


public class RigaOrdine {

	/* Dati provenienti dal DB */
	@Column(ColumnType.ID) private Number id;
	@Column private Number ordineId;
	@Column private Number prodottoId;
	@Column private Integer quantita;
	
	/* Campi lazy */
	private Ordine ordine;
	private Prodotto prodotto;	
	
	public RigaOrdine() {};
	public RigaOrdine(Number ordineId, Number prodottoId, Integer quantita) {
		this.ordineId = ordineId;
		this.prodottoId = prodottoId;
		this.quantita = quantita;
	}
	
	public Ordine getOrdine() {
		if (ordineId != null && (ordine == null || ordine.getId() != ordineId)) {
			DAO<Ordine> dao = DAOFactory.getDAOFactory().getOrdineDAO();
			ordine = dao.findOne("id", ordineId);
		}
		return ordine;
	}
	
	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
		this.ordineId = ordine.getId();
	}
	
	public Prodotto getProdotto() {
		if (prodottoId != null && (prodotto == null || prodotto.getId() != prodottoId)) {
			DAO<Prodotto> dao = DAOFactory.getDAOFactory().getProdottoDAO();
			prodotto = dao.findOne("id", prodottoId);
		}
		return prodotto;
	}
	
	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
		this.prodottoId = prodotto.getId();
	}

	public void incrementaQuantita(int quantita) {
		if (this.quantita == null)
			this.quantita = quantita;
		else
			this.quantita += quantita;
	}
	
	public Number getId() {
		return id;
	}

	public Number getOrdineId() {
		return ordineId;
	}

	public void setOrdineId(Number ordineId) {
		this.ordineId = ordineId;
	}

	public Number getProdottoId() {
		return prodottoId;
	}

	public void setProdottoId(Number prodottoId) {
		this.prodottoId = prodottoId;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

}
