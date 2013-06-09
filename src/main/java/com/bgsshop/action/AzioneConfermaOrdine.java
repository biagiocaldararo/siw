package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.bgsshop.facade.FacadeOrdine;
import com.bgsshop.model.RigaOrdine;
import com.bgsshop.model.Ordine;

public class AzioneConfermaOrdine extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		String destinazione = "homeCustomer";
		boolean inserito = false;
		
		if(request.getParameter("risp").equals("Si")){
			FacadeOrdine facade = new FacadeOrdine();
			Ordine ordineCorrente = (Ordine) sessione.getAttribute("ordineCorrente");
			ordineCorrente.setStato("chiuso");
			inserito = facade.inserisciOrdine(ordineCorrente);
			
			for(RigaOrdine r: ordineCorrente.getRigheOrdine())
				inserito &= facade.inserisciRigaOrdine(r);
		}
		
		if(inserito)
			destinazione = "inserimentoOrdineCompletato";
		else 
		    destinazione = "erroreInserimento";
		
		sessione.removeAttribute("ordineCorrente");
		
		return destinazione;
	}
}
