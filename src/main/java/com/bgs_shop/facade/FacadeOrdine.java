package com.bgs_shop.facade;

import com.bgs_shop.model.Ordine;
import com.bgs_shop.persistence.OrdineDAO;
import com.bgs_shop.persistence.sqlite.OrdineDAOSQLite;

public class FacadeOrdine {
	
	public boolean inserisciOrdine(Ordine ordine){
		OrdineDAO dao = new OrdineDAOSQLite();
		return dao.insert(ordine);
	}
}
