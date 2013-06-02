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
	private PreparedStatement statement;
	private Connection connection;

	@Override
	public boolean insert(Ordine ordine, long idCliente) {
		this.data = new DataSourceSQLite();
		String insert = "insert into ordine(cliente, data, stato, importo) values (?,?,?,?)";
		int inserito = 0;
		
		try {
			this.connection = data.getConnection();
			this.statement = this.connection.prepareStatement(insert);
			this.statement.setLong(1, idCliente);
			this.statement.setString(2, new Date().toString());
			this.statement.setString(3, ordine.getStato());
			this.statement.setDouble(4, ordine.getImporto());
			inserito = this.statement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();	
		} 
		finally {
			try {
				if (this.statement != null) 
					this.statement.close();
				if (this.connection!= null)
					this.connection.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
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
	public List<Ordine> findByCliente(Utente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

}
