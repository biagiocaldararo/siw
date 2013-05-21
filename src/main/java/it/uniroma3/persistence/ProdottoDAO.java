package it.uniroma3.persistence;

import java.util.*; 

import it.uniroma3.model.*;

public interface ProdottoDAO {	
	boolean insert(Prodotto prodotto); 
	boolean delete(Prodotto prodotto); 
	boolean update(Prodotto prodotto);
	Prodotto findByCod(long cod);
 	List<Prodotto> findAll();
}
