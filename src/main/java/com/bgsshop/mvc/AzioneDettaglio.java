package com.bgsshop.mvc;

import javax.servlet.http.HttpServletResponse;

import com.bgsshop.model.Model;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.PersistenceException;

public abstract class AzioneDettaglio<T extends Model> extends AzioneProtetta {
	
	protected abstract DAO<T> getDAO();
	protected abstract Class<T> getModel();
	
	protected String getContextObjectName() {
		return getModel().getSimpleName().toLowerCase();
	}
	
	public T getObject() {
		DAO<T> dao = getDAO();
		try {
			return dao.findOne("id", Long.parseLong(params[0]));
		} catch (NumberFormatException | PersistenceException e) {
			throw new NotFoundException("Prodotto inesistente");
		}
	}
	
	public int get() {
		request.setAttribute(getContextObjectName(), getObject());
		return HttpServletResponse.SC_OK;
	}
	
}
