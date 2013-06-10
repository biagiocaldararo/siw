package com.bgsshop.helper;

public class DoubleValidator implements Validator {

	@Override
	public Object clean(String value) throws ValidationException {
		try {
			return Double.valueOf(value);
		} catch (NumberFormatException | NullPointerException e) {
			throw new ValidationException("Questo campo deve essere un numero.");
		}
	}

}
