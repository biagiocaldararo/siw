package com.bgsshop.helper;

public class NotEmptyValidator implements Validator {

	@Override
	public Object clean(String value) throws ValidationException {
		if (value == null || value.isEmpty())
			throw new ValidationException("Questo campo non può essere vuoto");
		return value;
	}

}
