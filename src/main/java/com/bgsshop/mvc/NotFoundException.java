package com.bgsshop.mvc;

import javax.servlet.http.HttpServletResponse;

public class NotFoundException extends HttpException {
	private static final long serialVersionUID = 1L;
	public NotFoundException() {
		super(HttpServletResponse.SC_NOT_FOUND);
	};
	public NotFoundException(String message) {
		super(HttpServletResponse.SC_NOT_FOUND, message);
	}
}
