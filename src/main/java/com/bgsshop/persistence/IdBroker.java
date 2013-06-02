package com.bgsshop.persistence;

import java.sql.Connection;

public interface IdBroker {
	public long getId(Connection connection);
}
