package com.bgsshop.action;

import com.bgsshop.mvc.Azione;


public class AzioneHome extends Azione {

	public int get() {
		return redirect("prodotti");
	}

}
