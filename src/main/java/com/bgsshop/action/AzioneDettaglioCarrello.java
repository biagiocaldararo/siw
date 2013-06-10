package com.bgsshop.action;

import com.bgsshop.mvc.AzioneProtetta;

public class AzioneDettaglioCarrello extends AzioneProtetta {
	
	public int get() {
		return ok("carrello.jsp");
	}

	@Override
	public String getRuolo() {
		return "cliente";
	}

}
