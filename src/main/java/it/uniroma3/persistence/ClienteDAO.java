package it.uniroma3.persistence;

import it.uniroma3.model.*;

import java.util.List;

public interface ClienteDAO {	
	boolean insert(Cliente cliente); 
	boolean delete(Cliente cliente); 
	boolean update(Cliente utente);
	Cliente findByUsername(String username);
 	List<Cliente> findAll();
}
