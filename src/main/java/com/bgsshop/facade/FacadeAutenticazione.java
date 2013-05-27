package com.bgsshop.facade;

import com.bgsshop.model.*;
import com.bgsshop.persistence.ClienteDAO;
import com.bgsshop.persistence.sqlite.ClienteDAOSQLite;


public class FacadeAutenticazione {
	public Cliente login (String username, String password){
		ClienteDAO dao = new ClienteDAOSQLite();
		Cliente cliente = dao.findByUsername(username);
		boolean autenticato = true;
		
		if(cliente!=null) 
			autenticato &= cliente.getPassword().equals(password);
		
		if(!autenticato)
			cliente = null;
		
		return cliente;
	}
}
