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
		
		long cod;
		try {
			cod = Long.parseLong(request.getParameter("cod"));
		} catch (NumberFormatException e) {
			return "carrello";
		}
		
		for (RigaOrdine riga : ordineCorrente.getRigheOrdine()) {
			Prodotto p = riga.getProdotto();
			if (p.getCod() == cod) {
				ordineCorrente.eliminaRiga(cod);
				int n = (Integer) sessione.getAttribute("numeroProdotti");
				sessione.setAttribute("numeroProdotti", n - riga.getQuantita());
				break;
			}
		}
		
		return "carrello";
	}

}
