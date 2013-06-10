package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.bgsshop.facade.FacadeProdotto;

public class AzioneCatalogo extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		request.setAttribute("prodotti", FacadeProdotto.getProdotti());

		return "catalogoProdotti";
	}
}
