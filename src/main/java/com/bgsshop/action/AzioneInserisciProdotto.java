package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.bgsshop.helper.Helper;
import com.bgsshop.helper.ProdottoHelper;
import com.bgsshop.model.Prodotto;

public class AzioneInserisciProdotto extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		Helper helper = new ProdottoHelper(request);
		
		String destinazione = "inserimentoProdotto";
		
		if(helper.convalida()){
			String nome = request.getParameter("nome");
			String descrizione = request.getParameter("descrizione");
			double prezzo = Double.valueOf(request.getParameter("prezzo"));
			sessione.setAttribute("prodotto", new Prodotto(0, nome, descrizione, prezzo));
			destinazione = "confermaProdotto";
		}

		return destinazione;
	}
}
