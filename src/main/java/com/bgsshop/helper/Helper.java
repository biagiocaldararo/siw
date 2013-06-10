package com.bgsshop.helper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.bgsshop.model.Model;
import com.bgsshop.model.Prodotto;

public abstract class Helper<T extends Model> {
	
	protected Map<String, String> data;
	protected Map<String, Object> cleanedData;
	protected Map<String, String> errors;
	
	protected Boolean isValid;
	protected T object;
	
//	public void clean() {
//		values = new HashMap<>();
//		for (String field : getFields())
//			values.put(field, request.getParameter(field));
//	}
	
	public Helper(HttpServletRequest request, T object) {
		this.data = new HashMap<>();
		this.object = object;
		for (String field : getFields())
			data.put(field, request.getParameter(field));
	}
	
	public void clean() {
		isValid = true;
		cleanedData = new HashMap<>();
		errors = new HashMap<>();
		for (String field: getFields()) {
			String value = data.get(field);
			try {
				cleanedData.put(field, getValidator(field).clean(value));
			} catch (ValidationException e) {
				errors.put(field, e.getMessage());
				isValid = false;
			}
		}
		// TODO: cleanModel dovrebbe essere eseguito sempre
		if (isValid)
			cleanModel();
	}
	
	public Validator getValidator(String field) {
		return new NotEmptyValidator();
	}
	
	public abstract String[] getFields();

	public abstract void cleanModel();
	
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	public boolean isValid() {
		if (isValid == null)
			clean();
		return isValid;
	}
	
	public T getObject() {
		return object;
	}
}
