package com.hatrongtan99.app.Exception;

public class RequiredSignInException extends RuntimeException {
    public RequiredSignInException(String message) {
        super(message);
    }
}
