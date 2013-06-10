package com.bgsshop.persistence;

public class PersistenceException extends RuntimeException {

	public PersistenceException() {}
	
	public PersistenceException(String message) {
		super(message);
	}
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = 3954917552148486763L;

}
