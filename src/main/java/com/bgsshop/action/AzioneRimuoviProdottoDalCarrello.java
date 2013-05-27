package com.bgsshop.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bgsshop.model.Ordine;
import com.bgsshop.model.Prodotto;

public class AzioneRimuoviProdottoDalCarrello extends Azione {

	@Override @SuppressWarnings("unchecked")
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		Ordine ordineCorrente = (Ordine) sessione.getAttribute("ordineCorrente");
		Prodotto prodottoCorrente = null;
		
		for(Prodotto p: (List<Prodotto>) sessione.getAttribute("prodotti"))
			if(request.getParameter(Long.toString(p.getCod()))!=null)
				prodottoCorrente = p;
		
		ordineCorrente.eliminaRiga(prodottoCorrente.getCod());
		
		sessione.setAttribute("numeroProdotti", ordineCorrente.getNumeroProdotti());
		return "carrello";
	}

}
