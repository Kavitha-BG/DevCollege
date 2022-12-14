package com.devcollege.advice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import com.devcollege.exceptions.*;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ControllerAdvice
public class MyControllerAdvice {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Map<String,String>> handleEmptyInput(NotFoundException ex) {
		Map<String,String> errorMessage = new HashMap<>();
		errorMessage.put("message",ex.getPassedValue()+" doesn't exist " );
		errorMessage.put("error","Failed to access details");
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<Map<String,String>> handleNoDataFoundException(NoDataFoundException ex) {
		Map<String,String> errorMessage = new HashMap<>();
		errorMessage.put("error","No data Found");
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String,String>> handleConstraintViolationException(ConstraintViolationException ex) {
		Map<String,String> errorMessage = new HashMap<>();
		errorMessage.put("detailedMessage","You are not able to enroll to this course, " +
				"because you have already enrolled the course..!!");
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Map<String,String>> handleConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
		Map<String,String> errorMessage = new HashMap<>();
		errorMessage.put("detailedMessage","Course is not available for enrollment, It's full..!!");
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MissingPathVariableException.class)
	public ResponseEntity<Map<String,String>> handleMissingPathVariableException(MissingPathVariableException ex) {
		Map<String,String> errorMessage = new HashMap<>();
		errorMessage.put("detailedMessage","Id is missing..!!");
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

//	@ExceptionHandler(EmptyInputException.class)
//	public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException) {
//		return new ResponseEntity<String>("Failed to add details.", HttpStatus.BAD_REQUEST);
//	}

//new
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(NoSuchElementException.class)
//	public ResponseEntity<Map<String,String>> handleNoSuchElementException(NoSuchElementException ex) {
//		Map<String,String> errorMap = new HashMap<>();
//		errorMap.put("errorMessage", ex.getMessage());
//		return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@ExceptionHandler(InvalidInputException.class)
//	public ResponseEntity<String> handleInvalidInputException(InvalidInputException noSuchElementFoundException) {
//		return new ResponseEntity<String>("Failed to Delete details.", HttpStatus.BAD_REQUEST);
//	}
//
//	@ExceptionHandler(StudentNotFoundException.class)
//	public ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException studentNotFoundException) {
//		return new ResponseEntity<String>("Failed to Get Student details.", HttpStatus.BAD_REQUEST);
//	}
//
//	@ExceptionHandler(CourseNotFoundException.class)
//	public ResponseEntity<String> handleCourseNotFoundException(CourseNotFoundException courseNotFoundException) {
//		return new ResponseEntity<String>("Failed to Get Course details.", HttpStatus.BAD_REQUEST);
//	}
//
//	@ExceptionHandler(EnrollmentNotFoundException.class)
//	public ResponseEntity<Map<String,String>> handleEnrollmentNotFoundException(EnrollmentNotFoundException ex) {
//		Map<String,String> errorMap = new HashMap<>();
//		errorMap.put("errorMessage", ex.getMessage());
//		return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
//	}


//	@ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorDto> handleException(MethodArgumentNotValidException ex) {
//		ErrorDto dto = new ErrorDto();
//        dto.setDetailedMessages(ex.getBindingResult().getAllErrors().stream()
//            .map(err -> err.unwrap(ConstraintViolation.class))
//            .map(err -> String.format("'%s' %s", err.getPropertyPath(), err.getMessage()))
//            .collect(Collectors.toList()));
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
//    }

//new

	@ExceptionHandler(ApiResponseException.class)
	public ResponseEntity<ApiResponseException> handleApiResponseException (ApiResponseException ex) {
		String message = ex.getMessage();
		ApiResponseException apiResponseException = new ApiResponseException(message, false);
		return new ResponseEntity<ApiResponseException>(apiResponseException, HttpStatus.BAD_REQUEST);
	}


//	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String,String>> handleException(HttpMessageNotReadableException exception) {
		Throwable cause = exception.getCause();
		Map<String,String> errorMap = new HashMap<>();
		if (cause instanceof MismatchedInputException){
			MismatchedInputException mismatchedInputException = (MismatchedInputException) cause;
			errorMap.put( "Invalid Input. " , mismatchedInputException.getPath().get(0).getFieldName());
		}
		return new ResponseEntity<Map<String,String>>(errorMap,null,HttpStatus.BAD_REQUEST);
	}

//	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errorMap.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
	}

//    public static class ErrorDto {
//        private List<String> detailedMessages;
//
//        public ErrorDto( List<String> detailedMessages) {
//            this.detailedMessages = detailedMessages;
//        }
//
//		public ErrorDto() {  }
//
//		public List<String> getDetailedMessages() {
//			return detailedMessages;
//		}
//
//		public void setDetailedMessages(List<String> detailedMessages) {
//			this.detailedMessages = detailedMessages;
//		}
//
//		//if there is no suitable handler.
////		@ExceptionHandler(Exception.class)
////		public ResponseEntity<Map<String, String>> handleException(Exception exception) throws IOException {
////		    Map<String, String> errorResponse = new HashMap<>();
////		    errorResponse.put("message", exception.getLocalizedMessage());
////		    errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
////		    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
////		}
//
//    }
//
}
