package com.bgs_shop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.bgs_shop.facade.FacadeProdotto;
import com.bgs_shop.model.Cliente;
import com.bgs_shop.model.Ordine;

public class AzioneNuovoOrdine extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		
		if(sessione.getAttribute("prodotti")==null){
			FacadeProdotto facade = new FacadeProdotto();
			sessione.setAttribute("prodotti", facade.getProdotti());
		}
		
		Ordine ordineCorrente = new Ordine((Cliente) sessione.getAttribute("cliente"));
		sessione.setAttribute("ordineCorrente", ordineCorrente);
		sessione.setAttribute("numeroProdotti", 0);
		
		return "nuovoOrdine";
	}
}
