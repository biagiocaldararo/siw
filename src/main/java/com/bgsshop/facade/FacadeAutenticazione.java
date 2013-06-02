package com.bgsshop.facade;

import com.bgsshop.model.*;
import com.bgsshop.persistence.ClienteDAO;
import com.bgsshop.persistence.sqlite.ClienteDAOSQLite;


public class FacadeAutenticazione {
	
	public Cliente autentica(String username, String password){
		ClienteDAO dao = new ClienteDAOSQLite();
		Cliente cliente = dao.findByUsername(username);
		
		if(cliente!=null && checkPassword(password, cliente.getPassword())) 
			return cliente;
		
		return null;
	}
	
	// TODO: una volta che le password degli utenti non sono memorizzate in chiaro
	// nel database, bisogna calcolare l'hash della password e confrontarlo con
	// quello memorizzato nel db.
	public boolean checkPassword(String password, String hash) {
		return password.equals(hash);
	}
	
	public Cliente getUser(Long id) {
		return null;
	}
}
