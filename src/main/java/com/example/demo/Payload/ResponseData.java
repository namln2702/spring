package com.example.demo.Payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData {
    private int status;
    private String message;
    private Object data;

    public ResponseData() {
    }
}
