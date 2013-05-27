package com.bgsshop.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Ordine {
	private long cod;
	private Date data;
	private Cliente cliente;
	private String stato;
	private double importo;
	private List<RigaOrdine> righeOrdine;
	
	public Ordine(Cliente cliente){
		this.setCliente(cliente);
		this.setStato("aperto");
		this.righeOrdine = new LinkedList<RigaOrdine>();
	}

	public long getCod() {
		return cod;
	}

	public void setCod(long cod) {
		this.cod = cod;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
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
	}
	
	public void eliminaRiga(long cod){
		for(RigaOrdine riga: this.righeOrdine){
			if(riga.getProdotto().getCod()==cod)
				righeOrdine.remove(riga);
		}
	}

	public double getImporto() {
		this.setImporto(0);
		
		for(RigaOrdine r: this.righeOrdine)
			this.importo += r.getCosto();
		
		return this.importo;
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
