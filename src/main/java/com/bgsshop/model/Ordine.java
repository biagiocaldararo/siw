package com.bgsshop.model;

import java.sql.Date;
import java.util.List;

import com.bgsshop.persistence.Column;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;
import com.bgsshop.persistence.Column.ColumnType;

public class Ordine {
	@Column(ColumnType.ID) private Long id;
	@Column private Long data;
	@Column private Number cliente;
	@Column private String stato;
	@Column private Double importo;
	// private List<RigaOrdine> righeOrdine;
	
	
	public Ordine(Number id) { this.id = id.longValue(); }
	
	public Ordine() {}
	
	public Ordine(Utente cliente){
		this.setCliente(cliente);
		this.setStato("aperto");
		// this.righeOrdine = new LinkedList<RigaOrdine>();
	}

	public long getId() {
		return id.longValue();
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData() {
		return new Date(data);
	}

	public void setData(Date data) {
		this.data = data.getTime();
	}

	public Utente getCliente() {
		DAO<Utente> utenteDAO = DAOFactory.getDAOFactory().getUtenteDAO();
		return utenteDAO.findOne("id", cliente);
	}

	public void setCliente(Utente cliente) {
		this.cliente = cliente.getId();
	}
	public void setCliente(Number id) {
		this.cliente = id;
	}

	public List<RigaOrdine> getRigheOrdine() {
		DAO<RigaOrdine> dao = DAOFactory.getDAOFactory().getRigaOrdineDAO();
		return dao.findBy("ordine", id);
	}

	public void setRigheOrdine(List<RigaOrdine> righeOrdine) {
		throw new UnsupportedOperationException("setRigheordine non è implementato :(");
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public void aggiungiRiga(Prodotto prodotto, int quantita){
		aggiungiRiga(prodotto.getId(), quantita);
	}
	
	public void aggiungiRiga(Number prodotto, int quantita) {
		RigaOrdine riga = new RigaOrdine(id, prodotto, quantita);
		DAO<RigaOrdine> dao = DAOFactory.getDAOFactory().getRigaOrdineDAO();
		dao.insert(riga);
	}
	
	public void eliminaRiga(long prodotto){
//		for(RigaOrdine riga: this.righeOrdine){
//			if(riga.getProdotto().getId()==id) {
//				righeOrdine.remove(riga);
//				this.importo -= riga.getCosto();
//			}
//		}
		throw new UnsupportedOperationException("eliminaRiga non è implementato :(");
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}
	
	public int getNumeroProdotti(){
//		int numeroProdotti = 0;
//		
//		for(RigaOrdine r: this.righeOrdine)
//			numeroProdotti += r.getQuantita();
//		
//		return numeroProdotti;
		throw new UnsupportedOperationException("getNumeroProdotti non è implementato :(");
	}
	
	public Long getIdCliente() {
		return cliente.longValue();
	}
	
	@Override
	public String toString() {
		return String.format("<%s: %s %s€>", id, stato, importo);
	}
}
