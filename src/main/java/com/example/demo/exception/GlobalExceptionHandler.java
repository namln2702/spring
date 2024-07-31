package com.example.demo.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<?> handlingRuntimeException(RuntimeException runtimeException){
        return ResponseEntity.badRequest().body(runtimeException.getMessage());
    }
}
