package com.bgsshop.mvc;

import javax.servlet.http.HttpServletResponse;

import com.bgsshop.helper.Helper;
import com.bgsshop.model.Model;

public abstract class AzioneModifica<T extends Model> extends AzioneDettaglio<T> {
	

	public int post() {
		Helper<T> helper = getHelper();
		
		if (!helper.isValid()) {
			request.setAttribute("errori", helper.getErrors());
			return HttpServletResponse.SC_OK;
		}
		
		helper.getObject().save();
		return redirect(getSuccessURL());
	}
	
	protected abstract Helper<T> getHelper();
	protected abstract String getSuccessURL();
}
