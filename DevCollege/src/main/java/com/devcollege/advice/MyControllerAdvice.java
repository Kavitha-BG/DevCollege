package com.devcollege.advice;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.devcollege.exceptions.EmptyInputException;

@ControllerAdvice
public class MyControllerAdvice {
	
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException) {
		
		return new ResponseEntity<String>("Failed to add Student details.", HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(NoSuchElementFound.class)
//	public ResponseEntity<String> handleEmptyList(NoSuchElementFound noSuchElementFound) {
//		
//		return new ResponseEntity<String>("Input field is Empty, please look into it..", HttpStatus.NOT_FOUND);
//	}
	
	
//	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception,HttpHeaders headers, HttpStatus status, WebRequest request) {
//		
//		return new ResponseEntity<Object>("Please change http method request", HttpStatus.NOT_FOUND);
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

    public static class ErrorDto {

//        private final int status;
//        private final String error;
//        private final String message;
        private List<String> detailedMessages;

        public ErrorDto(HttpStatus httpStatus, List<String> detailedMessages) {
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
//
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
        
        

    }
	
}
