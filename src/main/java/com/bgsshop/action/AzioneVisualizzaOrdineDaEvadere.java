package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.bgsshop.facade.FacadeOrdine;
import com.bgsshop.helper.Helper;
import com.bgsshop.helper.OrdineHelper;
import com.bgsshop.model.Ordine;

public class AzioneVisualizzaOrdineDaEvadere extends Azione {

	
	@Override 
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		Helper helper = new OrdineHelper(request);
		Ordine ordine = null;
		
		String destinazione = "evasioneOrdine";
		
		if(helper.convalida()){
			long id = Long.valueOf(request.getParameter("id"));
			ordine = FacadeOrdine.getOrdine(id);
		
			if(ordine!=null){
				sessione.setAttribute("ordine", ordine);
				destinazione = "visualizzaOrdineDaEvadere";
			}
			else 
				destinazione = "ordineNonTrovato";
		}
		
		return destinazione;
	}
}