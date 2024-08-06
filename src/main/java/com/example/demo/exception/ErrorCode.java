package com.example.demo.exception;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter

public enum ErrorCode {


    UNCATEGORIZED_EXCEPTION(1000, "UNCATEGORIZED ERROR",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "UNCALEGORIZED ERROR", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "USER EXISTED", HttpStatus.BAD_REQUEST),
    USENAME_INVALID(1003, "USERNAME MUST BE AT LEAST 3 CHARACTERS", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATION(1004, "CONNECT FAILD", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;

    private HttpStatus status;

    ErrorCode(int code, String message, HttpStatus httpStatus){
        this.code = code;
        this.message = message;
        this.status = httpStatus;

    }


}
