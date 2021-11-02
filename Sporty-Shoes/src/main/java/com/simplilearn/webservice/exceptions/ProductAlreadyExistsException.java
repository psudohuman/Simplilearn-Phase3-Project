package com.simplilearn.webservice.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	String message;

	public ProductAlreadyExistsException(String message) {
		super(message);
	}
	
}
