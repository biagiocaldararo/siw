package com.bgsshop.action;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bgsshop.model.Prodotto;

public class AzioneDettagliProdotto extends Azione {

	@Override @SuppressWarnings("unchecked")
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		
		for(Prodotto p: (List<Prodotto>) sessione.getAttribute("prodotti"))
			if(request.getParameter(Long.toString(p.getCod()))!=null)
				sessione.setAttribute("prodotto", p);
		
		return "dettaglioProdotto";
	}
}
