package com.bgs_shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bgs_shop.model.*;


public class LoginAction {
	public boolean esegui(HttpServletRequest request){
		FacadeAutenticazione authService = new FacadeAutenticazione();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Cliente cliente = authService.login(username, password);
		boolean autenticato = false;
		
		if(cliente!=null){
			HttpSession sessione = request.getSession();
			sessione.setAttribute("cliente", cliente);
			autenticato = true;
		}
		
		return autenticato;
	}
}
