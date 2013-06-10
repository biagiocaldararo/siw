package com.bgsshop.action;

import com.bgsshop.model.Ordine;
import com.bgsshop.mvc.AzioneProtetta;

public class AzioneCarrelloAggiungi extends AzioneProtetta {

	public int post() {
		int qty = Integer.parseInt(request.getParameter("qty"));
		long id = Long.parseLong(params[0]);
		String next = request.getParameter("next");
		
		Ordine carrello = utente.getCarrello();
		carrello.add(id, qty);
		carrello.save();
		
		if (next != null && !next.isEmpty())
			return redirect("/bgsshop/"+next);
		return redirect("/bgsshop/");
	}

	@Override
	public String getRuolo() {
		return "cliente";
	}
}
