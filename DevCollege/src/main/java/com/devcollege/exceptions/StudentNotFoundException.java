package com.devcollege.exceptions;

public class StudentNotFoundException extends RuntimeException {
	
//	private static final long serialVersionUID = 1L;
//	private String errorCode;
//	private String errorMessage;
//	
//	public String getErrorCode() {
//		return errorCode;
//	}
//	public void setErrorCode(String errorCode) {
//		this.errorCode = errorCode;
//	}

	//	public String getErrorMessage() {
//		return errorMessage;
//	}
//	public void setErrorMessage(String errorMessage) {
//		this.errorMessage = errorMessage;
//	}
//
	public StudentNotFoundException(String message) {
		super(message);
	}
	
//	public StudentNotFoundException() {
//		super();
//	}
//	public StudentNotFoundException(String errorCode, String errorMessage) {
//		super();
//		this.errorCode = errorCode;
//		this.errorMessage = errorMessage;
//	}
	
}
