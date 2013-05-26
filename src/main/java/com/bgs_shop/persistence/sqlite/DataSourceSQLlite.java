package com.bgs_shop.persistence.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.bgs_shop.persistence.DataSource;

public class DataSourceSQLlite implements DataSource {
	private String dbURI = "jdbc:sqlite:bgsshop.sqlite";

	@Override
	public Connection getConnection(){
		// System.out.println(System.getProperty("user.dir"));
		Connection connection = null;
		try {
		    Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection(dbURI);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
