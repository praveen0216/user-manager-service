package com.management.user.exception;

import com.management.user.model.ErrorCode;
import com.management.user.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InputException.class)
    public ResponseEntity<ErrorResponse> handleInputException(InputException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_REQUEST, ex.getErrorMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParticipantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleParticipantNotFoundException(ParticipantNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.PARTICIPANT_NOT_FOUND, ex.getErrorMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AuctionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAuctionNotFoundException(AuctionNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.AUCTION_NOT_FOUND, ex.getErrorMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
