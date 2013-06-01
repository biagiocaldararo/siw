package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.bgsshop.facade.FacadeProdotto;
import com.bgsshop.model.Prodotto;

public class AzioneDettagliProdotto extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		long id;
		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e) {
			return "dettaglioProdotto";
		}
		
		Prodotto p = new FacadeProdotto().findById(id);
		
		request.setAttribute("prodotto", p);
		return "dettaglioProdotto";
	}
}
