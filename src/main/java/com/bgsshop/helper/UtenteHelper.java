package com.bgsshop.helper;

import javax.servlet.http.HttpServletRequest;

import com.bgsshop.model.Utente;

public class UtenteHelper extends Helper<Utente> {
	
	public UtenteHelper(HttpServletRequest request, Utente utente) {
		super(request, utente);
	}

	@Override
	public String[] getFields() {
		return new String[] {"username", "ruolo"};
	}

	@Override
	public void cleanModel() {
		object.setUsername((String) cleanedData.get("username"));
		object.setRuolo((String) cleanedData.get("ruolo"));
	}
	

}
