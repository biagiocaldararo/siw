package it.uniroma3.controller;

import it.uniroma3.model.*;

import javax.servlet.http.HttpServletRequest;

public class ClienteHelper {
	private HttpServletRequest request;
	private Cliente cliente;
	
	public ClienteHelper(HttpServletRequest request, Cliente cliente){
		this.request = request;
		this.cliente = cliente;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public boolean login(){		
		boolean loggato = true;
		String erroreInserimento = "Errore inserimento username o password";
		
		if(this.cliente == null){
			loggato = false;
			this.request.setAttribute("errore", erroreInserimento);
		}
		
		else if(!this.cliente.getPassword().equals(request.getParameter("password"))){
			loggato = false;
			this.request.setAttribute("errore", erroreInserimento);
		}

		return loggato;
	}
}
