package com.devcollege.exceptions;

public class CourseNotFoundException extends Exception{
     private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CourseNotFoundException(String message) {
        super(message);
    }


}
