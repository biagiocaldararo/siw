package com.bgsshop.persistence;

import java.util.*; 

import com.bgsshop.model.*;

// TODO: da eliminare
public interface ProdottoDAO {	
	void insert(Prodotto prodotto); 
	void delete(Prodotto prodotto); 
	void update(Prodotto prodotto);
	Prodotto findById(long id);
 	List<Prodotto> all();
}
