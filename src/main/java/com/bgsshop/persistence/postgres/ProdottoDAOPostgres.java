package com.bgsshop.persistence.postgres;

import java.sql.*;
import java.util.*;
import com.bgsshop.model.*;
import com.bgsshop.persistence.*;


public class ProdottoDAOPostgres implements DAO<Prodotto> {
	private DataSource data;
	private IdBroker broker;
	private final static String INSERT_QUERY = "INSERT INTO prodotto(id, nome, descrizione, prezzo) VALUES (?,?,?,?)";
	private final static String DELETE_QUERY = "DELETE FROM prodotto WHERE id=?";
	private final static String UPDATE_QUERY = "UPDATE prodotto SET nome =?, descrizione=?, prezzo=? WHERE id=?";
	private final static String FIND_QUERY = "SELECT * FROM prodotto WHERE id=?";
	private final static String SELECT_QUERY = "SELECT * FROM prodotto";
	
	public ProdottoDAOPostgres() {
		data = new DataSourcePostgres();
		broker = new IdBroker("prodotto_id_seq");
	}

	@Override
	public boolean insert(Prodotto prodotto) {
		int inserito = 0;
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY);){
			long id = broker.getNextId(conn);
			prodotto.setId(id);
			stmt.setLong(1, id);
			stmt.setString(2, prodotto.getNome());
			stmt.setString(3, prodotto.getDescrizione());
			stmt.setDouble(4, prodotto.getPrezzo());
			inserito = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return inserito!=0;
	}

	@Override
	public boolean delete(Prodotto prodotto) {		
		int eliminato = 0;	
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(DELETE_QUERY)) {
			stmt.setLong(1, prodotto.getId());
			eliminato = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return eliminato!=0;
	}

	@Override
	public boolean update(Prodotto prodotto) {
		int aggiornato= 0;
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {
			stmt.setString(1, prodotto.getNome());
			stmt.setString(2, prodotto.getDescrizione());
			stmt.setDouble(3, prodotto.getPrezzo());
			stmt.setLong(4, prodotto.getId());
			aggiornato = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return aggiornato!=0;
	}

	@Override
	public Prodotto findById(long id) {
		Prodotto prodotto = null;		
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(FIND_QUERY)) {
			stmt.setLong(1, id);
			ResultSet r = stmt.executeQuery();
			while (r.next()) 
				prodotto = new Prodotto(r.getLong("id"), r.getString("nome"), r.getString("descrizione"), r.getDouble("prezzo"));
		} catch (SQLException e) {
			e.printStackTrace();				
		}
		return prodotto;
	}
	
	@Override
	public List<Prodotto> findAll() {
		List<Prodotto> prodotti = new LinkedList<Prodotto>();	
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(SELECT_QUERY)) {
			ResultSet r = stmt.executeQuery();
			while (r.next()) {
				Prodotto prodotto = new Prodotto(r.getLong("id"), r.getString("nome"), r.getString("descrizione"), r.getDouble("prezzo"));
				prodotti.add(prodotto);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();				
		}
		return prodotti;
	}

	@Override
	public Prodotto findByString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Prodotto> findByObject(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
