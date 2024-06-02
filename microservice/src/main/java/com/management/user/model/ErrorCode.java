package com.management.user.model;

public enum ErrorCode {
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "An unexpected error occurred"),
    BID_NOT_FOUND("BID_NOT_FOUND", "No bids found for the auction"),
    PARTICIPANT_NOT_FOUND("BID_NOT_FOUND", "No bids found for the auction"),
    AUCTION_NOT_FOUND("AUCTION_NOT_FOUND", "The auction was not found"),
    INVALID_REQUEST("INVALID_REQUEST", "The request is invalid"),
    USER_NOT_FOUND("USER_NOT_FOUND", "The user was not found");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

