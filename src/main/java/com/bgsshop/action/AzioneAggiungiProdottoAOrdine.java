package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bgsshop.facade.FacadeProdotto;
import com.bgsshop.helper.Helper;
import com.bgsshop.helper.RigaOrdineHelper;
import com.bgsshop.model.Ordine;
import com.bgsshop.model.Prodotto;
import com.bgsshop.model.RigaOrdine;

public class AzioneAggiungiProdottoAOrdine extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		Helper helper = new RigaOrdineHelper(request);
		
		Ordine ordineCorrente = (Ordine) sessione.getAttribute("ordineCorrente");
		RigaOrdine rigaCorrente = null;
		boolean trovato = false;
		
		if(helper.convalida()){
			long id = Long.parseLong(request.getParameter("id"));
			for(RigaOrdine r: ordineCorrente.getRigheOrdine())
				if (id == r.getProdotto().getId()){
					trovato = true;
					rigaCorrente = r;
					break;
				}	
			if(trovato){
				int quantitaCorrente = Integer.valueOf(request.getParameter("quantita"));
				int nuovaQuantita = rigaCorrente.getQuantita() + quantitaCorrente;
				rigaCorrente.aggiornaRiga(nuovaQuantita);
			}
			else {
				Prodotto prodotto = new FacadeProdotto().getProdotto(id);
				ordineCorrente.aggiungiRiga(prodotto, Integer.valueOf(request.getParameter("quantita")));
			}
		}
		
		sessione.setAttribute("numeroProdotti", ordineCorrente.getNumeroProdotti());
		return "nuovoOrdine";
	}
}
