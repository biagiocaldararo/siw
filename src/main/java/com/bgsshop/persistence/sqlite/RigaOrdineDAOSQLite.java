package com.bgsshop.persistence.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import com.bgsshop.model.Ordine;
import com.bgsshop.model.RigaOrdine;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DataSource;
import com.bgsshop.persistence.PersistenceException;

public class RigaOrdineDAOSQLite implements DAO<RigaOrdine> {
	private DataSource data;
	private final static String INSERT_QUERY = "insert into rigaOrdine(ordine,prodotto,quantita,costo) values (?,?,?,?)";
	private final static String DELETE_QUERY = "DELETE FROM rigaOrdine WHERE id=?";
	private final static String UPDATE_QUERY = "UPDATE rigaOrdine SET ordine=?, SET prodotto=?, SET quantita=?, SET costo=?";
	private final static String FIND_QUERY = "SELECT * FROM RigaOrdine WHERE %s=?";
	private final static String SELECT_QUERY = "SELECT * FROM RigaOrdine";
	
	public RigaOrdineDAOSQLite() {
		data = new DataSourceSQLite();
	}
	
	@Override
	public void insert(RigaOrdine rigaOrdine){	
		try(Connection conn = data.getConnection();
			PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
			stmt.setLong(1, rigaOrdine.getOrdine().getId());
			stmt.setLong(2, rigaOrdine.getProdotto().getId());
			stmt.setInt(3, rigaOrdine.getQuantita());
			stmt.setDouble(4, rigaOrdine.getCosto());
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new PersistenceException("Errore nell'eliminazione della Riga Ordine.", e);
		}
	}

	@Override
	public void delete(RigaOrdine rigaOrdine) {
		try(Connection conn = data.getConnection();
			PreparedStatement stmt = conn.prepareStatement(DELETE_QUERY)) {
			stmt.setLong(1, rigaOrdine.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new PersistenceException("Errore nell'eliminazione della Riga Ordine.", e);
		}
		
	}

	@Override
	public void update(RigaOrdine rigaOrdine) {
		try(Connection conn = data.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {
			stmt.setLong(1,rigaOrdine.getOrdine().getId());
			stmt.setLong(2,rigaOrdine.getProdotto().getId());
			stmt.setInt(3,rigaOrdine.getQuantita());
			stmt.setDouble(4,rigaOrdine.getCosto());
			stmt.setLong(5, rigaOrdine.getId());
		}
		catch (SQLException e){
			throw new PersistenceException("Errore nell'aggiornamento della Riga Ordine.", e);
		}
		
	}

	@Override
	public void save(RigaOrdine object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RigaOrdine> findBy(String field, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RigaOrdine> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RigaOrdine findOne(String field, Object value) {
		// TODO Auto-generated method stub
		return null;
	}


}
