package com.bgsshop.persistence.postgres;

import java.util.ArrayList;
import java.util.List;
import com.bgsshop.model.Ordine;
import com.bgsshop.model.RigaOrdine;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;

public class OrdineProxy extends Ordine {
	private boolean caricato = false;
	
	public OrdineProxy(long id, String data, String stato, double importo) {
		super(id, data, stato, importo);
	}
	
	public List<RigaOrdine> getRigheOrdine() { 
	  if (!this.caricato) {
		List<RigaOrdine> righeOrdine = new ArrayList<RigaOrdine>();
		DAO<RigaOrdine> dao = DAOFactory.getDAOFactory().getRigaOrdineDAO();
	  	righeOrdine = dao.findByObject(this);
	  	this.setRigheOrdine(righeOrdine);
		this.caricato = true;
	  }
	  return super.getRigheOrdine(); 
	}
}
