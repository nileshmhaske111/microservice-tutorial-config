package com.lcwd.user.service.services.exceptions;

public class ResourcenotFoundException extends RuntimeException{
	
	public ResourcenotFoundException() {
		super("Resource not found on server ");
	}
	
	public ResourcenotFoundException(String message) {
		super(message);
	}
}
