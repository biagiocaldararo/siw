package com.bgsshop.action;

import com.bgsshop.model.Ordine;
import com.bgsshop.mvc.AzioneProtetta;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class AzioneAmministrazioneOrdini extends AzioneProtetta {
	
	@Override
	public String getRuolo() {
		return "cliente";
	}
	
	public int get() {
		DAO<Ordine> dao = DAOFactory.getDAOFactory().getOrdineDAO();
		request.setAttribute("ordini", dao.findAll());
		return ok("admin/ordini.jsp");
	}

}
