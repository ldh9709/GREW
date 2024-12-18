package com.itwill.jpa.auth.userinfo;

import java.util.Map;
/*  로그인 시 각 인증 제공자(Google, Kakao, Naver 등)로부터 받은 사용자 정보를 처리하기 위한 계약(설계)을 정의 */
public interface Oauth2UserInfo {
	//인증 제공자로부터 제공 받은 사용자 정보
	Map<String, Object> getAttributes();
	
	String getProviderId();
	
	String getProvider();

	String getEmail();

	String getName();

}	
