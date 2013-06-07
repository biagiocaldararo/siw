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
import com.bgsshop.persistence.RigaOrdineDAO;

public class RigaOrdineDAOSQLite implements DAO<RigaOrdine> {
	private DataSource data;
	private final static String INSERT_QUERY = "insert into rigaOrdine(ordine,prodotto,quantita,costo) values (?,?,?,?)";
	
	public RigaOrdineDAOSQLite() {
		data = new DataSourceSQLite();
	}
	
	@Override
	public boolean insert(RigaOrdine rigaOrdine) {
		int inserito = 0;
		
		try(Connection conn = data.getConnection();
			PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
			stmt.setLong(1, rigaOrdine.getOrdine().getId());
			stmt.setLong(2, rigaOrdine.getProdotto().getId());
			stmt.setInt(3, rigaOrdine.getQuantita());
			stmt.setDouble(4, rigaOrdine.getCosto());
			inserito = stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return inserito!=0;
	}

	@Override
	public boolean delete(RigaOrdine rigaOrdine) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(RigaOrdine rigaOrdine) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RigaOrdine findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RigaOrdine findByString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RigaOrdine> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
