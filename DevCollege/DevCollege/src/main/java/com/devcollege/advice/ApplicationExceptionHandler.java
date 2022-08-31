//package com.devcollege.advice;
//
//import com.devcollege.entities.Enrollment;
//import com.devcollege.exceptions.CourseNotFoundException;
//import com.devcollege.exceptions.EnrollmentNotFoundException;
//import com.devcollege.exceptions.StudentNotFoundException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//public class ApplicationExceptionHandler {
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public Map<String,String> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
//        Map<String, String> invalidInputException = new HashMap<>();
//        invalidInputException.put("Error Message", "Invalid input, please enter valid input..");
//        return invalidInputException;
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
//        Map<String, String> invalidMethodArgument = new HashMap<>();
//        exception.getBindingResult().getFieldErrors().forEach(error -> {
//            invalidMethodArgument.put(error.getField(), error.getDefaultMessage());
//        });
//        return invalidMethodArgument;
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(StudentNotFoundException.class)
//    public Map<String,String> handleStudentNotFoundException(StudentNotFoundException exception) {
//        Map<String, String> studentError = new HashMap<>();
//        studentError.put("errorMessage", exception.getMessage());
//        return studentError;
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(CourseNotFoundException.class)
//    public Map<String,String> handleCourseNotFoundException(CourseNotFoundException exception) {
//        Map<String, String> courseError = new HashMap<>();
//        courseError.put("errorMessage", exception.getMessage());
//        return courseError;
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(EnrollmentNotFoundException.class)
//    public Map<String,String> handleEnrollmentNotFoundException(EnrollmentNotFoundException exception) {
//        Map<String, String> enrollmentError = new HashMap<>();
//        enrollmentError.put("errorMessage", exception.getMessage());
//        return enrollmentError;
//    }
//}
