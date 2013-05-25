package com.bgs_shop.model;

import com.bgs_shop.persistence.postgres.*;

public class FacadeAutenticazione {
	public Cliente login (String username, String password){
		ClienteDAOPostgres dao = new ClienteDAOPostgres();
		Cliente trovato = dao.findByUsername(username);
		Cliente cliente = null;
		
		if(trovato!=null && trovato.getPassword().equals(password))
			cliente = trovato;
		
		return cliente;
	}
}
