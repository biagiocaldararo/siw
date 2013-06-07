package com.bgsshop.persistence;

import java.util.List;

import com.bgsshop.model.*;

// TODO: da eliminare
public interface UtenteDAO {	
	boolean insert(Utente cliente); 
	boolean delete(Utente cliente); 
	boolean update(Utente utente);
	Utente findByUsername(String username);
 	List<Utente> findAll();
}
