package com.bgsshop.persistence.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.bgsshop.persistence.DataSource;
import com.bgsshop.persistence.PersistenceException;

public class DataSourceSQLite implements DataSource {
	private String dbURI = "jdbc:sqlite:bgsshop.sqlite";

	@Override
	public Connection getConnection(){
		return getConnection(true);
	}

	@Override
	public Connection getConnection(boolean autoCommit) {
		// System.out.println(System.getProperty("user.dir"));
		Connection connection = null;
		try {
		    Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection(dbURI);
		    connection.setAutoCommit(autoCommit);
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Errore nella creazione della connessione", e);
		}	
		return connection;
	}
}
