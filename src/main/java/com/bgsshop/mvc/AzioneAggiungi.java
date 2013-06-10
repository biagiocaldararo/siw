package com.bgsshop.mvc;

import javax.servlet.http.HttpServletResponse;
import com.bgsshop.model.Model;

public abstract class AzioneAggiungi<T extends Model> extends AzioneModifica<T> {
	
	public int get() {
		return HttpServletResponse.SC_OK;
	}
	
}
