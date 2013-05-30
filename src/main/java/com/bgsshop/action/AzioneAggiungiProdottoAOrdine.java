package com.bgsshop.action;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bgsshop.helper.Helper;
import com.bgsshop.helper.RigaOrdineHelper;
import com.bgsshop.model.Ordine;
import com.bgsshop.model.Prodotto;
import com.bgsshop.model.RigaOrdine;

public class AzioneAggiungiProdottoAOrdine extends Azione {

	@Override @SuppressWarnings("unchecked")
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		Helper helper = new RigaOrdineHelper(request);
		
		Ordine ordineCorrente = (Ordine) sessione.getAttribute("ordineCorrente");
		RigaOrdine rigaCorrente = null;
		Prodotto prodottoCorrente = null;
		boolean trovato = false;
		
		if(helper.convalida()){
			for(Prodotto p: (List<Prodotto>) sessione.getAttribute("prodotti"))
				if(request.getParameter(Long.toString(p.getCod()))!=null)
					prodottoCorrente = p;
		
			for(RigaOrdine r: ordineCorrente.getRigheOrdine())
				if(prodottoCorrente.getCod() == r.getProdotto().getCod()){
					trovato = true;
					rigaCorrente = r;
				}	
			
			if(trovato){
				int quantitaCorrente = Integer.valueOf(request.getParameter("quantita"));
				int nuovaQuantita = rigaCorrente.getQuantita() + quantitaCorrente;
				rigaCorrente.aggiornaRiga(nuovaQuantita);
			}
			else
				ordineCorrente.aggiungiRiga(prodottoCorrente, Integer.valueOf(request.getParameter("quantita")));
		}
		
		sessione.setAttribute("numeroProdotti", ordineCorrente.getNumeroProdotti());
		return "nuovoOrdine";
	}

}
