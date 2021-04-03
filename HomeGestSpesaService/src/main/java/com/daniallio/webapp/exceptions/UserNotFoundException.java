package com.daniallio.webapp.exceptions;

import java.io.Serializable;

public class UserNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2033293230285192083L;


		
	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
