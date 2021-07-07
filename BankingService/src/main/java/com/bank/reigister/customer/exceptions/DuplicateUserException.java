package com.bank.reigister.customer.exceptions;

public class DuplicateUserException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1213508454769811811L;

	public DuplicateUserException(String message){
		super(message);
	}
}
