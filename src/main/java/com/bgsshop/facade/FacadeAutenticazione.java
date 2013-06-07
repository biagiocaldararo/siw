package com.bgsshop.facade;

import com.bgsshop.model.*;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;


public class FacadeAutenticazione {
	public Utente login (String username, String password){
		DAO<Utente> dao = DAOFactory.getDAOFactory().getUtenteDAO();
		Utente utente = dao.findOne("username", username);
		boolean autenticato = true;
		
		if(utente!=null) 
			autenticato &= utente.getPassword().equals(password);
		
		if(!autenticato)
			utente = null;
		
		return utente;
	}
}

