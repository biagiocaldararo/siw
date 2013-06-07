package com.bgsshop.facade;

import com.bgsshop.model.RigaOrdine;
import com.bgsshop.model.Utente;
import com.bgsshop.model.Ordine;
import com.bgsshop.persistence.OrdineDAO;
import com.bgsshop.persistence.RigaOrdineDAO;
import com.bgsshop.persistence.sqlite.OrdineDAOSQLite;
import com.bgsshop.persistence.sqlite.RigaOrdineDAOSQLite;

public class FacadeOrdine {
	
	public boolean inserisciOrdine(Ordine ordine, Utente utente){
		OrdineDAO dao = new OrdineDAOSQLite();
		return dao.insert(ordine);
	}
	
	public boolean inserisciRigaOrdine(RigaOrdine rigaOrdine){
		RigaOrdineDAO dao = new RigaOrdineDAOSQLite();
		return dao.insert(rigaOrdine);
	}
}
