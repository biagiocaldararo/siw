package com.bgs_shop.facade;

import com.bgs_shop.model.*;
import com.bgs_shop.persistence.ClienteDAO;
import com.bgs_shop.persistence.sqlite.ClienteDAOSQLite;


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
