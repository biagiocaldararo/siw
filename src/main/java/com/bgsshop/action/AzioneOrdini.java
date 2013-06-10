package com.bgsshop.action;

import com.bgsshop.mvc.AzioneProtetta;

public class AzioneOrdini extends AzioneProtetta {

	@Override
	public String getRuolo() {
		return "cliente";
	}
	
	public int get() {
		return ok("ordini.jsp");
	}

}
