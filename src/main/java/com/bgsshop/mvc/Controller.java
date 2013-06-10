package com.bgsshop.mvc;

import java.io.IOException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(filterName="Controller", urlPatterns={"/*"})
//@WebServlet(name="Controller", urlPatterns={"/"})
public class Controller implements Filter {
	private static final long serialVersionUID = 1L;
	private Map<Pattern, Class<Azione>> azioni; 
	
	@Override @SuppressWarnings("unchecked")
	public void init(FilterConfig config) {
		System.out.println("Controller init");
		String pkg = "com.bgsshop.action";
		String[][] urlconf = {
				{"/", "AzioneHome"},
				{"/login", "AzioneLogin"},
				{"/logout", "AzioneLogout"},
				
				{"/prodotti/?", "AzioneCatalogo"},
				{"/prodotti/(\\d+)", "AzioneDettaglioProdotto"},

				{"/carrello/?", "AzioneDettaglioCarrello"},
				{"/carrello/aggiungi/(\\d+)", "AzioneCarrelloAggiungi"},
				{"/carrello/rimuovi/(\\d+)", "AzioneCarrelloRimuovi"},
				{"/carrello/checkout", "AzioneCheckout"},
				
				{"/ordini/?", "AzioneOrdini"},
				{"/ordini/(\\d+)", "AzioneDettaglioOrdine"},
				
				{"/admin", "AzioneAmministrazione"},
				{"/admin/ordini", "AzioneAmministrazioneOrdini"},
				{"/admin/ordini/(\\d+)/spedisci", "AzioneSpedisciOrdine"},
				{"/admin/utenti/(\\d+)", "AzioneModificaUtente"},
				{"/admin/utenti/aggiungi", "AzioneAggiungiUtente"},
				{"/admin/prodotti/(\\d+)", "AzioneModificaProdotto"},
				{"/admin/prodotti/aggiungi", "AzioneAggiungiProdotto"},
		};
	
		azioni = new HashMap<Pattern, Class<Azione>>();
		for (int i = 0; i < urlconf.length; i++) {
			Pattern pattern = Pattern.compile(urlconf[i][0]);
			String azione = String.format("%s.%s", pkg, urlconf[i][1]);
			try {
				azioni.put(pattern, (Class<Azione>)Class.forName(azione));
			} catch (Exception e) {
				System.err.printf("Errore nella registrazione dell'Azione: %s", urlconf[i][1]);
				System.err.println();
			}
		}
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		System.out.println("Service");
		try {
			Azione azione = getAzione(request, response);
			if (azione == null) {
				chain.doFilter(req, res);
				return;
			}
			System.out.println("Eseguo azione: " + azione);
			int code = azione.esegui();
			String template = azione.getTemplate();
			if (code == HttpServletResponse.SC_FOUND) {
				response.sendRedirect(template);
			} else {
				response.setStatus(code);
				dispatch(request, response, template);
			}
		} catch (HttpException e) {
			System.err.println("httpexception");
			response.sendError(e.getStatus(), e.getLocalizedMessage());
		} catch (RuntimeException | ServletException | IOException exc) {
			System.out.println("scazzo");
			throw exc;
		} catch (Exception e) {
			System.out.println("scazzo brutto");
			throw new ServletException(e);
		}
	}


	private Azione getAzione(HttpServletRequest request, HttpServletResponse response)
			throws InstantiationException, IllegalAccessException {
		String path = request.getServletPath();
		System.out.println("Path: "+path);
		
		for(Pattern pattern : azioni.keySet()) {
			Matcher matcher = pattern.matcher(path);
			if (matcher.matches()) {
				System.out.println("Matched " + pattern);
				String[] parametri = new String[matcher.groupCount()];
				
				
				for (int i = 0; i < parametri.length; i++)
					parametri[i] = matcher.group(i+1);
				
				// request.setAttribute("parametri", parametri);
				Azione action = azioni.get(pattern).newInstance();
				action.init(request, response, parametri);
				return action;
			}
		}
		// TODO return Azione per gli errori
		//throw new NotFoundException("Non è stata registrata un'azione per questo indirizzo.");
		return null;
	}
	
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String template) throws ServletException, IOException
	{
		// ServletContext application  = getServletContext();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/" + template);
		rd.forward(request, response);	
	}

	@Override
	public void destroy() {}

}