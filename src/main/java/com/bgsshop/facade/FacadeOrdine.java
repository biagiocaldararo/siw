package com.bgsshop.facade;

import com.bgsshop.model.Utente;
import com.bgsshop.model.Ordine;
import com.bgsshop.persistence.OrdineDAO;
import com.bgsshop.persistence.sqlite.OrdineDAOSQLite;

public class FacadeOrdine {
	
	public boolean inserisciOrdine(Ordine ordine, Utente cliente){
		OrdineDAO dao = new OrdineDAOSQLite();
		return dao.insert(ordine, cliente.getId());
	}
}
