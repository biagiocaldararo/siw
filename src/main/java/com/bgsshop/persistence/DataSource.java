package com.bgsshop.persistence;

import java.sql.Connection;

public interface DataSource {
	public Connection getConnection();
	public Connection getConnection(boolean autoCommit);
}
