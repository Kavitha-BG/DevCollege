package com.devcollege.exceptions;


public class NoSuchElementFoundException extends RuntimeException {
	
//	private static final long serialVersionUID = 1L;
//	private String errorCode;
//	private String errorMessage;
//
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
//	public NoSuchElementFoundException(String errorMessage) {
//		super(errorMessage);
//	}
//
//	public NoSuchElementFoundException() {
//
//	}
//	public NoSuchElementFoundException(String errorCode, String errorMessage) {
//		super();
//		this.errorCode = errorCode;
//		this.errorMessage = errorMessage;
//	}

	String value;
	String message;
	String passedValue;

	@Override
	public String toString() {
		return "" +
				"value='" + value + '\'' +
				", message='" + message + '\'' +
				", passedValue='" + passedValue + '\'' +
				'}';
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPassedValue() {
		return passedValue;
	}

	public void setPassedValue(String passedValue) {
		this.passedValue = passedValue;
	}

	public NoSuchElementFoundException(String value, String value2, String passedValue) {
		super(String.format("%s not found %s %s ",value, value2,passedValue));
		this.value = value;
		this.message = value2;
		this.passedValue = passedValue;
	}
	
}
