package com.bgsshop.persistence;

import java.util.List;


public interface DAO<T> {
	void insert(T object); 
	void delete(T object); 
	void update(T object);
	void save(T object);
	List<T> filter(T object);
	List<T> all();
	T get(T object);
}
