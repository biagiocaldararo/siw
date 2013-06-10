package com.bgsshop.helper;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class OrdineHelper extends Helper {
	private long id;
	private Map<String,String> errori;
	
	public OrdineHelper(HttpServletRequest request) {
		super(request);
		this.id = 0;
		this.errori = new HashMap<String,String>();
	}

	@Override
	public boolean convalida() {
		boolean tuttoOk = true;
		String stringId = request.getParameter("id");
		String errore = "Valore non valido";
		
		if (stringId == null || stringId.isEmpty()){
			this.errori.put("id", errore);
			tuttoOk = false;
		}
		else {
			try {
				this.id = Long.valueOf(stringId);
				if (this.id <= 0){
					this.errori.put("id", errore);
					tuttoOk = false;
				}
			} catch (NumberFormatException e){
				this.errori.put("id", errore);
				tuttoOk = false;
			}
		}
		
		this.request.setAttribute("errori", this.errori);
		return tuttoOk;
	}
}
