package com.devcollege.advice;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import com.devcollege.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class MyControllerAdvice {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Map<String,String>> handleEmptyInput(NotFoundException ex) {
		Map<String,String> errorMessage = new HashMap<>();
		errorMessage.put("message",ex.getPassedValue()+" doesn't exist " );
		errorMessage.put("error","Failed to add details");
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}



	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException) {
		return new ResponseEntity<String>("Failed to add details.", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementFoundException.class)
	public ResponseEntity<String> handleNoSuchElementFoundException(NoSuchElementFoundException noSuchElementFoundException) {
		return new ResponseEntity<String>("Failed to update details.", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<String> handleInvalidInputException(InvalidInputException noSuchElementFoundException) {
		return new ResponseEntity<String>("Failed to Delete details.", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException studentNotFoundException) {
		return new ResponseEntity<String>("Failed to Get Student details.", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<String> handleCourseNotFoundException(CourseNotFoundException courseNotFoundException) {
		return new ResponseEntity<String>("Failed to Get Course details.", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EnrollmentNotFoundException.class)
	public ResponseEntity<String> handleEnrollmentNotFoundException(EnrollmentNotFoundException enrollmentNotFoundException) {
		System.out.println("++++++++++++++++======="+enrollmentNotFoundException.getMessage());
		return new ResponseEntity<String>(enrollmentNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
	}


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

//	@Override
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<Object> handleMethodArgumentNotValid(
//			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//		Map<String, String> errors = new HashMap<>();
//		ex.getBindingResult().getAllErrors().forEach((error) ->{
//
//			String fieldName = ((FieldError) error).getField();
//			String message = error.getDefaultMessage();
//			errors.put(fieldName, message);
//		});
//		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
//	}

    public static class ErrorDto {
        private List<String> detailedMessages;

        public ErrorDto( List<String> detailedMessages) {
            this.detailedMessages = detailedMessages;
        }

		public ErrorDto() {  }

		public List<String> getDetailedMessages() {
			return detailedMessages;
		}

		public void setDetailedMessages(List<String> detailedMessages) {
			this.detailedMessages = detailedMessages;
		}

		//if there is no suitable handler.
		@ExceptionHandler(Exception.class)
		public ResponseEntity<Map<String, String>> handleException(Exception exception) throws IOException {
		    Map<String, String> errorResponse = new HashMap<>();
		    errorResponse.put("message", exception.getLocalizedMessage());
		    errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
		    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

    }
	
}
