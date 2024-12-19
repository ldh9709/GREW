package com.itwill.jpa.auth.userinfo;

import java.util.Map;

public class NaverUserInfo implements Oauth2UserInfo {
	
	//네이버에서 반환된 사용자 정보의 최상위 JSON 데이터를 저장
	private Map<String, Object> attributes;
	
	private Map<String, Object> attributesResponse;
	
	public NaverUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
		this.attributesResponse = (Map<String, Object>) attributes.get("response");
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getProviderId() {
		return attributesResponse.get("id").toString();
	}

	@Override
	public String getProvider() {
		return "Naver";
	}

	@Override
	public String getEmail() {
		return attributesResponse.get("email").toString();
	}

	@Override
	public String getName() {
		return attributesResponse.get("name").toString();
	}

}
