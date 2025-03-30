package org.example.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseAPI<?>> handleIllegalArgumentException(IllegalArgumentException exception){
        String uri = "undefined";

        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            uri = request.getRequestURI();
        }catch (NullPointerException e){
            uri = "can't resolve";
        }

        return ResponseEntity.badRequest().body(new ResponseAPI<String>(
                HttpStatus.BAD_REQUEST.value(),
                uri,
                exception.getMessage(),
                null
        ));
    }
}
