package com.hatrongtan99.paymentservice.exceptions;

public class RequiredSignInException extends RuntimeException{
    public RequiredSignInException(String message){
        super(message);
    }
}
