//package com.devcollege.exceptions;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import javax.validation.ConstraintViolation;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import com.devcollege.advice.MyControllerAdvice.ErrorDto;
//import com.devcollege.payloads.ApiResponse;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//	
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ApiResponse> handleStudentNotFoundException(ResourceNotFoundException studentNotFoundException) {
//		String message = studentNotFoundException.getMessage();
//		ApiResponse apiResponse = new ApiResponse(message, false);
//		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
//	}
//	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException ex) {
//		
//		Map<String, String> resp =new HashMap<>();
//		ex.getBindingResult().getAllErrors().forEach((error) -> {
//			String fieldName =((FieldError) error)
//		}
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
//
//    }
//	
//	
//}
