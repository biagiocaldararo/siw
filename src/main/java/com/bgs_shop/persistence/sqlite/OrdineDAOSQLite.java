package com.bgs_shop.persistence.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import com.bgs_shop.model.Cliente;
import com.bgs_shop.model.Ordine;
import com.bgs_shop.model.Prodotto;
import com.bgs_shop.persistence.DataSource;
import com.bgs_shop.persistence.OrdineDAO;

public class OrdineDAOSQLite implements OrdineDAO {
	private DataSource data;
	private PreparedStatement statement;
	private Connection connection;

	@Override
	public boolean insert(Ordine ordine) {
		this.data = new DataSourceSQLite();
		String insert = "insert into ordine(data, stato, importo) values (?,?,?)";
		int inserito = 0;
		
		try {
			this.connection = data.getConnection();
			this.statement = this.connection.prepareStatement(insert);
			this.statement.setString(1, new Date().toString());
			this.statement.setString(2, ordine.getStato());
			this.statement.setDouble(3, ordine.getImporto());
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
	public Prodotto findByCod(long cod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ordine> findByCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

}
