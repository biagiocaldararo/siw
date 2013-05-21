package it.uniroma3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import it.uniroma3.model.*;

public class LoginAction {
	public boolean esegui(HttpServletRequest request){
		FacadeAutenticazione facade = new FacadeAutenticazione();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Cliente cliente = facade.login(username, password);
		boolean autenticato = false;
		
		if(cliente!=null){
			HttpSession sessione = request.getSession();
			sessione.setAttribute("cliente", cliente);
			autenticato = true;
		}
		
		return autenticato;
	}
}
