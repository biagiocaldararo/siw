package com.bgsshop.persistence;

import java.util.List;

import com.bgsshop.model.Utente;
import com.bgsshop.model.Ordine;
import com.bgsshop.model.Prodotto;

public interface OrdineDAO {
	boolean insert(Ordine Ordine, long idCliente); 
	boolean delete(Ordine Ordine); 
	boolean update(Ordine Ordine);
	Prodotto findById(long id);
 	List<Ordine> findByCliente(Utente cliente);
}
