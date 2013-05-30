package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.bgsshop.facade.FacadeProdotto;
import com.bgsshop.model.Prodotto;

public class AzioneDettagliProdotto extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {

		long cod;
		try {
			cod = Long.parseLong(request.getParameter("cod"));
		} catch (NumberFormatException e) {
			return "dettaglioProdotto";
		}
		
		Prodotto p = new FacadeProdotto().findByCod(cod);
		
		request.setAttribute("prodotto", p);
		return "dettaglioProdotto";
	}
}
