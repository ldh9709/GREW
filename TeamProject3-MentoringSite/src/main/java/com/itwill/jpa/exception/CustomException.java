package com.itwill.jpa.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
	
	private final int statusCode;  // 상태 코드
    private final String message;  // 메시지
    private final Throwable cause;  // 원인 예외 추가
    
    // 생성자
    public CustomException(int statusCode, String message, Throwable cause) {
    	super(message);
        this.statusCode = statusCode;
        this.message = message;
        this.cause = cause;  // 원인 예외 저장
    }
    
}
