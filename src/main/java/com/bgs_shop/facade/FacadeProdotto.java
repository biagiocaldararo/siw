package com.bgs_shop.facade;

import java.util.*;
import com.bgs_shop.model.Prodotto;
import com.bgs_shop.persistence.ProdottoDAO;
import com.bgs_shop.persistence.sqlite.ProdottoDAOSQLite;


public class FacadeProdotto {
	public List<Prodotto> getProdotti(){
		ProdottoDAO dao = new ProdottoDAOSQLite();
		return dao.findAll();
	}
	
	public boolean inserisciProdotto(Prodotto prodotto){
		ProdottoDAO dao = new ProdottoDAOSQLite();
		return dao.insert(prodotto);
	}	
}
