package com.bgsshop.controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bgsshop.action.Azione;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> comandi; 
	private Map<String, String> esiti; 

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prossimaPagina = null;
		String comando = request.getServletPath();
		String nomeAzione = this.comandi.get(comando);
		
		if (nomeAzione==null)
			prossimaPagina = "/error.jsp";
		else {
			Azione azione = null;
			try {
				azione = (Azione)Class.forName(nomeAzione).newInstance();
				String esitoAzione = azione.esegui(request);
				prossimaPagina = this.esiti.get(esitoAzione);
			} 
			catch (InstantiationException e) {
				prossimaPagina = "/error.jsp";
			} 
			catch (IllegalAccessException e) {
				prossimaPagina = "/error.jsp";
			} 
			catch (ClassNotFoundException e) {
				prossimaPagina = "/error.jsp";
			}
		}
		
		ServletContext application  = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(prossimaPagina);
		rd.forward(request, response);		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void init() {
		this.comandi = new HashMap<String, String>();
		this.comandi.put("/login.do","com.bgsshop.action.AzioneLogin");
		this.comandi.put("/consultaProdotti.do","com.bgsshop.action.AzioneCatalogo");
		this.comandi.put("/dettagliProdotto.do","com.bgsshop.action.AzioneDettagliProdotto");
		this.comandi.put("/inserisciProdotto.do","com.bgsshop.action.AzioneInserisciProdotto");
		this.comandi.put("/confermaProdotto.do","com.bgsshop.action.AzioneConfermaProdotto");
		this.comandi.put("/nuovoOrdine.do","com.bgsshop.action.AzioneNuovoOrdine");
		this.comandi.put("/aggiungiAlCarrello.do","com.bgsshop.action.AzioneAggiungiProdottoAOrdine");
		this.comandi.put("/rimuoviDalCarrello.do","com.bgsshop.action.AzioneRimuoviProdottoDalCarrello");
		this.comandi.put("/confermaOrdine.do","com.bgsshop.action.AzioneConfermaOrdine");
		this.comandi.put("/logout.do","com.bgsshop.action.AzioneLogout");
		this.esiti= new HashMap<String, String>();
		this.esiti.put("loginFallito","/loginFallito.jsp");
		this.esiti.put("homeAdmin","/homeAdmin.jsp");
		this.esiti.put("catalogoProdotti","/catalogoProdotti.jsp");
		this.esiti.put("dettaglioProdotto","/dettaglioProdotto.jsp");
		this.esiti.put("inserimentoProdotto","/inserimentoProdotto.jsp");
		this.esiti.put("confermaProdotto","/confermaProdotto.jsp");
		this.esiti.put("inserimentoProdottoCompletato","/inserimentoProdottoCompletato.jsp");
		this.esiti.put("erroreInserimento","/erroreInserimento.jsp");
		this.esiti.put("nuovoOrdine","/nuovoOrdine.jsp");
		this.esiti.put("carrello","/carrello.jsp");
		this.esiti.put("inserimentoOrdineCompletato","/inserimentoOrdineCompletato.jsp");
		this.esiti.put("homeCustomer","/homeCustomer.jsp");
		this.esiti.put("home","/home.jsp");
	}
}

