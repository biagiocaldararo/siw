package com.bgsshop.helper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RigaOrdineHelper extends Helper {
	private int quantita;
	private Map<String,String> errori;
	
	public RigaOrdineHelper(HttpServletRequest request){
		super(request);
		this.quantita = 0;
		this.errori = new HashMap<String,String>();
	}

	@Override
	public boolean convalida() {
		boolean tuttoOk = true;
		String stringQuantita = request.getParameter("quantita");
		String errore = "Valore non valido";
		
		if (stringQuantita == null || stringQuantita.isEmpty()){
			this.errori.put("quantita", errore);
			tuttoOk = false;
		}
		else {
			try {
				this.quantita = Integer.valueOf(stringQuantita);
				if (this.quantita <= 0){
					this.errori.put("quantita", errore);
					tuttoOk = false;
				}
			}
			catch (NumberFormatException e){
				this.errori.put("quantita", errore);
				tuttoOk = false;
			}
		}
		
		this.request.setAttribute("errori", this.errori);
		return tuttoOk;
	}
}
