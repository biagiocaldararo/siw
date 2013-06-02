package com.bgsshop.persistence;

import java.sql.Connection;

public interface DataSource {
	public Connection getConnection();
}
