package com.bgs_shop.model;

public class RigaOrdine {
	private Ordine ordine;
	private Prodotto prodotto;
	private int quantita;
	private double costo;
	
	public RigaOrdine (Ordine ordine, Prodotto prodotto, int quantita){
		this.setOrdine(ordine);
		this.setProdotto(prodotto);
		this.setQuantita(quantita);
		this.setCosto(this.quantita*this.prodotto.getPrezzo());
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
		this.setQuantita(quantita);
		this.setCosto(this.quantita*this.prodotto.getPrezzo());
	}
}