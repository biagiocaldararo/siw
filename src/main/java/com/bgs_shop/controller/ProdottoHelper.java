package com.bgs_shop.controller;

import javax.servlet.http.HttpServletRequest;

import com.bgs_shop.model.*;

import java.util.*;


public class ProdottoHelper {
	private HttpServletRequest request;
	private Prodotto prodotto;
	private Map<String,String> errori;
	
	public ProdottoHelper(HttpServletRequest request){
		this.request = request;
		this.prodotto = new Prodotto(-1, request.getParameter("nome"), request.getParameter("descrizione"), 0);
		this.errori = new HashMap<String,String>();
	}
	
	public Prodotto getProdotto() {
		return prodotto;
	}
	
	public Map<String,String> getErrori(){
		return this.errori;
	}
	
	public boolean convalida(){		
		boolean tuttoOk = true;
		String nome = this.prodotto.getNome();
		String desc = this.prodotto.getDescrizione();
		String stringPrezzo = request.getParameter("prezzo");
		String campoObbligatorio = "Campo obligatorio";
		String errorePrezzo = "Valore non valido";
		
		if (nome == null || nome.isEmpty()){
			this.errori.put("nome", campoObbligatorio);
		    tuttoOk = false;
		}
		if (desc == null || desc.isEmpty()){
			this.errori.put("descrizione", campoObbligatorio);
			tuttoOk = false;
		}
		if (stringPrezzo == null || stringPrezzo.isEmpty()){
			this.errori.put("prezzo", campoObbligatorio);
		    tuttoOk = false;
		}
		else {
			try {
				this.prodotto.setPrezzo(Double.valueOf(stringPrezzo));
				if (this.prodotto.getPrezzo() <= 0){
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
