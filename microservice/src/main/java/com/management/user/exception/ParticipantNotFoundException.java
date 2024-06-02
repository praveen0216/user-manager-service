package com.management.user.exception;

public class ParticipantNotFoundException extends RuntimeException {

    private String errorMessage;
    public ParticipantNotFoundException(String errorMessage) {
        super(errorMessage);
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
