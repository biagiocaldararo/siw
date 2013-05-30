package com.bgsshop.controller;

import java.io.IOException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bgsshop.action.Azione;

// TODO: guardare Servlet.authenticate / login / logout per l'autenticazione:
// http://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html#authenticate(javax.servlet.http.HttpServletResponse)

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<Pattern, Azione> azioni; 
	
	public void init() {
		System.out.println("Controller init");
		String pkg = "com.bgsshop.action";
		String[][] urlconf = {
				{"/login", "AzioneLogin"},
				{"/logout", "AzioneLogout"},
				{"/prodotti", "AzioneCatalogo"},
				{"/prodotti/inserisci", "AziendeInserisciProdotto"},
				{"/prodotti/(\\d+)", "AzioneDettaglioProdotto"},
				{"/carrello/", "AzioneDettaglioCarrello"},
				{"/carrello/aggiungi/(\\d+)", "AzioneCarrelloAggiungi"},
				{"/carrello/rimuovi/(\\d+)", "AzioneCarrelloRimuovi"},
				{"/carrello/checkout", "AzioneCheckout"},	
		};
	
		azioni = new HashMap<Pattern, Azione>();
		for (int i = 0; i < urlconf.length; i++) {
			Pattern pattern = Pattern.compile(urlconf[i][0]);
			String azione = String.format("%s.%s", pkg, urlconf[i][1]);
			try {
				azioni.put(pattern, (Azione) Class.forName(azione).newInstance());
			} catch (Exception e) {
				System.err.printf("Errore nella registrazione dell'Azione: %s", urlconf[i][1]);
				System.err.println();
			}
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// String path = request.getServletPath();
		// System.out.println(path);

		Azione azione = getAzione(request);
		
		if (azione == null) {
			dispatch(request, response, "notfound.jsp");
			return;
		}
		String template;
		try {
			template = azione.esegui(request);
			dispatch(request, response, template);
		} catch (Exception e) {
			e.printStackTrace();
			
			template = "error.jsp";
			dispatch(request, response, template);
		}
	}


	private Azione getAzione(HttpServletRequest request) {
		String path = request.getServletPath();
		
		for(Pattern pattern : azioni.keySet()) {
			Matcher matcher = pattern.matcher(path);
			if (matcher.matches()) {
				String[] parametri = new String[matcher.groupCount()];
				
				
				for (int i = 0; i < parametri.length; i++)
					parametri[i] = matcher.group(i+1);
				
				request.setAttribute("parametri", parametri);
				return azioni.get(pattern);
			}
		}
		// TODO return Azione per gli errori
		return null;
	}
	
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String template) throws ServletException, IOException
	{
		ServletContext application  = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/WEB-INF/" + template);
		rd.forward(request, response);	
	}

}