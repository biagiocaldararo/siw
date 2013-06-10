package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.bgsshop.facade.FacadeOrdine;
import com.bgsshop.model.Utente;

public class AzioneVisualizzaOrdini extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		sessione.setAttribute("ordini", FacadeOrdine.getOrdiniUtente((Utente) sessione.getAttribute("utente")));
		return "visualizzaOrdini";
	}
}
