package com.bgsshop.action;

import com.bgsshop.helper.Helper;
import com.bgsshop.helper.ProdottoHelper;
import com.bgsshop.model.Prodotto;
import com.bgsshop.mvc.AzioneAggiungi;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class AzioneAggiungiProdotto extends AzioneAggiungi<Prodotto> {
	
	public AzioneAggiungiProdotto() {
		setTemplate("admin/gestioneProdotto.jsp");
	}

	@Override
	protected Helper<Prodotto> getHelper() {
		return new ProdottoHelper(request);
	}

	@Override
	protected String getSuccessURL() {
		return "/bgsshop/prodotti";
	}

	@Override
	protected DAO<Prodotto> getDAO() {
		return DAOFactory.getDAOFactory().getProdottoDAO();
	}

	@Override
	protected Class<Prodotto> getModel() {
		return Prodotto.class;
	}

	@Override
	public String getRuolo() {
		return "admin";
	}

}
