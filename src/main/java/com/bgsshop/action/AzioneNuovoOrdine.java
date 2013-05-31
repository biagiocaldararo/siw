package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.bgsshop.facade.FacadeProdotto;
import com.bgsshop.model.Cliente;
import com.bgsshop.model.Ordine;

public class AzioneNuovoOrdine implements Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		
		FacadeProdotto facade = new FacadeProdotto();
		request.setAttribute("prodotti", facade.getProdotti());
		
		Ordine ordineCorrente = new Ordine((Cliente) sessione.getAttribute("cliente"));
		sessione.setAttribute("ordineCorrente", ordineCorrente);
		sessione.setAttribute("numeroProdotti", 0);
		
		return "nuovoOrdine";
	}
}
