package it.uniroma3.model;

import it.uniroma3.persistence.postgres.*;

public class FacadeAutenticazione {
	public Cliente login (String username, String Password){
		ClienteDAOPostgres u = new ClienteDAOPostgres();
		return u.findByUsername(username);
	}
}
