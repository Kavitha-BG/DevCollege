package com.devcollege.exceptions;

public class CourseNotFoundException extends Exception{
//     private String message;
//
//    @Override
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public CourseNotFoundException(String message) {
//        super(message);
//    }

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

    public CourseNotFoundException(String value, String message, String passedValue) {
        super(String.format("%s not found %s %s ",value, message,passedValue));
        this.value = value;
        this.message = message;
        this.passedValue = passedValue;
    }

}
