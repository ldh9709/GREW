package com.itwill.jpa.exception;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.itwill.jpa.response.Response;

@ControllerAdvice
public class GlobalExceptionHandler {

	// 공통적으로 Content-Type을 application/json으로 설정
    private HttpHeaders createCommonHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
        return httpHeaders;
    }
    
    // 상태 코드에 따른 HttpStatus를 반환하는 메서드
    private HttpStatus mapStatusCodeToHttpStatus(int statusCode) {
        switch (statusCode) {
            case 6001: // CREATED_ANSWER_FAIL
            case 6201: // UPDATE_ANSWER_FAIL
            case 6301: // DELETE_ANSWER_FAIL
            case 6401: // ACCEPT_ANSWER_FAIL
            case 6600: // READ_ANSWER_LIST_FAIL
                return HttpStatus.BAD_REQUEST; // 일반적으로 실패는 BAD_REQUEST로 설정

            case 6000: // CREATED_ANSWER_SUCCESS
            case 6200: // UPDATE_ANSWER_SUCCESS
            case 6300: // DELETE_ANSWER_SUCCESS
            case 6400: // ACCEPT_ANSWER_SUCCESS
            case 6500: // READ_ANSWER_LIST_SUCCESS
            case 6700: // READ_ANSWER_SUCCESS
                return HttpStatus.OK; // 성공은 OK 상태로 처리

            default:
                return HttpStatus.INTERNAL_SERVER_ERROR; // 기본적으로 INTERNAL_SERVER_ERROR로 처리
        }
    }
	
    // CustomException을 처리하는 핸들러
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response> handleCustomException(CustomException ex) {
        // Response 객체 생성
        Response response = new Response();
        response.setStatus(ex.getStatusCode());  // CustomException에서 제공된 상태 코드
        response.setMessage(ex.getMessage());    // CustomException에서 제공된 메시지
        response.setData(null);                  // 데이터는 필요 없으므로 null
        
        // 공통 헤더 설정
        HttpHeaders httpHeaders = createCommonHeaders();
        
        // HttpStatus 동적 처리
        HttpStatus httpStatus = mapStatusCodeToHttpStatus(ex.getStatusCode());
        
        // ResponseEntity로 응답 반환
        return new ResponseEntity<Response>(response, httpHeaders, httpStatus);  
    }
}