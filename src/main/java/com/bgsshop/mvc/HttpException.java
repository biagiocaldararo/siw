package com.bgsshop.mvc;

public class HttpException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int status = 500;
	
	public HttpException() {};
	
	public HttpException(int status) {
		this.status = status;
	}
	
	public HttpException(int status, String message) {
		super(message);
		this.status = status;
	}
	
	public HttpException(Throwable cause) {
		super(cause);
	}

	public int getStatus() {
		return status;
	}
	
}
