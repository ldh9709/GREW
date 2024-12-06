package com.itwill.jpa.response;

import java.util.HashMap;

import lombok.Data;

@Data
public class Response {

    private int status;
    private String message;
    private Object data;
    private Object addData;
    public Response() {
        this.status = 0;
        this.data =new HashMap<>();
        this.addData =new HashMap<>();
        this.message = "";
    }
    
}