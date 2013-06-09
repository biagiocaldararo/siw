package com.bgsshop.persistence.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import com.bgsshop.model.Ordine;
import com.bgsshop.model.Utente;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DataSource;

public class OrdineDAOSQLite implements DAO<Ordine> {
	private DataSource data;
	
	private final static String INSERT_QUERY = "insert into ordine(Utente, data, stato, importo) values (?,?,?,?)";
	private final static String FIND_USER_QUERY = "select id, data, stato, importo from ordine where utente = ?";

	public OrdineDAOSQLite() {
		data = new DataSourceSQLite();
	}
	
	@Override
	public boolean insert(Ordine ordine) {
		int inserito = 0;
		
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
			stmt.setLong(1, ordine.getUtente().getId());
			stmt.setString(2, new Date().toString());
			stmt.setString(3, ordine.getStato());
			stmt.setDouble(4, ordine.getImporto());
			inserito = stmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();	
		}

		return inserito!=0;
	}

	@Override
	public boolean delete(Ordine Ordine) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Ordine Ordine) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Ordine findByString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ordine> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ordine findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ordine> findByObject(Object object) {
		Utente utente = (Utente) object;
		List<Ordine> ordini = new LinkedList<Ordine>();	
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(FIND_USER_QUERY)) {
			stmt.setLong(1, utente.getId());
			ResultSet r = stmt.executeQuery();
			while (r.next()) {
				Ordine ordine = new Ordine(r.getLong("id"), r.getString("data"), r.getString("stato"), r.getDouble("importo"));
				ordini.add(ordine);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();				
		}
		
		return ordini;
	}
}
