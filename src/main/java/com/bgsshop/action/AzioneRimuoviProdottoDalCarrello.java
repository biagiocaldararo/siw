package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bgsshop.model.Ordine;
import com.bgsshop.model.Prodotto;
import com.bgsshop.model.RigaOrdine;

public class AzioneRimuoviProdottoDalCarrello implements Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		Ordine ordineCorrente = (Ordine) sessione.getAttribute("ordineCorrente");
		
		long id;
		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e) {
			return "carrello";
		}
		
		for (RigaOrdine riga : ordineCorrente.getRigheOrdine()) {
			Prodotto p = riga.getProdotto();
			if (p.getId() == id) {
				ordineCorrente.eliminaRiga(id);
				int n = (Integer) sessione.getAttribute("numeroProdotti");
				sessione.setAttribute("numeroProdotti", n - riga.getQuantita());
				break;
			}
		}
		
		return "carrello";
	}
}
