package com.hatrongtan99.app.exception;

import com.hatrongtan99.dto.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;

@RestControllerAdvice
public class ExceptionHandlerApi {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ResponseObject<?>> handleNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject<>(false, ex.getMessage(), null));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseObject<?>> handleMethodArgumentNotValidException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject<>(false, "fasle", null));
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ResponseObject<?>> handleAuthenticationException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject<>(false, ex.getMessage(), null));
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<ResponseObject<?>> handleConflictException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseObject<>(false, ex.getMessage(), null));
    }
}
