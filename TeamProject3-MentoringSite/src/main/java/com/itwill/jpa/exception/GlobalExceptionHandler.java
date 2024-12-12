package com.itwill.jpa.exception;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;

import com.itwill.jpa.exception.member_information.AlreadyFollowedException;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/******************** 멤버 ***********************/
    @ExceptionHandler(AlreadyFollowedException.class)
    public ResponseEntity<Response> handleExistedUserException(AlreadyFollowedException e) {
        Response response = new Response();
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setMessage(e.getMessage());
        response.setData(null);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.CONFLICT);
    }
	
    
    
	/***************** 멘토 프로필********************/
	/***************** 멘토 게시글********************/
	/***************** 질문 게시글********************/
	/***************** 답변 게시글********************/
	/******************** 알림 ***********************/
	/******************** 채팅 ***********************/
	
    // 그 외 예상치 못한 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGenericException(Exception e) {
        Response response = new Response();
        response.setStatus(ResponseStatusCode.INTERNAL_SERVER_ERROR);
        response.setMessage(ResponseMessage.INTERNAL_SERVER_ERROR);
        response.setData(e.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
