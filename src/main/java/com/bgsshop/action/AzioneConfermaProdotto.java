package com.bgsshop.action;

import com.bgsshop.model.Prodotto;
import com.bgsshop.mvc.AzioneProtetta;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class AzioneConfermaProdotto extends AzioneProtetta {
	
	public int get() {
		return ok("confermaProdotto.jsp");
	}
	
	public int post() {
		Prodotto prodotto = (Prodotto) request.getSession().getAttribute("prodotto");
		if (prodotto != null) {
			DAO<Prodotto> dao = DAOFactory.getDAOFactory().getProdottoDAO();
			dao.insert(prodotto);
		}
		return redirect("/bgsshop/");
	}

	@Override
	public String getRuolo() {
		return "admin";
	}
}
