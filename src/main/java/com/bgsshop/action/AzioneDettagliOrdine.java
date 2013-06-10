package com.bgsshop.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bgsshop.model.Ordine;

public class AzioneDettagliOrdine extends Azione {
	
	@Override @SuppressWarnings("unchecked")
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		
		long id;
		try {
			id = Long.valueOf(request.getParameter("id"));
		} catch (NumberFormatException e) {
			return "erroreInserimento";
		}
		
		for(Ordine ordine: (List<Ordine>) sessione.getAttribute("ordini"))
			if(ordine.getId()==id){
				sessione.setAttribute("righeOrdine", ordine.getRigheOrdine());
				break;
			}
		
		return "dettagliOrdine";
	}

}
