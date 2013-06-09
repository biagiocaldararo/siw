package com.bgsshop.facade;

import java.util.List;

import com.bgsshop.model.RigaOrdine;
import com.bgsshop.model.Utente;
import com.bgsshop.model.Ordine;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class FacadeOrdine {
	
	public boolean inserisciOrdine(Ordine ordine){
		DAO<Ordine> dao = DAOFactory.getDAOFactory().getOrdineDAO();
		return dao.insert(ordine);
	}
	
	public boolean inserisciRigaOrdine(RigaOrdine rigaOrdine){
		DAO<RigaOrdine> dao = DAOFactory.getDAOFactory().getRigaOrdineDAO();
		return dao.insert(rigaOrdine);
	}
	
	public List<Ordine> getOrdiniUtente(Utente utente){
		DAO<Ordine> dao = DAOFactory.getDAOFactory().getOrdineDAO();
		return dao.findByObject(utente);
	}
}
