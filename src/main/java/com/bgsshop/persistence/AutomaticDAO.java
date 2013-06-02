package com.bgsshop.persistence;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.bgsshop.Utils;
import com.bgsshop.persistence.sqlite.DataSourceSQLite;

public class AutomaticDAO<T> implements DAO<T> {

	// private Class<? extends Model> model;
	// private Field[] fields;
	private Map<String, Field> fields;
	private Field id;
	private DataSource datasource;
	private Class<T> model;
	private String name;

	public AutomaticDAO(Class<T> model) {
		this.model = model;
		
		//List<Field> fields = new ArrayList<Field>();
		fields = new HashMap<String, Field>();
		for (Field field : model.getDeclaredFields()) {
			Column annotation = field.getAnnotation(Column.class);
			if (annotation != null) {
				field.setAccessible(true);
				switch (annotation.value()) {
					case ID:
						this.id = field;
						break;
					case COLUMN:
						fields.put(field.getName(), field);
				}
			}
			
		}
		if (this.id == null)
			throw new PersistenceException("Un model deve definire una Column di tipo ID.");
		
		if (fields.size() == 0)
			throw new PersistenceException("Un model deve definire almeno una 'Column'.");
		
		//this.fields = fields.toArray(new Field[fields.size()]);
		
		datasource = new DataSourceSQLite();
		name = model.getSimpleName().toLowerCase();
	}

	public void save(T instance) {
		Long a = getId(instance);
		if (a == null)
			insert(instance);
		else
			update(instance);
	}
	
	public void insert(T instance) {
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = datasource.getConnection();
			stmt = conn.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			for (Field field : fields.values())
				stmt.setObject(i++, field.get(instance));
			
			stmt.execute();
			ResultSet res = stmt.getGeneratedKeys();
			res.next();
			
			id.set(instance, res.getLong(1));
			
		} catch (IllegalAccessException|SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Qualcosa è andato storto");
		} finally {
			// http://stackoverflow.com/questions/2225221/closing-database-connections-in-java
			try { stmt.close(); } catch (Exception e) { }
			try { conn.close(); } catch (Exception e) { }
		}		
	}
	
	public void update(T instance) {
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = datasource.getConnection();
			stmt = conn.prepareStatement(getUpdateQuery());
			
			int i = 1;
			for (Field field : fields.values())
				stmt.setObject(i++, field.get(instance));
			
			stmt.setLong(i, getId(instance));
			
			stmt.executeUpdate();
			
		} catch (IllegalAccessException|SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Qualcosa è andato storto");
		} finally {
			try { stmt.close(); } catch (Exception e) { }
			try { conn.close(); } catch (Exception e) { }
		}
	}
	
	public void delete(T instance) {
		Long id = getId(instance);
		if (id == null)
			return;

		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = datasource.getConnection();
			stmt = conn.prepareStatement(String.format("DELETE FROM %s WHERE id=?", name));
			
			stmt.setLong(1, getId(instance));
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Qualcosa è andato storto");
		} finally {
			try { stmt.close(); } catch (Exception e) { }
			try { conn.close(); } catch (Exception e) { }
		}		
		
	}
	
	public T[] all() {
		Statement stmt = null;
		Connection conn = null;
		ResultSet res = null;
		conn = datasource.getConnection();
		int count;
		
		try {
			stmt = conn.createStatement();
			res = stmt.executeQuery(String.format("SELECT COUNT(*) FROM %s", name));
			res.next();
			count = res.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Qualcosa è andato storto");
		} finally {
			try { res.close(); } catch (Exception e) { }
			try { stmt.close(); } catch (Exception e) { }
		}
			
		try {
			String names = Utils.join(fields.keySet(), ", ");
			String query = String.format("SELECT %s, id FROM %s", names, name);
			
			stmt = conn.createStatement();
			res = stmt.executeQuery(query);
			
			@SuppressWarnings("unchecked")
			T[] models = (T[]) Array.newInstance(this.model, count);
			
			int i = 0;
			while (res.next()) {
				T model = this.model.newInstance();
				for (Field field : fields.values())
					field.set(model, res.getObject(field.getName()));
				id.set(model, res.getLong("id"));
				models[i++] = model;
			}
			return models;
			
		} catch (SQLException | IllegalArgumentException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new PersistenceException("Qualcosa è andato storto");
		} finally {
			try { stmt.close(); } catch (Exception e) { }
			try { conn.close(); } catch (Exception e) { }
		}
	}

	
	private String getInsertQuery() {
		String query = "INSERT INTO %s(%s) VALUES (%s)";
		String[] placeholders = new String[fields.size()];
		for (int i = 0; i < fields.size(); i++)
			placeholders[i] = "?";
		String names = Utils.join(fields.keySet(), ",");
		return String.format(query, name, names, Utils.join(placeholders, ","));
	}
	
	private String getUpdateQuery() {
		String query = "UPDATE %s SET %s=? WHERE id=?";
		return String.format(query, name, Utils.join(fields.keySet(), "=?, "));
	}
	
	public Long getId(T instance) {
			try {
				return (Long) id.get(instance);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				throw new PersistenceException("Qualcosa è andato storto");
			}
	}
	
}
