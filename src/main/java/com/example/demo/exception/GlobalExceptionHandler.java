package com.example.demo.exception;


import com.example.demo.dto.response.ApiResponse;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<?> handlingRuntimeException(RuntimeException runtimeException){
        return ResponseEntity.badRequest().body(runtimeException.getMessage());
    }

    @ExceptionHandler(value = ApplicationContextException.class)
    ResponseEntity<?> handlingAppContextException(ApplicationContextException applicationContextException){
        return ResponseEntity.badRequest().body(applicationContextException.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<?> handlingAccessDeniedException(AccessDeniedException accessDeniedException){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.builder()
                        .code(1001)
                        .message(accessDeniedException.getMessage())
                .build());
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<?> handlingException(Exception exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }


}
