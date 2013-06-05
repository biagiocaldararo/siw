package com.bgsshop.persistence;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	public List<T> filter(T instance) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet res = null;
		
		Map<String, Object> params = prepareParams(instance);
		String query = getFilterQuery(params);
		try {
			conn = datasource.getConnection();
			stmt = conn.prepareStatement(query);
			
			int i = 1;
			if (params != null)
				for (Object param : params.values())
					stmt.setObject(i++, param);

			res = stmt.executeQuery();
			
			List<T> models = new ArrayList<T>();
			i = 0;
			while (res.next()) {
				T model = this.model.newInstance();
				for (Field field : fields.values())
					field.set(model, res.getObject(field.getName()));
				this.id.set(model, res.getLong("id"));
				models.add(model);
			}
			return models;
			
		} catch (SQLException | IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
			throw new PersistenceException("Qualcosa è andato storto");
		} finally {
			try { stmt.close(); } catch (Exception e) { }
			try { conn.close(); } catch (Exception e) { }
		}
	}
	
	public List<T> all() {
		return filter(null);
	}
	
	private Map<String, Object> prepareParams(T instance) {
		if (instance == null)
			return null;
		
		try {
			Map<String, Object> params = new HashMap<>();
			for (Field field : fields.values()) {
				Object param = field.get(instance);
				if (param != null)
					params.put(field.getName(), param);
			}
			Object id = this.id.get(instance);
			if (id != null)
				params.put("id", id);
			
			return params;
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new PersistenceException(":P");
		}
	}
	
	private String getFilterQuery(Map<String, Object> params) {
		String select = Utils.join(fields.keySet(), ", ");
		
		if (params == null)
			return String.format("SELECT %s, id FROM %s", select, name);
		
		String where = Utils.join(params.keySet(), "=? AND ");
		return String.format("SELECT %s, id FROM %s WHERE %s=?", select, name, where);	
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
