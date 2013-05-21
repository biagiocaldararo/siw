package it.uniroma3.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

public class ProdottoHelper {
	private HttpServletRequest request;
	private String nome;
	private String descrizione;
	private double prezzo;
	private Map<String,String> errori;
	
	public ProdottoHelper(HttpServletRequest request){
		this.request = request;
		this.nome = request.getParameter("nome");
		this.descrizione = request.getParameter("descrizione");
		this.prezzo = 0;
		this.errori = new HashMap<String,String>();
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getDescrizione(){
		return this.descrizione;
	}
	
	public double getPrezzo(){
		return this.prezzo;
	}
	
	public Map<String,String> getErrori(){
		return this.errori;
	}
	
	public boolean convalida(){		
		boolean tuttoOk = true;
		String stringPrezzo = request.getParameter("prezzo");
		String campoObbligatorio = "Campo obligatorio";
		String errorePrezzo = "Valore non valido";
		
		if (this.nome == null || this.nome.isEmpty()){
			this.errori.put("nome", campoObbligatorio);
		    tuttoOk = false;
		}
		if (this.descrizione == null || this.descrizione.isEmpty()){
			this.errori.put("descrizione", campoObbligatorio);
			tuttoOk = false;
		}
		if (stringPrezzo == null || stringPrezzo.isEmpty()){
			this.errori.put("prezzo", campoObbligatorio);
		    tuttoOk = false;
		}
		else {
			try {
				this.prezzo = Double.valueOf(stringPrezzo);
				if (this.prezzo <= 0){
					this.errori.put("prezzo", errorePrezzo);
					tuttoOk = false;
				}
			}
			catch (NumberFormatException e){
				this.errori.put("prezzo", errorePrezzo);
				tuttoOk = false;
			}
		}
		
		this.request.setAttribute("errori", this.errori);
		return tuttoOk;
	}
}
