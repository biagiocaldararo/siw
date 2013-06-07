package com.bgsshop.persistence.sqlite;

import java.sql.*;
import java.util.*;

import com.bgsshop.model.*;
import com.bgsshop.persistence.*;


public class ProdottoDAOSQLite implements DAO<Prodotto> {
	private final static String INSERT_QUERY = "INSERT INTO prodotto(nome, descrizione, prezzo) VALUES (?,?,?)";
	private final static String DELETE_QUERY = "DELETE FROM prodotto WHERE id=?";
	private final static String UPDATE_QUERY = "UPDATE prodotto SET nome =?, descrizione=?, prezzo=? WHERE id=?";
	private final static String FIND_QUERY = "SELECT * FROM prodotto WHERE %s=?";
	private final static String SELECT_QUERY = "SELECT * FROM prodotto";
	
	private DataSource data;
	
	public ProdottoDAOSQLite() {
		data = new DataSourceSQLite();
	}

	@Override
	public void insert(Prodotto prodotto) {
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY);){
			stmt.setString(1, prodotto.getNome());
			stmt.setString(2, prodotto.getDescrizione());
			stmt.setDouble(3, prodotto.getPrezzo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException("Errore nell'iserimento del Prodotto.", e);	
		}
	}

	@Override
	public void delete(Prodotto prodotto) {		
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(DELETE_QUERY)) {
			stmt.setLong(1, prodotto.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException("Errore nell'eliminazione del Prodotto.", e);
		}
	}

	@Override
	public void update(Prodotto prodotto) {
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {
			stmt.setString(1, prodotto.getNome());
			stmt.setString(2, prodotto.getDescrizione());
			stmt.setDouble(3, prodotto.getPrezzo());
			stmt.setLong(4, prodotto.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException("Errore nell'aggiornamento del Prodotto.", e);	
		}
	}
	
	@Override
	public List<Prodotto> findAll() {
		List<Prodotto> prodotti = new LinkedList<Prodotto>();	
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(SELECT_QUERY)) {
			ResultSet r = stmt.executeQuery();
			while (r.next())
				prodotti.add(creaProdotto(r));
		} 
		catch (SQLException e) {
			throw new PersistenceException("Errore nella ricerca dei Prodotti.", e);				
		}
		return prodotti;
	}

	@Override
	public void save(Prodotto prodotto) {
		if (prodotto.getId() != null)
			update(prodotto);
		else
			insert(prodotto);
	}

	@Override
	public List<Prodotto> findBy(String field, Object value) {
		List<Prodotto> prodotti = new ArrayList<Prodotto>();
		String query = String.format(FIND_QUERY, field);
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setObject(1, value);
			ResultSet r = stmt.executeQuery();
			while (r.next())
				prodotti.add(creaProdotto(r));
			return prodotti;
		} catch (SQLException e) {
			throw new PersistenceException("Errore nella ricerca dei Prodotti.", e);			
		}
	}
	
	@Override
	public Prodotto findOne(String field, Object value) {
		List<Prodotto> prodotti = findBy(field, value);
		if (prodotti == null || prodotti.size() == 0)
			throw new PersistenceException("Prodotto non trovato.");
		if (prodotti.size() > 1)
			throw new PersistenceException("Più di un prodotto trovato con findOne.");
		return prodotti.get(0);
	}
	
	private Prodotto creaProdotto(ResultSet r) throws SQLException {
		Prodotto p = new Prodotto(r.getLong("id"));
		p.setNome(r.getString("nome"));
		p.setDescrizione(r.getString("descrizione"));
		p.setPrezzo(r.getDouble("prezzo"));
		return p;
	}
}
