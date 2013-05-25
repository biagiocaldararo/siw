package com.bgs_shop.persistence;

import java.sql.*;

public class DataSource {
	private String dbURI = "jdbc:sqlite:bgsshop.sqlite";

	public Connection getConnection() throws PersistenceException {
		// System.out.println(System.getProperty("user.dir"));
		Connection connection = null;
		try {
		    Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection(dbURI);
		} catch (ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
}
