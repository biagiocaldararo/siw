package com.bgsshop.persistence;

import java.util.List;

import com.bgsshop.model.*;

public interface ClienteDAO {	
	boolean insert(Cliente cliente); 
	boolean delete(Cliente cliente); 
	boolean update(Cliente utente);
	Cliente findByUsername(String username);
 	List<Cliente> findAll();
}
