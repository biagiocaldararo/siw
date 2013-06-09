package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class AzioneVisualizzaOrdini extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		return "visualizzaOrdini";
	}

}
