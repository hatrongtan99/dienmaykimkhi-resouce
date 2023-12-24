package com.hatrongtan99.app.exception;

import org.springframework.security.core.Authentication;

public class RequiredSignInException extends RuntimeException {
    public RequiredSignInException(String message) {
        super(message);
    }
}
