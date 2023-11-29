package com.hatrongtan99.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {
    private int statusCode;
    private boolean success;
    private String message;
}
