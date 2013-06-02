package com.bgsshop.facade;

import com.bgsshop.model.*;
import com.bgsshop.persistence.UtenteDAO;
import com.bgsshop.persistence.sqlite.UtenteDAOSQLite;


public class FacadeAutenticazione {
	public Utente login (String username, String password){
		UtenteDAO dao = new UtenteDAOSQLite();
		Utente cliente = dao.findByUsername(username);
		boolean autenticato = true;
		
		if(cliente!=null) 
			autenticato &= cliente.getPassword().equals(password);
		
		if(!autenticato)
			cliente = null;
		
		return cliente;
	}
}
