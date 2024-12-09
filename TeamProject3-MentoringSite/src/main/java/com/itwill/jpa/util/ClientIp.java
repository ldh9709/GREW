package com.itwill.jpa.util;

import jakarta.servlet.http.HttpServletRequest;

public class ClientIp {
	public String getClientIp(HttpServletRequest request) {
	    String clientIp;
	    
	    // 헤더에서 X-Forwarded-For 값을 찾아 클라이언트의 실제 IP를 추출
	    String xForwardedFor = request.getHeader("X-Forwarded-For");
	    if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            // 첫 번째 IP를 추출 (여러 IP가 있을 수 있음)
            String[] ipArray = xForwardedFor.split(",");
            clientIp = ipArray[0].trim();  // 각 IP 앞뒤 공백 제거
        } else {
            // X-Forwarded-For가 없으면, request.getRemoteAddr()에서 IP 추출
            clientIp = request.getRemoteAddr();
        }
	    System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+clientIp);
	    return clientIp;
	}
}
