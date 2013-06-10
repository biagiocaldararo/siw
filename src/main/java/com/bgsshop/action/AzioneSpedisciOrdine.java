package com.bgsshop.action;

import com.bgsshop.model.Ordine;
import com.bgsshop.mvc.AzioneDettaglio;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class AzioneSpedisciOrdine extends AzioneDettaglio<Ordine> {

	public int get() {
		Ordine ordine = getObject();
		ordine.setStato("evaso");
		ordine.save();
		return redirect("/bgsshop/admin/ordini");
	}
	
	@Override
	public String getRuolo() {
		return "admin";
	}

	@Override
	protected DAO<Ordine> getDAO() {
		return DAOFactory.getDAOFactory().getOrdineDAO();
	}

	@Override
	protected Class<Ordine> getModel() {
		return Ordine.class;
	}

}
