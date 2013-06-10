package com.bgsshop.action;

import com.bgsshop.facade.FacadeProdotto;
import com.bgsshop.mvc.Azione;

public class AzioneCatalogo extends Azione {

	public int get() {
		FacadeProdotto facade = new FacadeProdotto();
		request.setAttribute("prodotti", facade.getProdotti());

		return ok("catalogo.jsp");
	}
}
