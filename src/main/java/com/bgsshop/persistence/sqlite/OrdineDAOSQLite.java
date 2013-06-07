package com.bgsshop.persistence.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import com.bgsshop.model.Utente;
import com.bgsshop.model.Ordine;
import com.bgsshop.model.Prodotto;
import com.bgsshop.persistence.DataSource;
import com.bgsshop.persistence.OrdineDAO;

public class OrdineDAOSQLite implements OrdineDAO {
	private DataSource data;
	private final static String INSERT_QUERY = "insert into ordine(utente, data, stato, importo) values (?,?,?,?)";

	public OrdineDAOSQLite() {
		data = new DataSourceSQLite();
	}
	
	@Override
	public boolean insert(Ordine ordine, long idUtente) {
		int inserito = 0;
		
		try (Connection conn = data.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
			stmt.setLong(1, idUtente);
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
	public Prodotto findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ordine> findByUtente(Utente utente) {
		// TODO Auto-generated method stub
		return null;
	}
}
