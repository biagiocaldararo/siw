package com.bgsshop.helper;

import javax.servlet.http.HttpServletRequest;

import com.bgsshop.model.Prodotto;

public class ProdottoHelper extends Helper<Prodotto> {

	public ProdottoHelper(HttpServletRequest request, Prodotto prodotto) {
		super(request, prodotto);
	}
	public ProdottoHelper(HttpServletRequest request) {
		super(request, new Prodotto());
	}

	@Override
	public String[] getFields() {
		return new String[] {"nome", "descrizione", "prezzo"};
	}

	@Override
	public void cleanModel() {
		object.setNome((String) cleanedData.get("nome"));
		object.setDescrizione((String) cleanedData.get("descrizione"));
		object.setPrezzo((double) cleanedData.get("prezzo"));
	}
	
	@Override
	public Validator getValidator(String field) {
		if ("prezzo".equals(field))
			return new DoubleValidator();
		return new NotEmptyValidator();
	}

}
