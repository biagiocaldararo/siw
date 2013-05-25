package com.bgs_shop.persistence;

import java.util.List;

import com.bgs_shop.model.Cliente;
import com.bgs_shop.model.Ordine;
import com.bgs_shop.model.Prodotto;

public interface OrdineDAO {
	boolean insert(Ordine Ordine); 
	boolean delete(Ordine Ordine); 
	boolean update(Ordine Ordine);
	Prodotto findByCod(long cod);
 	List<Ordine> findByCliente(Cliente cliente);
}
