package com.bgs_shop.persistence;


public interface DAO<T> {
	void insert(T object); 
	void delete(T object); 
	void update(T object);
	void save(T object);
	T[] all();
}
