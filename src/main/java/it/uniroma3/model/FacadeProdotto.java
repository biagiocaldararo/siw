package it.uniroma3.model;

import java.util.*;

import it.uniroma3.persistence.postgres.*;

public class FacadeProdotto {
	public List<Prodotto> getProdotti(){
		ProdottoDAOPostgres p = new ProdottoDAOPostgres();
		return p.findAll();
	}
	
	public boolean inserisciProdotto(String nome, String descrizione, double prezzo){
		return true;
	}
	
}
