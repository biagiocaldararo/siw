package com.bgsshop.facade;

import com.bgsshop.Password;
import com.bgsshop.model.*;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class FacadeAutenticazione {
	
	public Utente autentica(String username, String password){
		DAO<Utente> dao = DAOFactory.getDAOFactory().getUtenteDAO();
		
		try {
			Utente utente = dao.findOne("username", username);
			if (Password.check(password, utente.getPassword()))
				return utente;
		} catch (Exception e) {
			throw new RuntimeException("Errore nell'autenticazione", e);
		}
		return null;
	}
		
}

