package com.itwill.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import com.itwill.jpa.auth.FormLoginFailureHandler;
import com.itwill.jpa.auth.PrincipalDetailsService;
import com.itwill.jpa.auth.PrincipalOauth2UserSerivce;

@Configuration//이 클래스를 읽고 빈으로 등록한다
@EnableWebSecurity(debug = true)//Spring Security의 설정을 활성화
public class SecurityConfig {
	
	/*
	 * Spring Security가 제공하는 OAuth2 Provider의 기본 설정값을 제공하는 유틸리티 클래스
	 * Google, Facebook, GitHub 등 기본적으로 지원되는 OAuth2 제공자들의 ClientRegistration 정보를 간단히 생성가능
	 */
	CommonOAuth2Provider provider;
	
	/*
	 * OAuth2 인증 과정을 시작하는 필터
	 * 사용자가  /oauth2/authorization/{registrationId} URL로 접근하면 인증 요청을 생성하고 
	 * 사용자를 인증 제공자의 로그인 화면으로 리다이렉트
	 */
	OAuth2AuthorizationRequestRedirectFilter redirectFilter;
	
	/*
	 * OAuth2 인증 요청을 생성하기 위한 인터페이스
	 * ClientRegistration 정보를 기반으로, 인증 요청(OAuth2AuthorizationRequest)을 생성]
	 * 인증 요청에 추가 파라미터를 포함하거나 커스터마이징이 필요한 경우 사용
	 */
	OAuth2AuthorizationRequestResolver resolver;
	
	/*
	 * OAuth2 로그인 후 인증 코드를 교환하여 Access Token을 가져오고, 사용자 정보를 로드하는 필터.
	 * /login/oauth2/code/{registrationId} 경로로 들어오는 요청을 처리
	 * 1. 인증 서버에서 받은 Authorization Code를 사용하여 Access Token을 요청
	 * 2. Access Token을 사용해 사용자 정보를 가져오고, 이를 기반으로 OAuth2User 객체 생성
	 * 3. 사용자 정보를 기반으로 인증 객체(OAuth2AuthenticationToken)를 생성하고 인증 상태로 설정.
	 */
	OAuth2LoginAuthenticationFilter authenticationFilter;
	
	@Autowired
	//OAuth2 로그인 시(SNS) 사용자 정보를 처리하는 서비스
	private PrincipalOauth2UserSerivce PrincipalOauth2UserSerivce;
	
	@Autowired
	//폼 로그인 시 사용자 인증 정보를 처리하는 서비스
	private PrincipalDetailsService principalDetailsService;
	
	@Autowired
	//로그인 실패 시 동작을 정의하는 핸들러.
	private FormLoginFailureHandler formLoginFailureHandler;
	
	//인증 없이 접근 가능한 경로 정의
	private final String[] whitelist = { };
	
	@Bean
	//Spring Security에서 HTTP 요청에 대한 보안 설정을 구성
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
