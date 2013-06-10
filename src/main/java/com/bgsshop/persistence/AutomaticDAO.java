package com.bgsshop.persistence;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bgsshop.Utils;
import com.bgsshop.persistence.sqlite.DataSourceSQLite;

public class AutomaticDAO<T> implements DAO<T> {

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
		try (Connection conn = datasource.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {
			int i = 1;
			for (Field field : fields.values())
				stmt.setObject(i++, field.get(instance));
			
			stmt.execute();
			ResultSet res = stmt.getGeneratedKeys();
			res.next();
			
			id.set(instance, res.getLong(1));
		} catch (IllegalAccessException | SQLException e) {
			throw new PersistenceException("Qualcosa è andato storto", e);
		}		
	}
	
	public void update(T instance) {
		try (Connection conn = datasource.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(getUpdateQuery())) {
			int i = 1;
			for (Field field : fields.values())
				stmt.setObject(i++, field.get(instance));
			
			stmt.setLong(i, getId(instance));
			
			stmt.executeUpdate();
			
		} catch (IllegalAccessException|SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Qualcosa è andato storto");
		}
	}
	
	public void delete(T instance) {
		Long id = getId(instance);
		if (id == null)
			return;

		String query = String.format("DELETE FROM %s WHERE id=?", name);
		try (Connection conn = datasource.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setLong(1, getId(instance));
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Qualcosa è andato storto");
		}
	}

	public List<T> select(T instance) {
		Map<String, Object> params = prepareParams(instance);
		String query = getFilterQuery(params);
		
		try (Connection conn = datasource.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(query)) {

			int i = 1;
			if (params != null)
				for (Object param : params.values())
					stmt.setObject(i++, param);

			return createModels(stmt.executeQuery());
			
		} catch (SQLException | IllegalArgumentException e) {
			throw new PersistenceException("Qualcosa è andato storto", e);
		}
	}
	
	public Integer count(T instance) {
		String query = String.format("SELECT COUNT(*) FROM %s%s",
				name, getWhereQuery(prepareParams(instance)));
		
		try (Connection conn = datasource.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(query)) {
			
			ResultSet res = stmt.executeQuery();
			
			res.next();
			return res.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Qualcosa è andato storto", e);
		}
		
	}
	
	private List<T> createModels(ResultSet res) {
		try {
			List<T> models = new ArrayList<T>();
			while (res.next()) {
				T model = this.model.newInstance();
				for (Field field : fields.values())
					field.set(model, res.getObject(field.getName()));
				this.id.set(model, res.getLong("id"));
				models.add(model);
			}
			return models;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SQLException e) {
			throw new PersistenceException("Errore nella creazione del modello", e);
		}
	}
	
	public List<T> findAll() {
		return select(null);
	}
	
	public T get(T instance) {
		List<T> models = select(instance);
		if (models == null || models.size() == 0)
			throw new ModelNotFoundException();
		if (models.size() > 1)
			throw new MultipleObjectsException();
		return models.get(0);
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
		return String.format("SELECT %s, id FROM %s%s", select, name, getWhereQuery(params));
	}
	
	private String getWhereQuery(Map<String, Object> params) {
		if (params == null) return "";
		return String.format(" WHERE %s=?", Utils.join(params.keySet(), "=? AND "));
	}
	
	private String placeholders(int count) {
		String[] placeholders = new String[count];
		for (int i = 0; i < count; i++)
			placeholders[i] = "?";
		return Utils.join(placeholders, ",");
	}

	private String getInsertQuery() {
		String query = "INSERT INTO %s(%s) VALUES (%s)";
		String names = Utils.join(fields.keySet(), ",");
		return String.format(query, name, names, placeholders(fields.size()));
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

	@Override
	public List<T> findBy(String field, Object value) {
		try {
			T model = this.model.newInstance();
			("id".equals(field) ? id : fields.get(field)).set(model, value);
			return select(model);
		} catch (InstantiationException | IllegalAccessException | NullPointerException e) {
			throw new PersistenceException("Qualcosa è andato storto", e);
		}
	}

	@Override
	public T findOne(String field, Object value) {
		List<T> models = findBy(field, value);
		if (models == null || models.size() == 0)
			throw new PersistenceException("Model non trovato.");
		if (models.size() > 1)
			throw new PersistenceException("Più di un model trovato con findOne.");
		return models.get(0);
	}
	
	public List<T> in(String field, Collection<?> values) {
		String query = String.format("SELECT %s, id FROM %s WHERE %s IN (%s)",
				Utils.join(fields.keySet(), ","), name, field, placeholders(values.size()));
		try (Connection conn = datasource.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(query)) {
			
			int i = 1;
			for (Object value : values)
				stmt.setObject(i++, value);
			
			return createModels(stmt.executeQuery());
			
		} catch (SQLException e) {
			throw new PersistenceException("Qualcosa è andato storto", e);
		}
		
	}
	
}
