package com.bgsshop.model;

import java.util.List;

import com.bgsshop.Password;
import com.bgsshop.persistence.Column;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;
import com.bgsshop.persistence.Column.ColumnType;

public class Utente extends Model {
	
	/* Dati provenienti dal DB */
	@Column(ColumnType.ID) private Number id;
	@Column private String username;
	@Column private String password;
	@Column private String ruolo;
	@Column private Number carrelloId;
	
	/* Campi lazy */
	private Ordine carrello;
	
	public Utente() {};
	public Utente(Number id) { this.id = id.longValue(); }
	public Utente(String username) { this.username = username; }
	
	public Utente(String username, String password, String ruolo) {
		this.setUsername(username);
		this.setPassword(password);
		this.setRuolo(ruolo);
	}
	
	public Ordine getCarrello() {
		DAO<Ordine> dao = DAOFactory.getDAOFactory().getOrdineDAO();
		if (carrelloId == null) {
			Ordine carrello = new Ordine();
			carrello.setStato("aperto");
			carrello.setUtenteId(id);
			carrello.setTotale(0.0);
			carrello.setNumProdotti(0);
			dao.insert(carrello);
			setCarrello(carrello);
			save();
		} else if (carrello == null || carrello.getId() != carrelloId) {
			carrello = dao.findOne("id", carrelloId);
		}
		return carrello;
	}
	
	public void setCarrello(Ordine carrello) {
		this.carrello = carrello;
		this.carrelloId = carrello.getId();
	}
	
	public List<Ordine> getOrdini() {
		Ordine query = new Ordine();
		query.setUtenteId(id);
		DAO<Ordine> dao = DAOFactory.getDAOFactory().getOrdineDAO();
		return dao.select(query);
	}
	
	public boolean isAdmin() {
		return "admin".equals(this.getRuolo());
	}
	
	/* metodo di utilità */
	public void save() {
		DAO<Utente> dao = DAOFactory.getDAOFactory().getUtenteDAO();
		if (id == null)
			dao.insert(this);
		else
			dao.update(this);
	}
	
	
	/* getter e setter */
	
	public Number getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		try {
			this.password = Password.getSaltedHash(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	public Number getCarrelloId() {
		return carrelloId;
	}
	
	public void setCarrelloId(Number carrelloId) {
		this.carrelloId = carrelloId;
	}
	
	@Override
	public String toString() {
		return String.format("<Utente(%s, %s):%d>", username, ruolo, id);
	}
}

