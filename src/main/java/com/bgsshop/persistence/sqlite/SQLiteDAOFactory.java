package com.bgsshop.persistence.sqlite;

import com.bgsshop.model.Ordine;
import com.bgsshop.model.Prodotto;
import com.bgsshop.model.RigaOrdine;
import com.bgsshop.model.Utente;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class SQLiteDAOFactory extends DAOFactory {

	private static final DAO<Utente> utenteDAO = new UtenteDAOSQLite();
	private static final DAO<Ordine> ordineDAO = new OrdineDAOSQLite();
	private static final DAO<Prodotto> prodottoDAO = new ProdottoDAOSQLite();
	private static final DAO<RigaOrdine> rigaOrdineDAO = new RigaOrdineDAOSQLite();
	
	@Override
	public DAO<Utente> getUtenteDAO() {
		return utenteDAO;
	}

	@Override
	public DAO<Ordine> getOrdineDAO() {
		return ordineDAO;
	}

	@Override
	public DAO<Prodotto> getProdottoDAO() {
		return prodottoDAO;
	}

	@Override
	public DAO<RigaOrdine> getRigaOrdineDAO() {
		return rigaOrdineDAO;
	}

}
