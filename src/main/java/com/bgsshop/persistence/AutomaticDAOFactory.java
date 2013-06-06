package com.bgsshop.persistence;

import com.bgsshop.model.Ordine;
import com.bgsshop.model.Prodotto;
import com.bgsshop.model.RigaOrdine;
import com.bgsshop.model.Utente;

public class AutomaticDAOFactory extends DAOFactory {

	private static final DAO<Utente> utenteDAO = new AutomaticDAO<Utente>(Utente.class);
	private static final DAO<Ordine> ordineDAO = new AutomaticDAO<Ordine>(Ordine.class);
	private static final DAO<Prodotto> prodottoDAO = new AutomaticDAO<Prodotto>(Prodotto.class);
	private static final DAO<RigaOrdine> rigaOrdineDAO = new AutomaticDAO<RigaOrdine>(RigaOrdine.class);
	
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
