package com.bgsshop.persistence;

import java.util.List;

import com.bgsshop.model.Cliente;
import com.bgsshop.model.Ordine;
import com.bgsshop.model.Prodotto;

public interface OrdineDAO {
	boolean insert(Ordine Ordine); 
	boolean delete(Ordine Ordine); 
	boolean update(Ordine Ordine);
	Prodotto findById(long id);
 	List<Ordine> findByCliente(Cliente cliente);
}
