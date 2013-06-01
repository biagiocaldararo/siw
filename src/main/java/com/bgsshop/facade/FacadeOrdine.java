package com.bgsshop.facade;

import com.bgsshop.model.Cliente;
import com.bgsshop.model.Ordine;
import com.bgsshop.persistence.OrdineDAO;
import com.bgsshop.persistence.sqlite.OrdineDAOSQLite;

public class FacadeOrdine {
	
	public boolean inserisciOrdine(Ordine ordine, Cliente cliente){
		OrdineDAO dao = new OrdineDAOSQLite();
		return dao.insert(ordine, cliente.getId());
	}
}
