package com.itwill.jpa.auth.userinfo;

import java.util.Map;
/***
 * Oauth2UserInfo 인터페이스를 구현한 클래스
 * Oauth2UserInfo는 공통 메서드(예: getEmail, getProviderId)를 정의하며, 
 * 다양한 OAuth2 제공자(Google, Naver, Kakao 등)에 대해 일관성 있는 접근 방식을 제공
 */
public class GoogleUserInfo implements Oauth2UserInfo {
	
	private Map<String, Object> attributes;
	
	/*
	 * Google에서 제공한 사용자 데이터를 매핑하여 객체를 생성
	 * attributes는 OAuth2 인증 과정에서 받은 사용자 정보 JSON을 담고있다.
	 */
	public GoogleUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	/*
	 * OAuth2 서버에서 제공한 전체 사용자 데이터를 반환
	 */
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	/*
	 * Google OAuth2 서버에서 제공하는 고유 사용자 ID(sub 필드)를 반환
	 * sub: Google에서 모든 사용자에 대해 유일한 식별자(providerId)로 제공
	 */
	@Override
	public String getProviderId() {
		return attributes.get("sub").toString();
	}
	
	/*
	 * 인증 제공자 이름을 반환
	 */
	@Override
	public String getProvider() {
		return "GooGle";
	}
	
	/*
	 * Google에서 인증된 사용자의 이메일 주소 반환
	 */
	@Override
	public String getEmail() {
		return attributes.get("email").toString();
	}
	
	/*
	 * 구글에서 인증된 사용자의 이름 반환
	 */
	@Override
	public String getName() {
		return attributes.get("name").toString();
	}

}
