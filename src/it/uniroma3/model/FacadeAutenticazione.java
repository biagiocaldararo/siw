package it.uniroma3.model;

import it.uniroma3.persistence.postgres.*;

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
