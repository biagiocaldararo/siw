package com.bgsshop.model;

public class RigaOrdine {
	private Long id;
	private Ordine ordine;
	private Prodotto prodotto;
	private int quantita;
	private double costo;
	
	public RigaOrdine (Ordine ordine, Prodotto prodotto, int quantita){
		this.setId(null);
		this.setOrdine(ordine);
		this.setProdotto(prodotto);
		this.setQuantita(quantita);
		this.setCosto(this.quantita*this.prodotto.getPrezzo());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
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
		this.ordine.setImporto(this.ordine.getImporto()-this.costo);
		this.quantita = quantita;
		this.costo = quantita*this.prodotto.getPrezzo();
		this.ordine.setImporto(this.ordine.getImporto()+this.costo);
	}
}
