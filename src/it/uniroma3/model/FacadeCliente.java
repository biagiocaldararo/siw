package it.uniroma3.model;

import it.uniroma3.persistence.postgres.*;

public class FacadeCliente {
	public Cliente trovaUtente(String username){
		ClienteDAOPostgres u = new ClienteDAOPostgres();
		return u.findByUsername(username);
	}
}
