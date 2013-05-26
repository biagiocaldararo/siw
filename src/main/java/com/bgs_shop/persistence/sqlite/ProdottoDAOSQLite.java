package com.bgs_shop.persistence.sqlite;

import java.sql.*;
import java.util.*;
import com.bgs_shop.model.*;
import com.bgs_shop.persistence.*;


public class ProdottoDAOSQLite implements ProdottoDAO {
	private DataSource data;
	private PreparedStatement statement;
	private Connection connection;

	@Override
	public boolean insert(Prodotto prodotto) {
		this.data = new DataSourceSQLite();
		String insert = "insert into prodotto(nome, descrizione, prezzo) values (?,?,?)";
		int inserito = 0;
		
		try {
			this.connection = data.getConnection();
			this.statement = this.connection.prepareStatement(insert);
			this.statement.setString(1, prodotto.getNome());
			this.statement.setString(2, prodotto.getDescrizione());
			this.statement.setDouble(3, prodotto.getPrezzo());
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
		this.data = new DataSourceSQLite();
		String delete = "delete from prodotto where cod=?";
		int eliminato = 0;
		
		try {
			this.connection = data.getConnection();
			this.statement = this.connection.prepareStatement(delete);
			this.statement.setLong(1, prodotto.getCod());
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
		this.data = new DataSourceSQLite();
		String update = "update prodotto set nome =?, descrizione=?, prezzo=? where cod=?";
		int aggiornato= 0;
		
		try {
			this.connection = data.getConnection();
			this.statement = this.connection.prepareStatement(update);
			this.statement.setString(1, prodotto.getNome());
			this.statement.setString(2, prodotto.getDescrizione());
			this.statement.setDouble(3, prodotto.getPrezzo());
			this.statement.setLong(4, prodotto.getCod());
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
	public Prodotto findByCod(long cod) {
		this.data = new DataSourceSQLite();
		String query= "select cod, nome, descrizione, prezzo from prodotto where cod=?";
		Prodotto prodotto = null;	
		
		try {
			this.connection = data.getConnection();
			this.statement = this.connection.prepareStatement(query);
			this.statement.setLong(1, cod);
			ResultSet r = statement.executeQuery();
			while (r.next()) 
				prodotto = new Prodotto(r.getLong("cod"), r.getString("nome"), r.getString("descrizione"), r.getDouble("prezzo"));
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
		this.data = new DataSourceSQLite();
		String query= "select cod, nome, descrizione, prezzo from prodotto";
		List<Prodotto> prodotti = new LinkedList<Prodotto>();	
		
		try {
			this.connection = data.getConnection();
			this.statement = this.connection.prepareStatement(query);
			ResultSet r = statement.executeQuery();
			while (r.next()) {
				Prodotto prodotto = new Prodotto(r.getLong("cod"), r.getString("nome"), r.getString("descrizione"), r.getDouble("prezzo"));
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
