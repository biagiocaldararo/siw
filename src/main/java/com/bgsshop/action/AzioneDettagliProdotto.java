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
			id = Long.valueOf(request.getParameter("id"));
		} catch (NumberFormatException e) {
			return "erroreInserimento";
		}
		
		Prodotto p = FacadeProdotto.getProdotto(id);
		
		request.setAttribute("prodotto", p);
		return "dettagliProdotto";
	}
}
