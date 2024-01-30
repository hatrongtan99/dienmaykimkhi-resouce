package com.hatrongtan99.app.exception;

public class RequiredSignInException extends RuntimeException{
    public RequiredSignInException(String message) {
        super(message);
    }
}
