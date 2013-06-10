package com.bgsshop.helper;

public interface Validator {
	
	public Object clean(String value) throws ValidationException;

}
