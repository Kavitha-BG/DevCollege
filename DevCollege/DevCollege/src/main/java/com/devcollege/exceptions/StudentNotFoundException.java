package com.devcollege.exceptions;

import com.devcollege.entities.Student;

public class StudentNotFoundException extends RuntimeException {

	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StudentNotFoundException(String message) {
		super(message);
	}

}
