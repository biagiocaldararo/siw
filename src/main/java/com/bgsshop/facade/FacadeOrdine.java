package com.bgsshop.facade;

import java.util.List;

import com.bgsshop.model.RigaOrdine;
import com.bgsshop.model.Utente;
import com.bgsshop.model.Ordine;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class FacadeOrdine {
	private static final DAO<Ordine> O_DAO = DAOFactory.getDAOFactory().getOrdineDAO();
	private static final DAO<RigaOrdine> R_DAO = DAOFactory.getDAOFactory().getRigaOrdineDAO();
	
	public static boolean inserisciOrdine(Ordine ordine){
		return O_DAO.insert(ordine);
	}
	
	public static boolean inserisciRigaOrdine(RigaOrdine rigaOrdine){
		return R_DAO.insert(rigaOrdine);
	}
	
	public static List<Ordine> getOrdiniUtente(Utente utente){
		return O_DAO.findByObject(utente);
	}
	
	public static Ordine getOrdine(long id){
		return O_DAO.findById(id);
	}
	
	public static boolean aggiornaOrdine(Ordine ordine){
		return O_DAO.update(ordine);
	}
}
