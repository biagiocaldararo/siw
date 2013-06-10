package com.bgsshop.action;

import com.bgsshop.helper.Helper;
import com.bgsshop.helper.NuovoUtenteHelper;
import com.bgsshop.model.Utente;
import com.bgsshop.mvc.AzioneAggiungi;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;


public class AzioneAggiungiUtente extends AzioneAggiungi<Utente> {
	
	public AzioneAggiungiUtente() {
		setTemplate("admin/aggiungiUtente.jsp");
	}
	
	@Override
	protected Helper<Utente> getHelper() {
		return new NuovoUtenteHelper(request);
	}

	@Override
	protected String getSuccessURL() {
		return "/bgsshop/admin";
	}

	@Override
	protected DAO<Utente> getDAO() {
		return DAOFactory.getDAOFactory().getUtenteDAO();
	}

	@Override
	protected Class<Utente> getModel() {
		return Utente.class;
	}

	@Override
	public String getRuolo() {
		return "admin";
	}
}