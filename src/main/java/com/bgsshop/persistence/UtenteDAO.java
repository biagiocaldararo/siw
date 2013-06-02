package com.bgsshop.persistence;

import java.util.List;

import com.bgsshop.model.*;

public interface UtenteDAO {	
	boolean insert(Utente cliente); 
	boolean delete(Utente cliente); 
	boolean update(Utente utente);
	Utente findByUsername(String username);
 	List<Utente> findAll();
}
