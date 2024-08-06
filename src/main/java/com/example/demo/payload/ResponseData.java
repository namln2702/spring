package com.example.demo.payload;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseData {
    private int status;
    private String message;
    private Object data = null;


}
