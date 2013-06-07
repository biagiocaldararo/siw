package com.bgsshop.facade;

import java.util.*;

import com.bgsshop.model.Ordine;
import com.bgsshop.model.Prodotto;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;
import com.bgsshop.persistence.ProdottoDAO;
import com.bgsshop.persistence.sqlite.ProdottoDAOSQLite;


public class FacadeProdotto {
	public List<Prodotto> getProdotti(){
		DAO<Prodotto> dao = DAOFactory.getDAOFactory().getProdottoDAO();
		return dao.findAll();
	}
	
	public boolean inserisciProdotto(Prodotto prodotto){
		DAO<Prodotto> dao = DAOFactory.getDAOFactory().getProdottoDAO();
		return dao.insert(prodotto);
	}
	
	public Prodotto getProdotto(long id) {
		DAO<Prodotto> dao = DAOFactory.getDAOFactory().getProdottoDAO();
		return dao.findById(id);
	}
}
