package com.bgsshop.mvc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Azione {
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected String[] params;
	protected String template;
	
	public Azione() {}
	
	public void init(HttpServletRequest request, HttpServletResponse response, String[] params) {
		this.request = request;
		this.response = response;
		this.params = params;
	}

	public int esegui() throws Exception {
		try {
			String methodName = request.getMethod().toLowerCase();
			Method method = this.getClass().getMethod(methodName);
			return (int) method.invoke(this);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException |
				 IllegalArgumentException e) {
			throw new HttpException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		} catch (InvocationTargetException e) {
			 if (e.getCause() instanceof Exception)
				 throw (Exception) e.getCause();
			 throw (Error) e.getCause();
		}
	}
	
	
	protected int ok(String template) {
		setTemplate(template);
		return HttpServletResponse.SC_OK;
	}
	
	protected int forbidden(String template) {
		setTemplate(template);
		return HttpServletResponse.SC_FORBIDDEN;
	}
	
	protected int redirect(String location) {
		setTemplate(location);
		return HttpServletResponse.SC_FOUND;
	}
	
	protected void setTemplate(String template) {
		this.template = template;
	}
	
	public String getTemplate() {
		return template;
	}
}
