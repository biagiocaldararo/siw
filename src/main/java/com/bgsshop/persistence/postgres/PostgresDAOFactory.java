package com.bgsshop.persistence.postgres;

import com.bgsshop.model.Ordine;
import com.bgsshop.model.Prodotto;
import com.bgsshop.model.RigaOrdine;
import com.bgsshop.model.Utente;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class PostgresDAOFactory extends DAOFactory {

	private static final DAO<Utente> utenteDAO = new UtenteDAOPostgres();
	private static final DAO<Ordine> ordineDAO = new OrdineDAOPostgres();
	private static final DAO<Prodotto> prodottoDAO = new ProdottoDAOPostgres();
	private static final DAO<RigaOrdine> rigaOrdineDAO = new RigaOrdineDAOPostgres();
	
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
