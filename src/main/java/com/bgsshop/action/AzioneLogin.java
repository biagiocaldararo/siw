package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bgsshop.facade.*;
import com.bgsshop.model.*;

public class AzioneLogin extends Azione{

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String destinazione = "loginFallito";
		Utente utente = FacadeAutenticazione.login(username, password);
	
		if(utente!=null){
			HttpSession sessione = request.getSession();
		    sessione.setAttribute("utente", utente);
		    destinazione = "homeAdmin";
		    if(utente.getRuolo().equals("customer"))
		       destinazione = "homeCustomer";
		}
		
		return destinazione;
	}
}
