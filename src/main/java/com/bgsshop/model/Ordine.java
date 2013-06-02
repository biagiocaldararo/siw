package com.bgsshop.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Ordine {
	private long id;
	private Date data;
	private Utente cliente;
	private String stato;
	private double importo;
	private List<RigaOrdine> righeOrdine;
	
	public Ordine(Utente cliente){
		this.setCliente(cliente);
		this.setStato("aperto");
		this.righeOrdine = new LinkedList<RigaOrdine>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Utente getCliente() {
		return cliente;
	}

	public void setCliente(Utente cliente) {
		this.cliente = cliente;
	}

	public List<RigaOrdine> getRigheOrdine() {
		return righeOrdine;
	}

	public void setRigheOrdine(List<RigaOrdine> righeOrdine) {
		this.righeOrdine = righeOrdine;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public void aggiungiRiga(Prodotto prodotto, int quantita){
		RigaOrdine rigaOrdine = new RigaOrdine(this, prodotto, quantita);
		this.righeOrdine.add(rigaOrdine);
		this.importo += rigaOrdine.getCosto();
	}
	
	public void eliminaRiga(long id){
		for(RigaOrdine riga: this.righeOrdine){
			if(riga.getProdotto().getId()==id) {
				righeOrdine.remove(riga);
				this.importo -= riga.getCosto();
			}
		}
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}
	
	public int getNumeroProdotti(){
		int numeroProdotti = 0;
		
		for(RigaOrdine r: this.righeOrdine)
			numeroProdotti += r.getQuantita();
		
		return numeroProdotti;
	}
}
