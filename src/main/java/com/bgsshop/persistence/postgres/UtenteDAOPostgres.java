package com.bgsshop.persistence.postgres;

import java.sql.*;

import java.util.*;

import com.bgsshop.model.*;
import com.bgsshop.persistence.*;


public class UtenteDAOPostgres implements UtenteDAO {
	private DataSource data;
	private PreparedStatement statement;
	private Connection connection;

	@Override
	public boolean insert(Utente utente) {
		this.data = new DataSourcePostgres();
		String insert = "insert into utente(username, password, ruolo) values (?,?,?)";
		int inserito = 0;
		
		try {
			this.connection = this.data.getConnection();
			this.statement = this.connection.prepareStatement(insert);
			this.statement.setString(1, utente.getUsername());
			this.statement.setString(2, utente.getPassword());
			this.statement.setString(3, utente.getRuolo());
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
	public boolean delete(Utente utente) {
		this.data = new DataSourcePostgres();
		String delete = "delete from utente where username=?";
		int eliminato = 0;
		
		try {
			this.connection = this.data.getConnection();
			this.statement = this.connection.prepareStatement(delete);
			this.statement.setString(1, utente.getUsername());
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
	public boolean update(Utente utente) {
		this.data = new DataSourcePostgres();
		String update = "update utente set password=?, ruolo=? where username=?";
		int aggiornato= 0;
		
		try {
			this.connection = this.data.getConnection();
			this.statement = this.connection.prepareStatement(update);
			this.statement.setString(1, utente.getPassword());
			this.statement.setString(2, utente.getRuolo());
			this.statement.setString(3, utente.getUsername());
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
	public Utente findByUsername(String username) {
		this.data = new DataSourcePostgres();
		String query= "select username, password, ruolo from utente where username=?";
		Utente utente = null;	
		
		try {
			this.connection = this.data.getConnection();
			this.statement = this.connection.prepareStatement(query);
			this.statement.setString(1, username);
			ResultSet r = this.statement.executeQuery();
			while (r.next()) 
				utente = new Utente(r.getLong("id"), r.getString("username"), r.getString("password"), r.getString("ruolo"));
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

		return utente;
	}
	
	@Override
	public List<Utente> findAll() {
		this.data = new DataSourcePostgres();
		String query= "select username, password, ruolo from utente";
		List<Utente> utenti = new LinkedList<Utente>();	
		
		try {
			this.connection = this.data.getConnection();
			this.statement = this.connection.prepareStatement(query);
			ResultSet r = this.statement.executeQuery();
			while (r.next()) {
				Utente utente = new Utente(r.getLong("id"), r.getString("username"), r.getString("password"), r.getString("ruolo"));
				utenti.add(utente);
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

		return utenti;
	}
}

