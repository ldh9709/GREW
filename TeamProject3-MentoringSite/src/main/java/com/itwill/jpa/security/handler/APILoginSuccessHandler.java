package com.itwill.jpa.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.itwill.jpa.auth.PrincipalDetails;
import com.itwill.jpa.util.JWTUtil;
import com.nimbusds.jose.shaded.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class APILoginSuccessHandler implements AuthenticationSuccessHandler {

    /* 로그인 성공 후 호출되는 메서드
     * 
     * Authentication: 인증된 사용자 정보
     * 
     * */
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                         Authentication authentication) throws IOException, ServletException {
        
        // 1. 인증된 사용자의 정보를 가져옵니다.
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        
        // 2. 사용자 클레임 정보를 준비합니다.
        Map<String, Object> claims = new HashMap<>();

        claims = principal.getClaims();
        
        /***** 디버깅 START *****/
        System.out.println(">>>>> principal : " + principal);
        System.out.println(">>>>> claims : " + claims);
        /***** 디버깅 END *****/
        // 3. JWT 토큰을 생성합니다.
        // JWTUtil.generateToken() 메서드를 사용해 액세스 토큰(accessToken)과 리프레시 토큰(refreshToken)을 생성합니다.
        String accessToken = JWTUtil.generateToken(claims, 60);  // access token은 60분 동안 유효
        String refreshToken = JWTUtil.generateToken(claims, 60 * 24);  // refresh token은 1일 동안 유효

        // 4. JSON 문자열 생성 후 Base64로 인코딩
        String jsonValue = String.format("{\"accessToken\": \"%s\", \"refreshToken\": \"%s\"}", accessToken, refreshToken);
        String encodedValue = Base64.getEncoder().encodeToString(jsonValue.getBytes());
        
        // 5. 쿠키 설정
        Cookie cookie = new Cookie("member", encodedValue); // Base64로 인코딩된 값 저장
        cookie.setHttpOnly(false); // JavaScript에서 접근 가능
        cookie.setSecure(false); // HTTPS에서만 전송 (개발 환경에서는 false)
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24); // 1일

        response.addCookie(cookie);
        
        // 6. 클레임을 JSON 문자열로 변환합니다.
        claims.put("accessToken", accessToken);
        claims.put("refreshToken", refreshToken);
        
        Gson gson = new Gson();
        String jsonStr = gson.toJson(claims);
        
        //리다이렉트 설정
        // 7. 응답 설정
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr);  // 클라이언트에게 JSON 형식의 클레임을 반환
        printWriter.close();
        
        // 8. 헤더 설정 (선택적으로 Authorization 헤더 추가)
        response.setHeader("Authorization", "Bearer " + accessToken);
        if (response.isCommitted()) {
            return;
        }
    }
}
