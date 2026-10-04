package com.ajay.interview_prep_backend.exception;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException( String message) {
        super(message);
    }

}
