package it.uniroma3.persistence;

public class PersistenceException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public PersistenceException(String messaggio){
		super(messaggio);
	}

}
