package com.bgsshop.persistence.postgres;

import java.sql.*;
import java.util.*;

import com.bgsshop.model.*;
import com.bgsshop.persistence.*;


public class ProdottoDAOPostgres implements ProdottoDAO {
	private DataSource data;
	private PreparedStatement statement;
	private Connection connection;
	private IdBroker broker = new ProductIdBroker();

	@Override
	public boolean insert(Prodotto prodotto) {
		this.data = new DataSourcePostgres();
		String insert = "insert into prodotto(id, nome, descrizione, prezzo) values (?,?,?,?)";
		int inserito = 0;
		
		try {
			this.connection = data.getConnection();
			long id = this.broker.getId(this.connection);
			prodotto.setId(id);
			this.statement = this.connection.prepareStatement(insert);
			this.statement.setLong(1, id);
			this.statement.setString(2, prodotto.getNome());
			this.statement.setString(3, prodotto.getDescrizione());
			this.statement.setDouble(4, prodotto.getPrezzo());
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
	public boolean delete(Prodotto prodotto) {
		this.data = new DataSourcePostgres();
		String delete = "delete from prodotto where id=?";
		int eliminato = 0;
		
		try {
			this.connection = data.getConnection();
			this.statement = this.connection.prepareStatement(delete);
			this.statement.setLong(1, prodotto.getId());
			eliminato = this.statement.executeUpdate();
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

		return eliminato!=0;
	}

	@Override
	public boolean update(Prodotto prodotto) {
		this.data = new DataSourcePostgres();
		String update = "update prodotto set nome =?, descrizione=?, prezzo=? where id=?";
		int aggiornato= 0;
		
		try {
			this.connection = data.getConnection();
			this.statement = this.connection.prepareStatement(update);
			this.statement.setString(1, prodotto.getNome());
			this.statement.setString(2, prodotto.getDescrizione());
			this.statement.setDouble(3, prodotto.getPrezzo());
			this.statement.setLong(4, prodotto.getId());
			aggiornato = this.statement.executeUpdate();
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

		return aggiornato!=0;
	}

	@Override
	public Prodotto findById(long id) {
		this.data = new DataSourcePostgres();
		String query= "select * from prodotto where id=?";
		Prodotto prodotto = null;	
		
		try {
			this.connection = data.getConnection();
			this.statement = this.connection.prepareStatement(query);
			this.statement.setLong(1, id);
			ResultSet r = statement.executeQuery();
			while (r.next()) 
				prodotto = new Prodotto(r.getLong("id"), r.getString("nome"), r.getString("descrizione"), r.getDouble("prezzo"));
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

		return prodotto;
	}
	
	@Override
	public List<Prodotto> findAll() {
		this.data = new DataSourcePostgres();
		String query= "select * from prodotto";
		List<Prodotto> prodotti = new LinkedList<Prodotto>();	
		
		try {
			this.connection = data.getConnection();
			this.statement = this.connection.prepareStatement(query);
			ResultSet r = statement.executeQuery();
			while (r.next()) {
				Prodotto prodotto = new Prodotto(r.getLong("id"), r.getString("nome"), r.getString("descrizione"), r.getDouble("prezzo"));
				prodotti.add(prodotto);
			}
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

		return prodotti;
	}
}
