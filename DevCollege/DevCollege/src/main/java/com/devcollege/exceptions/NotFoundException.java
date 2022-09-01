package com.devcollege.exceptions;

public class NotFoundException extends RuntimeException{
    String value;

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

    String message;
    String passedValue;

    public NotFoundException(String value, String value2, String passedValue) {
        super(String.format("%s not found %s %s ",value, value2,passedValue));
        this.value = value;
        this.message = value2;
        this.passedValue = passedValue;
    }


}
