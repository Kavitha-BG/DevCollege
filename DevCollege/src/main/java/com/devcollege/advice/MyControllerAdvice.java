package com.devcollege.advice;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devcollege.exceptions.EmptyInputException;
import com.devcollege.exceptions.InvalidInputException;
import com.devcollege.exceptions.NoSuchElementFoundException;
import com.devcollege.exceptions.StudentNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyControllerAdvice {
	
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException) {
		
		return new ResponseEntity<String>("Failed to add Student details.", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementFoundException.class)
	public ResponseEntity<String> handleNoSuchElementFoundException(NoSuchElementFoundException noSuchElementFoundException) {
		
		return new ResponseEntity<String>("Failed to update Student details.", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<String> handleInvalidInputException(InvalidInputException noSuchElementFoundException) {
		
		return new ResponseEntity<String>("Failed to Delete Student details.", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException studentNotFoundException) {

		return new ResponseEntity<String>("Failed to Get Student details.", HttpStatus.BAD_REQUEST);
	}
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(StudentNotFoundException.class)
//	public Map<String, String> handleStudentNotFoundException(StudentNotFoundException studentNotFoundException) {
//		Map<String, String> exception = new HashMap<>();
//		exception.put("errorMessage", studentNotFoundException.getMessage());
//
//		return exception;
//	}
	
	
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleException(MethodArgumentNotValidException ex) {

       ErrorDto dto = new ErrorDto();

        dto.setDetailedMessages(ex.getBindingResult().getAllErrors().stream()
            .map(err -> err.unwrap(ConstraintViolation.class))
            .map(err -> String.format("'%s' %s", err.getPropertyPath(), err.getMessage()))
            .collect(Collectors.toList()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);

    }
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public Map<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
//		Map<String,String> errorMap = new HashMap<>();
//		methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(error -> {
//			errorMap.put(error.getField(),error.getDefaultMessage());
//		});
//		return errorMap;
//	}

	
	
	
	
	
	
	
//	@ExceptionHandler(value = MethodArgumentNotValidException.class)
//	   public ResponseEntity<String> handleException(MethodArgumentNotValidException ex, WebRequest req){
//		return new ResponseEntity<String>("Failed to Get the details.", HttpStatus.BAD_REQUEST);
//	   // Build your custom response object and access the exception message using ex.getMessage()
//	}
//	
	
	

    public static class ErrorDto {

//        private final int status;
//        private final String error;
//        private final String message;
        private List<String> detailedMessages;

        public ErrorDto( List<String> detailedMessages) {
//            status = httpStatus.value();
//            error = httpStatus.getReasonPhrase();
            this.detailedMessages = detailedMessages;
        }

		public ErrorDto() {

		}

		public List<String> getDetailedMessages() {
			return detailedMessages;
		}

		public void setDetailedMessages(List<String> detailedMessages) {
			this.detailedMessages = detailedMessages;
		}

//		public int getStatus() {
//			return status;
//		}
//
//		public String getError() {
//			return error;
//		}
//
//		public String getMessage() {
//			return message;
//		}

//		public ErrorDto(int status, String error, String message, List<String> detailedMessages) {
//			super();
//			this.status = status;
//			this.error = error;
//			this.message = message;
//			this.detailedMessages = detailedMessages;
//		}		
		
		//if there is no suitable handler.
//		@ExceptionHandler(Exception.class)
//		public ResponseEntity<Map<String, String>> handleException(
//		        Exception e) throws IOException {
//		    Map<String, String> errorResponse = new HashMap<>();
//		    errorResponse.put("message", e.getLocalizedMessage());
//		    errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
//		    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//		}

    }
	
}
