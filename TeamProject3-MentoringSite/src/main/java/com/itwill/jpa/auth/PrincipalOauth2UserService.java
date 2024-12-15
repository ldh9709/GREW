package com.itwill.jpa.auth;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.itwill.jpa.auth.userinfo.GoogleUserInfo;
import com.itwill.jpa.auth.userinfo.KakaoUserInfo;
import com.itwill.jpa.auth.userinfo.NaverUserInfo;
import com.itwill.jpa.auth.userinfo.Oauth2UserInfo;
import com.itwill.jpa.dto.member_information.MemberSecurityDto;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    // 멤버 리포지토리
    private final MemberRepository memberRepository;

    // 패스워드 암호화
    private final PasswordEncoder passwordEncoder;

    @Override
    // SNS 로그인 요청이 처리될 때 호출되는 메소드
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // OAuth2 서버(예: Google, Kakao)와 통신하여 사용자 정보를 가져옴.
        OAuth2User oauth2User = super.loadUser(userRequest);

        System.out.println(">>>" + oauth2User.getClass().getSimpleName());
        System.out.println(">>> 인증유저" + oauth2User);
        System.out.println("***********useRequest********");
        System.out.println(userRequest);
        System.out.println("clientRegistration : " + userRequest.getClientRegistration().getClientName());
        System.out.println("clientRegistration : " + userRequest.getClientRegistration().getClientId());
        System.out.println("accessToken : " + userRequest.getAccessToken().getTokenValue());
        System.out.println("additionalParameter : " + userRequest.getAdditionalParameters());
        System.out.println("***********END********");

        // Oauth2UserInfo 객체 초기화
        Oauth2UserInfo oauth2UserInfo = null;

        // SNS의 서버를 가져옴(Google, Naver 등)
        String provider = userRequest.getClientRegistration().getRegistrationId();

        // 인증 제공자에 따라 적절한 Oauth2UserInfo 구현체 사용
        if (provider.equals("google")) {
            // 제공자별로 다른 JSON 구조를 파싱
            oauth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
            System.out.println("oauth2UserInfo : >>>>>" + oauth2UserInfo);
        } else if (provider.equals("naver")) {
            // Naver 추가 로직
        	oauth2UserInfo = new NaverUserInfo(oauth2User.getAttributes());
        	System.out.println("oauth2UserInfo : >>>>>" + oauth2UserInfo);
        } else if (provider.equals("kakao")) {
            // Kakao 추가 로직
        	oauth2UserInfo= new KakaoUserInfo(oauth2User.getAttributes());
        }
        // 인증 제공자의 이름을 가져옴
        String name = oauth2UserInfo.getName();
        System.out.println("name : >>>" + name);
        // 인증 제공자의 이메일을 가져옴
        String email = oauth2UserInfo.getEmail();

        // SNS 로그인 사용자는 비밀번호를 설정하지 않음으로 임의로 암호화된 값 저장
        String password = passwordEncoder.encode("password" + UUID.randomUUID().toString().substring(0, 6));

        Member findMember = memberRepository.findByMemberEmail(email);

        if (findMember == null) {
            findMember = Member.toSecurityEntity(MemberSecurityDto.JoinOAuth2()
            		.memberName(name)
                    .memberEmail(email)
                    .memberPassword(password) // 암호화된 비밀번호 저장
                    .memberProvider(provider)
                    .build());
            memberRepository.save(findMember);
        }
        
        MemberSecurityDto securityMember = MemberSecurityDto.toDto(findMember);
        System.out.println("securityMember : >>>" + securityMember);
        return new PrincipalDetails(securityMember, oauth2UserInfo);
    }
}
