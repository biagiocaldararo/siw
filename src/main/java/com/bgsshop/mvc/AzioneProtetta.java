package com.bgsshop.mvc;

import com.bgsshop.model.Utente;

public abstract class AzioneProtetta extends Azione {
	
	protected Utente utente;
	
	public abstract String getRuolo();
	
	public int esegui() throws Exception {
		utente = (Utente) request.getSession().getAttribute("user");
		if (utente != null && (utente.getRuolo().equals(getRuolo()) || utente.isAdmin()))
			return super.esegui();
		throw new ForbiddenException("Permesso negato.");
	}


}
