package com.bgsshop.facade;

import com.bgsshop.model.Utente;
import com.bgsshop.model.Ordine;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class FacadeOrdine {
	
	public boolean inserisciOrdine(Ordine ordine, Utente utente){
		DAO<Ordine> dao = DAOFactory.getDAOFactory().getOrdineDAO();
		dao.insert(ordine);
		return true;
	}
}
