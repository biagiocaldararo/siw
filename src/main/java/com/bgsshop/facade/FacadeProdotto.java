package com.bgsshop.facade;

import java.util.*;
import com.bgsshop.model.Prodotto;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class FacadeProdotto {
	private static final DAO<Prodotto> DAO = DAOFactory.getDAOFactory().getProdottoDAO();
	
	public static List<Prodotto> getProdotti(){
		return DAO.findAll();
	}
	
	public static Prodotto getProdotto(long id) {
		return DAO.findById(id);
	}
	
	public static boolean inserisciProdotto(Prodotto prodotto){
		return DAO.insert(prodotto);
	}
}
