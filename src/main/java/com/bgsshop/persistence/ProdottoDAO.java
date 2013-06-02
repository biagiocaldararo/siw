package com.bgsshop.persistence;

import java.util.*; 

import com.bgsshop.model.*;


public interface ProdottoDAO {	
	boolean insert(Prodotto prodotto); 
	boolean delete(Prodotto prodotto); 
	boolean update(Prodotto prodotto);
	Prodotto findById(long id);
 	List<Prodotto> findAll();
}
