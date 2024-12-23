package com.itwill.jpa.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.itwill.jpa.auth.PrincipalDetails;
import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.util.JWTUtil;
import com.nimbusds.jose.shaded.gson.Gson;

import jakarta.servlet.ServletException;
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
        // 인증이 성공하면, Spring Security는 Authentication 객체를 통해 로그인한 사용자 정보를 제공합니다.
    	 PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
    	 
        // 2. 사용자의 클레임 정보를 가져옵니다.
        // getClaims() 메서드를 통해 사용자의 이메일, 역할, 그리고 SNS 로그인 여부 등의 정보를 가져옵니다. -> JWT필요한 데이터 정보를 가져옴
        Map<String, Object> claims = principal.getClaims();
        
        System.out.println(">>>>> principal : " + principal);
        System.out.println(">>>>> claims : " + claims);
        // 3. JWT 토큰을 생성합니다.
        // JWTUtil.generateToken() 메서드를 사용해 액세스 토큰(accessToken)과 리프레시 토큰(refreshToken)을 생성합니다.
        String accessToken = JWTUtil.generateToken(claims, 60);  // access token은 60분 동안 유효
        String refreshToken = JWTUtil.generateToken(claims, 60 * 24);  // refresh token은 1일(60*24분) 동안 유효
        
        // 4. 클레임을 JSON 문자열로 변환합니다.
        // 생성된 클레임 정보를 JSON 형식으로 변환합니다. 이는 클라이언트에게 반환될 응답 내용입니다.
        claims.put("accessToken", accessToken);
        claims.put("refreshToken", refreshToken);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(claims);
        
        // 5. HTTP 응답 헤더와 본문 설정
        // 클라이언트가 JSON 형식으로 응답을 받을 수 있도록 응답 타입을 설정합니다.
        response.setContentType("application/json; charset=UTF-8");
        
        // 6. 응답 본문에 JSON 문자열을 출력합니다.
        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr);  // 클라이언트에게 JSON 형식의 클레임을 반환
        System.out.println("대성공");
        printWriter.close();
        
        // 인증 성공 후 클라이언트에 응답을 보낸다
        // 토큰을 응답 헤더나 쿠키에 담아 클라이언트로 전송할 수 있음
        response.setHeader("Authorization", "Bearer " + accessToken);
        
        if (response.isCommitted()) {
        	return;
        }
        // 토큰 전송 후 원하는 페이지로 리다이렉트
        response.sendRedirect("/home");
	}

}
