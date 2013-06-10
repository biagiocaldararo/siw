package com.bgsshop.action;

import com.bgsshop.helper.Helper;
import com.bgsshop.helper.UtenteHelper;
import com.bgsshop.model.Utente;
import com.bgsshop.mvc.AzioneModifica;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;



public class AzioneModificaUtente extends AzioneModifica<Utente> {
	
	public AzioneModificaUtente() {
		setTemplate("admin/modificaUtente.jsp");
	}
	
	@Override
	protected Helper<Utente> getHelper() {
		return new UtenteHelper(request, getObject());
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