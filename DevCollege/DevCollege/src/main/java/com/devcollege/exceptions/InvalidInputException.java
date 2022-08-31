package com.devcollege.exceptions;


public class InvalidInputException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public InvalidInputException(String errorMessage) {
		super(errorMessage);
	}
	
	public InvalidInputException() {
		
	}
	public InvalidInputException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	
	
	
}
