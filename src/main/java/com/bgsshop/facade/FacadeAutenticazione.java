package com.bgsshop.facade;

import com.bgsshop.model.*;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class FacadeAutenticazione {
	private static final DAO<Utente> dao = DAOFactory.getDAOFactory().getUtenteDAO();
	
	public static Utente login (String username, String password){
		Utente utente = dao.findByString(username);
		boolean autenticato = true;
		
		if(utente!=null) 
			autenticato &= utente.getPassword().equals(password);
		
		if(!autenticato)
			utente = null;
		
		return utente;
	}
}
