package com.itwill.jpa.controller.exception;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.itwill.jpa.exception.CustomException;
import com.itwill.jpa.response.Response;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response> handleCustomException(CustomException ex) {
        // 예외를 Response로 변환
        Response response = new Response();
        response.setStatus(ex.getStatusCode());
        response.setMessage(ex.getMessage());
        response.setData(null);

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // ResponseEntity 반환
        return new ResponseEntity<>(response, headers, HttpStatus.valueOf(ex.getStatusCode()));
    }
}
