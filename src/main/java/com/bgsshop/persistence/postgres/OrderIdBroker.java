package com.bgsshop.persistence.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bgsshop.persistence.IdBroker;

public class OrderIdBroker implements IdBroker {
	private static final String query = "select nextval('sequenza_ordine') as id;";
	
	@Override
	public long getId(Connection connection) {
		long id = 0;
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			result.next();
			id = result.getLong("id");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}

}
