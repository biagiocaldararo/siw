package it.uniroma3.persistence.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCodBroker {
	private static final String query = "select nextval('sequenza_prodotto') as cod;";

	private ProductCodBroker(){
	}
	
	public static long getCod(Connection connection) {
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
