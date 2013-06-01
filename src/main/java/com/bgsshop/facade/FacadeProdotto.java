package com.bgsshop.facade;

import java.util.*;
import com.bgsshop.model.Prodotto;
import com.bgsshop.persistence.ProdottoDAO;
import com.bgsshop.persistence.sqlite.ProdottoDAOSQLite;


public class FacadeProdotto {
	public List<Prodotto> getProdotti(){
		ProdottoDAO dao = new ProdottoDAOSQLite();
		return dao.findAll();
	}
	
	public boolean inserisciProdotto(Prodotto prodotto){
		ProdottoDAO dao = new ProdottoDAOSQLite();
		return dao.insert(prodotto);
	}
	
	public Prodotto findById(long id) {
		return new ProdottoDAOSQLite().findById(id);
	}
}
