package com.bgsshop.persistence;

import java.util.List;

import com.bgsshop.model.Ordine;
import com.bgsshop.model.RigaOrdine;

public interface RigaOrdineDAO {
	
	boolean insert(RigaOrdine rigaOrdine);
	boolean delete(RigaOrdine rigaOrdine);
	boolean update(RigaOrdine rigaOrdine);
	List<RigaOrdine> findByOrdine(Ordine ordine);

}
