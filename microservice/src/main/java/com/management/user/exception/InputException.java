package com.management.user.exception;


public class InputException extends RuntimeException {
    private String errorMessage;
    public InputException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
