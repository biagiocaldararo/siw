package com.bgsshop.model;

import com.bgsshop.persistence.Column;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;
import com.bgsshop.persistence.Column.ColumnType;


public class RigaOrdine {
	
	@Column(ColumnType.ID) Long id;
	@Column private Number ordine;
	@Column private Number prodotto;
	@Column private Integer quantita;
	@Column private Double costo;
	
	public RigaOrdine() {};
	public RigaOrdine(Number ordine) {
		this.ordine = ordine;
	}
	public RigaOrdine(Number ordine, Number prodotto) {
		this(ordine);
		this.prodotto = prodotto;
	}
	public RigaOrdine(Number ordine, Number prodotto, int quantita) {
		this(ordine, prodotto);
		this.quantita = quantita;
	}
	
	public RigaOrdine(Ordine ordine, Prodotto prodotto, int quantita){
		this.setOrdine(ordine);
		this.setProdotto(prodotto);
		this.setQuantita(quantita);
		//this.setCosto(this.quantita*this.prodotto.getPrezzo());
	}

	public Ordine getOrdine() {
		DAO<Ordine> ordineDAO = DAOFactory.getDAOFactory().getOrdineDAO();
		return ordineDAO.get(new Ordine(ordine));
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine.getId();
	}

	public Prodotto getProdotto() {
		DAO<Prodotto> prodottoDAO = DAOFactory.getDAOFactory().getProdottoDAO();
		return prodottoDAO.get(new Prodotto(prodotto));
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto.getId();
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	public void aggiornaRiga(int quantita){
//		this.ordine.setImporto(this.ordine.getImporto()-this.costo);
//		this.quantita = quantita;
//		this.costo = quantita*this.prodotto.getPrezzo();
//		this.ordine.setImporto(this.ordine.getImporto()+this.costo);
		throw new UnsupportedOperationException("aggiornaRiga non è implementato :(");
	}
	
	@Override
	public String toString() {
		return String.format("RigaOrdine(%s, %s, %s)", ordine, prodotto, quantita);
	}
}
