package com.itwill.jpa.auth;

import java.util.UUID;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.itwill.jpa.auth.userinfo.GoogleUserInfo;
import com.itwill.jpa.auth.userinfo.Oauth2UserInfo;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor//final 선언된 멤버필드에 의존성 주입
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
	
	//멤버 리포지토리
	private final MemberRepository memberRepository;
	
	//패스워드 암호화
	//private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private PasswordEncoder passwordEncoder;
	
	//사용자의 기본적인 속성과 권한을 포함한 OAuth2 사용자 클래스
	DefaultOAuth2User defaultOAuth2User;
	
	@Override
	//SNS로그인 요청이 처리될때 호출되는 메소드
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		/*
		 * OAuth2 서버(예: Google, Kakao)와 통신하여 사용자 정보를 가져옴.
		 * OAuth2User 객체가 반환되며, 사용자의 속성(예: 이메일, 이름)을 포함
		 */
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		//oauth2UserInfo 객체를 null로 생성
		Oauth2UserInfo oauth2UserInfo = null;
		
		//SNS의 서버를 가져옴(Google, Naver 등)
		String provider = userRequest.getClientRegistration().getRegistrationId();
		
		//인증 제공자에 따라 적절한 Oauth2UserInfo 구현체 사용
		if(provider.equals("google")) {
			//제공자별로 다른 JSON 구조를 파싱
			oauth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
		} else if(provider.equals("naver")) {
			
			
		} else if(provider.equals("kakao")) {
			
			
		}
		
		//인증 제공자의 이메일을 가져옴
		String email = oauth2UserInfo.getEmail();
		
		//SNS 로그인 사용자는 비밀번호를 설정하지 않음으로 임의로 암호화된 값 저장
		//String password = bCryptPasswordEncoder.encode("password" + UUID.randomUUID().toString().substring(0, 6));
		String password = passwordEncoder.encode("password" + UUID.randomUUID().toString().substring(0, 6));
		//String password ="password" + UUID.randomUUID().toString().substring(0, 6);
		
		Member findMember = memberRepository.findByMemberEmail(email);
		
		if(findMember == null) {
		}
		
		return null; 
	}
}
