package com.hatrongtan99.app.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotFoundException extends RuntimeException{

    private String message;
    public NotFoundException(String message) {
        this.message = message;
    }


}
