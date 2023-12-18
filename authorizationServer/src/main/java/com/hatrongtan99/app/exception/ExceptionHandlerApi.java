package com.hatrongtan99.app.exception;

import com.hatrongtan99.dto.ResponseObject;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerApi {

    @ExceptionHandler({UsernameNotFoundException.class, NotFound.class})
    public ResponseEntity<ResponseObject<?>> handleNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject<>(false, ex.getMessage(), null));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseObject<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> error = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            error.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject<>(false, "false", error));
    }

    @ExceptionHandler({AuthenticationException.class, AuthorizationServiceException.class, ExpiredJwtException.class})
    public ResponseEntity<ResponseObject<?>> handleAuthenticationException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject<>(false, ex.getMessage(), null));
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<ResponseObject<?>> handleConflictException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseObject<>(false, ex.getMessage(), null));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseObject<?>> handleException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject<>(false, ex.getMessage(), null));
    }
}
