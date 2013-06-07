package com.bgsshop.persistence.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.bgsshop.model.Ordine;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DataSource;
import com.bgsshop.persistence.PersistenceException;

public class OrdineDAOSQLite implements DAO<Ordine> {
	private DataSource data;
	
	private final static String INSERT_QUERY = "insert into ordine(Utente, data, stato, importo) values (?,?,?,?)";

	public OrdineDAOSQLite() {
		data = new DataSourceSQLite();
	}
	
	@Override
	public void insert(Ordine ordine) {
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
			stmt.setLong(1, ordine.getIdCliente());
			stmt.setString(2, new Date().toString());
			stmt.setString(3, ordine.getStato());
			stmt.setDouble(4, ordine.getImporto());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException("Errore nell'iserimento dell'Ordine.", e);
		}
	}

	@Override
	public void delete(Ordine object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Ordine object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Ordine object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ordine> findBy(String field, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ordine> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ordine findOne(String field, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}
