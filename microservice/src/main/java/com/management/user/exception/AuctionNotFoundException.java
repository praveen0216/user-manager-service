package com.management.user.exception;

public class AuctionNotFoundException extends RuntimeException {

    private String errorMessage;
    public AuctionNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
