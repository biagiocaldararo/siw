package com.bgsshop.action;

import com.bgsshop.model.Ordine;
import com.bgsshop.mvc.AzioneProtetta;
import com.bgsshop.mvc.ForbiddenException;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class AzioneDettaglioOrdine extends AzioneProtetta {

	@Override
	public String getRuolo() {
		return "cliente";
	}
	
	public int get() {
		
		DAO<Ordine> dao = DAOFactory.getDAOFactory().getOrdineDAO();
		Ordine ordine = dao.findOne("id", Long.parseLong(params[0]));
		
		if (ordine.getUtenteId() != utente.getId() && !utente.isAdmin())
			throw new ForbiddenException();
		
		request.setAttribute("ordine", ordine);	
		return ok("dettaglioOrdine.jsp");
	}

}
