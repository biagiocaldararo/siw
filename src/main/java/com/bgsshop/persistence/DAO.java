package com.bgsshop.persistence;

import java.util.List;

public interface DAO<T> {
	boolean insert(T object); 
	boolean delete(T object); 
	boolean update(T object);
	T findById(long id);
	T findByString(String string);
	List<T> findAll();
}
