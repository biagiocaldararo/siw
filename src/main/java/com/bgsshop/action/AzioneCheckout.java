package com.bgsshop.action;

import java.util.Date;

import com.bgsshop.model.Ordine;
import com.bgsshop.mvc.AzioneProtetta;


public class AzioneCheckout extends AzioneProtetta {

	@Override
	public String getRuolo() {
		return "cliente";
	}
	
	public int get() {
		Ordine carrello = utente.getCarrello();
		carrello.setStato("chiuso");
		carrello.setData(new Date());
		carrello.save();
		utente.setCarrelloId(null);
		utente.save();
		return redirect("/bgsshop/");
	}
	
	

}
