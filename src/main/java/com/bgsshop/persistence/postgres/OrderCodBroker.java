package com.bgsshop.persistence.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bgsshop.persistence.CodBroker;

public class OrderCodBroker implements CodBroker {
	private static final String query = "select nextval('sequenza_ordine') as cod;";
	
	@Override
	public long getCod(Connection connection) {
		long cod = 0;
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			result.next();
			cod = result.getLong("cod");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cod;
	}

}
