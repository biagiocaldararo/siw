package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.bgsshop.facade.FacadeProdotto;
import com.bgsshop.model.Prodotto;

public class AzioneDettaglioProdotto extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		
		String[] parametri = (String[]) request.getAttribute("parametri");

		long cod;
		try {
			cod = Long.parseLong(parametri[0]);
		} catch (NumberFormatException e) {
			return "notfound.jsp";
		}
		
		// TODO: questo metodo dovrebbe sollevare un'eccezione se non trova il prodotto.
		Prodotto p = new FacadeProdotto().findByCod(cod);
		if (p == null)
			return "notfound.jsp";
		
		request.setAttribute("prodotto", p);
		return "dettaglioProdotto.jsp";
	}
}