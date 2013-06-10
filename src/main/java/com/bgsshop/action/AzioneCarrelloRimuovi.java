package com.bgsshop.action;

import com.bgsshop.model.Ordine;
import com.bgsshop.mvc.AzioneProtetta;

public class AzioneCarrelloRimuovi extends AzioneProtetta {
	
	public int get() {
		long id = Long.parseLong(params[0]);
		
		Ordine carrello = utente.getCarrello();
		carrello.del(id);
		carrello.save();
		
		return redirect("/bgsshop/");
	}

	@Override
	public String getRuolo() {
		return "cliente";
	}
}
