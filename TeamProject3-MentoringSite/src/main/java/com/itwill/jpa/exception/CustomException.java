package com.itwill.jpa.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
	
	private final int statusCode;  // 상태 코드
    private final String message;  // 메시지
    
    // 생성자
    public CustomException(int statusCode, String message, Throwable cause) {
    	super(message, cause);  // 부모 클래스에서 message와 cause(원인)를 처리
        this.statusCode = statusCode;
        this.message = message;  // 필요 시 중복 저장 (Optional)
    }
    
}
