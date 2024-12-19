package com.itwill.jpa.auth.userinfo;

import java.util.Map;

public class KakaoUserInfo implements Oauth2UserInfo {
	
	//카카오에서 반환된 사용자 정보의 최상위 JSON 데이터를 저장
	private Map<String, Object> attributes;
	//attributes의 kakao_account 필드에 해당하며, 계정 관련 세부 정보를 담고 있는 JSON 데이터
	private Map<String, Object> attributesAccount;
	//attributesAccount의 profile 필드에 해당하며, 사용자의 프로필 정보(예: 닉네임, 프로필 사진 등)를 담고 있는 JSON 데이터
	private Map<String, Object> attributesProfile;
	
	public KakaoUserInfo(Map<String, Object> attributes) {
		System.out.println(">>>>> kakao attributes : " + attributes);
		this.attributes = attributes;
		this.attributesAccount = (Map<String, Object>) attributes.get("kakao_account");
		this.attributesProfile = (Map<String, Object>) attributesAccount.get("profile");
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getProviderId() {
		return attributes.get("id").toString();
	}

	@Override
	public String getProvider() {
		return "Kakao";
	}

	@Override
	public String getEmail() {
		return attributesAccount.get("email").toString();
	}

	@Override
	public String getName() {
		return attributesProfile.get("nickname").toString();
	}

}
