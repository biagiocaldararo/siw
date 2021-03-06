package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.bgsshop.facade.FacadeProdotto;
import com.bgsshop.model.Prodotto;

public class AzioneConfermaProdotto extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		String destinazione = "inserimentoProdotto";
		
		if (request.getParameter("risp").equals("Si")){
			FacadeProdotto facade = new FacadeProdotto();
		    if(facade.inserisciProdotto((Prodotto) sessione.getAttribute("prodotto")))
		    	destinazione = "inserimentoProdottoCompletato";
		    else 
		    	destinazione = "erroreInserimento";
		}
		
		return destinazione;   
	}
}
