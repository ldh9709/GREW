package com.itwill.jpa.controller.auth;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.response.Response;
import com.itwill.jpa.util.CustomJWTException;
import com.itwill.jpa.util.JWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	
	@PostMapping("/refresh")
	public ResponseEntity<Map<String, String>> refreshAccessToken(@RequestBody Map<String, String> tokenRequest) {
		String refreshToken = tokenRequest.get("refreshToken");
		
		if(refreshToken == null || refreshToken.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error","리프레시 토큰 존재"));
		}
		
		try {
            // 리프레시 토큰 검증
            Map<String, Object> claims = JWTUtil.validateToken(refreshToken);

            // 새 액세스 토큰 및 리프레시 토큰 생성
            String newAccessToken = JWTUtil.generateToken(claims, 60);  // 60분
            String newRefreshToken = JWTUtil.generateToken(claims, 60 * 24);  // 24시간

            // 새로운 토큰을 반환
            return ResponseEntity.ok(Map.of(
                "accessToken", newAccessToken,
                "refreshToken", newRefreshToken
            ));
        } catch (CustomJWTException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        }
	}
}
