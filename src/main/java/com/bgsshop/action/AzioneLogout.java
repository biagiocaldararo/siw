package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class AzioneLogout extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		request.getSession().invalidate();
		return "home";
	}
}
