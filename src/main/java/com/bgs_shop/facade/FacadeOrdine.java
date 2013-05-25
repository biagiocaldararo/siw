package com.bgs_shop.facade;

import com.bgs_shop.model.Ordine;
import com.bgs_shop.persistence.postgres.OrdineDAOPostgres;

public class FacadeOrdine {
	
	public boolean inserisciOrdine(Ordine ordine){
		OrdineDAOPostgres dao = new OrdineDAOPostgres();
		return dao.insert(ordine);
	}
}
