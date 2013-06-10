package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.bgsshop.facade.FacadeProdotto;
import com.bgsshop.model.Utente;
import com.bgsshop.model.Ordine;

public class AzioneNuovoOrdine extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		sessione.setAttribute("prodotti", FacadeProdotto.getProdotti());
		
		Ordine ordineCorrente = new Ordine((Utente) sessione.getAttribute("utente"));
		sessione.setAttribute("ordineCorrente", ordineCorrente);
		sessione.setAttribute("numeroProdotti", 0);
		
		return "nuovoOrdine";
	}
}
