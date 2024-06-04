package com.management.user.exception;

public class BidNotFoundException extends RuntimeException {

    private String errorMessage;
    public BidNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
