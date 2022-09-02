package com.devcollege.exceptions;

import com.devcollege.entities.Student;

public class StudentNotFoundException extends RuntimeException {

//	private String message;
//
//	@Override
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public StudentNotFoundException(String message) {
//		super(message);
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

	public StudentNotFoundException(String value, String message, String passedValue) {
		super(String.format("%s not found %s %s ",value, message,passedValue));
		this.value = value;
		this.message = message;
		this.passedValue = passedValue;
	}
}
