package com.bgsshop.persistence;

import java.util.Collection;
import java.util.List;

import com.bgsshop.model.RigaOrdine;

// levare filter / get dall'interfaccia
// sono troppo difficili per un DAO semplice
public interface DAO<T> {
	void insert(T object); 
	void delete(T object); 
	void update(T object);
	void save(T object);
	List<T> select(T query);
	T get(T query);
	List<T> findBy(String field, Object value);
	List<T> findAll();
	T findOne(String field, Object value);
	List<T> in(String field, Collection<?> values);
	Integer count(T query);
}
