package com.itwill.jpa.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
	
	private final int statusCode;  // 상태 코드
    private final String message;  // 메시지
    
    // 생성자
    public CustomException(int statusCode, String message) {
    	super(message);
        this.statusCode = statusCode;
        this.message = message;
    }
}
