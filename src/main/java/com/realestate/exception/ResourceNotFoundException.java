package com.realestate.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)yrouma aldik gerek kalmadi buna cunku bu exceptionu bir responseentity olarak olusturup donuyorum
public class ResourceNotFoundException extends RuntimeException {


	public ResourceNotFoundException(String message) {
		super(message);
	}
}
