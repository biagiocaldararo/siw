package com.bgsshop.persistence;

import java.util.List;

import com.bgsshop.model.Ordine;

// TODO: da eliminare
public interface OrdineDAO {
	void insert(Ordine ordine); 
	void delete(Ordine ordine); 
	void update(Ordine ordine);
	List<Ordine> all();
}
