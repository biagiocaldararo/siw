package com.bgsshop.helper;

import javax.servlet.http.HttpServletRequest;

import com.bgsshop.model.Utente;

public class NuovoUtenteHelper extends Helper<Utente> {

	public NuovoUtenteHelper(HttpServletRequest request) {
		super(request, new Utente());
	}
	

	@Override
	public String[] getFields() {
		return new String[] {"username", "password", "ruolo"};
	}

	@Override
	public void cleanModel() {
		object.setUsername((String) cleanedData.get("username"));
		object.setPassword((String) cleanedData.get("password"));
		object.setRuolo((String) cleanedData.get("ruolo"));
	}
	

}