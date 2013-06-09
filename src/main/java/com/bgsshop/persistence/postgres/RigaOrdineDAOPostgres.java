package com.bgsshop.persistence.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.bgsshop.model.RigaOrdine;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DataSource;

public class RigaOrdineDAOPostgres implements DAO<RigaOrdine> {
	private DataSource data;
	private IdBroker broker;
	private final static String INSERT_QUERY = "insert into rigaOrdine (id, ordine, prodotto, quantita, costo) values (?,?,?,?,?)";
	
	public RigaOrdineDAOPostgres() {
		data = new DataSourcePostgres();
		broker = new IdBroker("riga_ordine_id_seq");
	}
	
	@Override
	public boolean insert(RigaOrdine rigaOrdine) {
		int inserito = 0;
		
		try(Connection conn = data.getConnection();
			PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
			long id = broker.getNextId(conn);
			rigaOrdine.setId(id);
			stmt.setLong(1, id);
			stmt.setLong(2, rigaOrdine.getOrdine().getId());
			stmt.setLong(3, rigaOrdine.getProdotto().getId());
			stmt.setInt(4, rigaOrdine.getQuantita());
			stmt.setDouble(5, rigaOrdine.getCosto());
			inserito = stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return inserito!=0;
	}

	@Override
	public boolean delete(RigaOrdine rigaOrdine) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(RigaOrdine rigaOrdine) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RigaOrdine findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RigaOrdine findByString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RigaOrdine> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RigaOrdine> findByObject(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}