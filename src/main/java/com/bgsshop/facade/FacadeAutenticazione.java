package com.bgsshop.facade;

import com.bgsshop.model.*;
import com.bgsshop.persistence.UtenteDAO;
import com.bgsshop.persistence.sqlite.UtenteDAOSQLite;


public class FacadeAutenticazione {
	public Utente login (String username, String password){
		UtenteDAO dao = new UtenteDAOSQLite();
		Utente utente = dao.findByUsername(username);
		boolean autenticato = true;
		
		if(utente!=null) 
			autenticato &= utente.getPassword().equals(password);
		
		if(!autenticato)
			utente = null;
		
		return utente;
	}
}
