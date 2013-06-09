package com.bgsshop.persistence.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdBroker {
	private String query;
	
	public IdBroker(String sequence){
		this.query = "select nextval('"+sequence+"') as id;";
	}
	
	public long getNextId(Connection connection) {
		long id = 0;
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			result.next();
			id = result.getLong("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
}
