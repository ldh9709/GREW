package com.itwill.jpa.exception;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseStatusCode;

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
            // Answer 관련 상태 코드
            case ResponseStatusCode.CREATED_ANSWER_FAIL:
            case ResponseStatusCode.UPDATE_ANSWER_FAIL:
            case ResponseStatusCode.DELETE_ANSWER_FAIL:
            case ResponseStatusCode.ACCEPT_ANSWER_FAIL:
            case ResponseStatusCode.READ_ANSWER_LIST_FAIL:
                return HttpStatus.BAD_REQUEST; // 실패 시 BAD_REQUEST(400)

            // Inquiry 관련 상태 코드

            case ResponseStatusCode.CREATED_INQUIRY_FAIL:
            case ResponseStatusCode.UPDATE_INQUIRY_FAIL:
            case ResponseStatusCode.DELETE_INQUIRY_FAIL:
            case ResponseStatusCode.READ_INQUIRY_LIST_FAIL:
                return HttpStatus.BAD_REQUEST; // 실패 시 BAD_REQUEST(400)

            // 기본적으로 처리되지 않은 경우
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR; // 서버 에러 시 INTERNAL_SERVER_ERROR(500)
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