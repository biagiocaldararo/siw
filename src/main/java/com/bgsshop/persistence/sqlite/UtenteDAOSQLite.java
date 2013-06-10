package com.bgsshop.persistence.sqlite;

import java.sql.*;

import java.util.*;

import com.bgsshop.model.*;
import com.bgsshop.persistence.*;


public class UtenteDAOSQLite implements DAO<Utente> {
	private final static String INSERT_QUERY = "INSERT INTO utente(username, password, ruolo) VALUES (?,?,?)";
	// TODO: trasformare tutti i WHERE in "id=?"
	private final static String DELETE_QUERY = "DELETE FROM utente WHERE username=?";
	private final static String UPDATE_QUERY = "UPDATE utente SET password=?, ruolo=? WHERE username=?";
	private final static String FIND_QUERY = "SELECT * FROM utente WHERE %s=?";
	private final static String SELECT_QUERY = "SELECT * FROM utente";
	private DataSource data;
	
	public UtenteDAOSQLite() {
		data = new DataSourceSQLite();
	}

	// TODO: settare l'id dell'utente appena inserito.
	@Override
	public void insert(Utente utente) {	
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
			stmt.setString(1, utente.getUsername());
			stmt.setString(2, utente.getPassword());
			stmt.setString(3, utente.getRuolo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException("Errore nell'iserimento dell'Utente.", e);	
		}
	}

	@Override
	public void delete(Utente utente) {
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(DELETE_QUERY)) {
			stmt.setString(1, utente.getUsername());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException("Errore nell'eliminazione dell'Utente.", e);	
		}
	}

	@Override
	public void update(Utente utente) {
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {
			stmt.setString(1, utente.getPassword());
			stmt.setString(2, utente.getRuolo());
			stmt.setString(3, utente.getUsername());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException("Errore nell'aggiornamento dell'Utente.", e);	
		}
	}
	
	@Override
	public List<Utente> findAll() {
		List<Utente> utenti = new LinkedList<Utente>();	
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(SELECT_QUERY)) {
			ResultSet r = stmt.executeQuery();
			while (r.next())
				utenti.add(creaUtente(r));
			return utenti;
		} catch (SQLException e) {
			throw new PersistenceException("Errore nella ricerca dei Prodotti.", e);					
		}
	}
	
	@Override
	public void save(Utente utente) {
		if (utente.getId() != null)
			update(utente);
		else
			insert(utente);
	}
	
	@Override
	public List<Utente> findBy(String field, Object value) {
		List<Utente> utenti = new LinkedList<Utente>();
		String query = String.format(FIND_QUERY, field); // TODO: possible SQL injection ...
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setObject(1, value);
			ResultSet r = stmt.executeQuery();
			while (r.next()) 
				utenti.add(creaUtente(r));
			return utenti;
		} catch (SQLException e) {
			throw new PersistenceException("Errore nella ricerca degli Utenti.", e);					
		}
	}

	@Override
	public Utente findOne(String field, Object value) {
		List<Utente> utenti = findBy(field, value);
		if (utenti == null || utenti.size() == 0)
			throw new PersistenceException("Utente non trovato.");
		if (utenti.size() > 1)
			throw new PersistenceException("Più di un utente trovato con findOne.");
		return utenti.get(0);
	}
	
	private Utente creaUtente(ResultSet r) throws SQLException {
		Utente u = new Utente(r.getLong("id"));
		u.setUsername(r.getString("username"));
		u.setPassword(r.getString("password"));
		u.setRuolo(r.getString("ruolo"));
		return u;
	}

	@Override
	public List<Utente> select(Utente query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente get(Utente query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utente> in(String field, Collection<?> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer count(Utente query) {
		// TODO Auto-generated method stub
		return null;
	}

}

