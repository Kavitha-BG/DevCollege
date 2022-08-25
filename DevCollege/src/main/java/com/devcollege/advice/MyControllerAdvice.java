package com.devcollege.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devcollege.exceptions.EmptyInputException;


@ControllerAdvice
public class MyControllerAdvice {
	
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException) {
		
		return new ResponseEntity<String>("Input field is Empty, Failed to add Student details.", HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(NoSuchElementFound.class)
//	public ResponseEntity<String> handleEmptyList(NoSuchElementFound noSuchElementFound) {
//		
//		return new ResponseEntity<String>("Input field is Empty, please look into it..", HttpStatus.NOT_FOUND);
//	}
}
