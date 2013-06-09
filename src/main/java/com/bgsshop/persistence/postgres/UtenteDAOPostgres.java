package com.bgsshop.persistence.postgres;

import java.sql.*;
import java.util.*;
import com.bgsshop.model.*;
import com.bgsshop.persistence.*;


public class UtenteDAOPostgres implements DAO<Utente> {
	private DataSource data;
	private IdBroker broker;
	private final static String INSERT_QUERY = "INSERT INTO utente (id, username, password, ruolo) VALUES (?,?,?,?)";
	private final static String DELETE_QUERY = "DELETE FROM utente WHERE username=?";
	private final static String UPDATE_QUERY = "UPDATE utente SET password=?, ruolo=? WHERE username=?";
	private final static String FIND_QUERY = "SELECT * FROM utente WHERE username=?";
	private final static String SELECT_QUERY = "SELECT * FROM utente";
	
	
	public UtenteDAOPostgres() {
		data = new DataSourcePostgres();
		broker = new IdBroker("utente_id_seq");
	}

	@Override
	public boolean insert(Utente utente) {	
		int inserito = 0;
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
			long id = broker.getNextId(conn);
			utente.setId(id);
			stmt.setLong(1, id);
			stmt.setString(2, utente.getUsername());
			stmt.setString(3, utente.getPassword());
			stmt.setString(4, utente.getRuolo());
			inserito = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return inserito != 0;
	}

	@Override
	public boolean delete(Utente utente) {
		int eliminato = 0;
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(DELETE_QUERY)) {
			stmt.setString(1, utente.getUsername());
			eliminato = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return eliminato!=0;
	}

	@Override
	public boolean update(Utente utente) {
		int aggiornato= 0;
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {
			stmt.setString(1, utente.getPassword());
			stmt.setString(2, utente.getRuolo());
			stmt.setString(3, utente.getUsername());
			aggiornato = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return aggiornato!=0;
	}

	@Override
	public Utente findByString(String username) {
		Utente utente = null;	
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(FIND_QUERY)) {
			stmt.setString(1, username);
			ResultSet r = stmt.executeQuery();
			while (r.next()) 
				utente = new Utente(r.getLong("id"), r.getString("username"), r.getString("password"), r.getString("ruolo"));
		} catch (SQLException e) {
			e.printStackTrace();				
		}
		return utente;
	}
	
	@Override
	public List<Utente> findAll() {
		List<Utente> utenti = new LinkedList<Utente>();	
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(SELECT_QUERY)) {
			ResultSet r = stmt.executeQuery();
			while (r.next()) {
				Utente utente = new Utente(r.getLong("id"), r.getString("username"), r.getString("password"), r.getString("ruolo"));
				utenti.add(utente);
			}
		} catch (SQLException e) {
			e.printStackTrace();				
		}
		return utenti;
	}

	@Override
	public Utente findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utente> findByObject(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}

