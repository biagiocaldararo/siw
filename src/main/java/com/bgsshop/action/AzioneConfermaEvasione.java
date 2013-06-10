package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bgsshop.facade.FacadeOrdine;
import com.bgsshop.model.Ordine;

public class AzioneConfermaEvasione extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		String destinazione = "evasioneOrdine";
		
		if(request.getParameter("risp").equals("Si")){
			Ordine ordine = (Ordine) sessione.getAttribute("ordine");
			if(ordine.getStato().equals("evaso"))
			   destinazione = "ordineGiaEvaso";
			else {
				ordine.setStato("evaso");
		        if(FacadeOrdine.aggiornaOrdine(ordine))
		        	destinazione = "evasioneOrdineCompletata";
		        else
		        	destinazione = "erroreInserimento";
			}
		}
		
		return destinazione;
	}
}
