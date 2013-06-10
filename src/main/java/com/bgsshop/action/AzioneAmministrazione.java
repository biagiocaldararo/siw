package com.bgsshop.action;

import com.bgsshop.mvc.AzioneProtetta;

public class AzioneAmministrazione extends AzioneProtetta {

	@Override
	public String getRuolo() {
		return "admin";
	}
	
	public int get() {
		return ok("admin/home.jsp");
	}

}
