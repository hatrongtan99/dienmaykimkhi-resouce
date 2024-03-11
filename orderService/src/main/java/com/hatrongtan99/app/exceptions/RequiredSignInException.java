package com.hatrongtan99.app.exceptions;

public class RequiredSignInException extends RuntimeException {
    public RequiredSignInException(String message) {
        super(message);
    }
}
