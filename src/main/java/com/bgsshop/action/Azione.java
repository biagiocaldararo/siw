package com.bgsshop.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public interface Azione {
	public String esegui(HttpServletRequest request) throws ServletException;
}
