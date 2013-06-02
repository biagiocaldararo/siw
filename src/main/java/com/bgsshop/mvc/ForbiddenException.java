package com.bgsshop.mvc;

import javax.servlet.http.HttpServletResponse;

public class ForbiddenException extends HttpException {
	private static final long serialVersionUID = 1L;
	public ForbiddenException() {
		super(HttpServletResponse.SC_FORBIDDEN);
	};
	public ForbiddenException(String message) {
		super(HttpServletResponse.SC_FORBIDDEN, message);
	}
}