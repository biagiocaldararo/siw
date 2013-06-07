package com.bgsshop.facade;

import java.util.*;
import com.bgsshop.model.Prodotto;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class FacadeProdotto {
	public List<Prodotto> getProdotti(){
		DAO<Prodotto> dao = DAOFactory.getDAOFactory().getProdottoDAO();
		return dao.findAll();
	}
	
	public boolean inserisciProdotto(Prodotto prodotto){
		DAO<Prodotto> dao = DAOFactory.getDAOFactory().getProdottoDAO();
		dao.insert(prodotto);
		return true;
	}
	
	public Prodotto getProdotto(long id) {
		DAO<Prodotto> dao = DAOFactory.getDAOFactory().getProdottoDAO();
		return dao.findOne("id", id);
	}
}
