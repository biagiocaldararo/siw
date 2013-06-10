package com.bgsshop.action;

import com.bgsshop.model.Prodotto;
import com.bgsshop.mvc.AzioneDettaglio;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class AzioneDettaglioProdotto extends AzioneDettaglio<Prodotto> {
	
	public AzioneDettaglioProdotto() {
		setTemplate("dettaglioProdotto.jsp");
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
		return "cliente";
	}
}

