package com.itwill.jpa.exception;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.itwill.jpa.response.Response;
import com.itwill.jpa.util.HttpStatusMapper;


@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	// 공통적으로 Content-Type을 application/json으로 설정
    private HttpHeaders createCommonHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
        return httpHeaders;
    }
    
    // 상태 코드에 따른 HttpStatus를 반환하는 메서드
    private HttpStatus mapStatusCodeToHttpStatus(int statusCode) {
        // HttpStatusMapper를 사용하여 상태 코드에 맞는 HttpStatus 반환
        return HttpStatusMapper.getHttpStatus(statusCode);
    }

	
    // CustomException을 처리하는 핸들러
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response> handleCustomException(CustomException ex) {
    	
    	// 스택 트레이스를 로그로 기록
        logger.error("Exception occurred: ", ex);
    	
        // Response 객체 생성
        Response response = new Response();
        response.setStatus(ex.getStatusCode());  // CustomException에서 제공된 상태 코드
        response.setMessage(ex.getMessage());    // CustomException에서 제공된 메시지
        
        // 클라이언트에는 간단한 에러 메시지만 제공
        response.setData(ex.getCause().getMessage());

        // 공통 헤더 설정
        HttpHeaders httpHeaders = createCommonHeaders();
        
        // HttpStatus 동적 처리 (HttpStatusMapper 사용)
        HttpStatus httpStatus = mapStatusCodeToHttpStatus(ex.getStatusCode());
        
        // ResponseEntity로 응답 반환
        return new ResponseEntity<Response>(response, httpHeaders, httpStatus);  
    }
}
