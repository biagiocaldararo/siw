package com.bgsshop.persistence;

import com.bgsshop.model.*;
import com.bgsshop.persistence.sqlite.SQLiteDAOFactory;

public abstract class DAOFactory {

	// Basta modificare questa riga per cambiare Factory.
	private static final DAOFactory factory = new AutomaticDAOFactory();
	
	public static DAOFactory getDAOFactory() {
		return factory;
	}
	
	public abstract DAO<Utente> getUtenteDAO();
	public abstract DAO<Ordine> getOrdineDAO();
	public abstract DAO<Prodotto> getProdottoDAO();
	public abstract DAO<RigaOrdine> getRigaOrdineDAO();
	
}
