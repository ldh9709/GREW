package com.itwill.jpa.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.itwill.jpa.auth.userinfo.Oauth2UserInfo;
import com.itwill.jpa.dto.member_information.MemberSecurityDto;
import com.itwill.jpa.entity.member_information.Member;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
/*
 * Spring Security가 인증 및 권한 부여를 처리할 때 필요한 사용자 정보를 제공하는 클래스
 * 일반 로그인과 OAuth2 로그인 모두에서 사용할 수 있도록 설계
 */
public class PrincipalDetails implements UserDetails, OAuth2User {
	
	private final MemberSecurityDto member;//일반 사용자 정보
	private Oauth2UserInfo oauth2UserInfo;//SNS 사용자 정보
	
	
	//일반 로그인 시 사용
	public PrincipalDetails(MemberSecurityDto member) {
		this.member = member;
		System.out.println("<<<<<PrincipalDetailsMember : " + this.member);
	}
	//SNS 로그인 시 사용
	public PrincipalDetails(MemberSecurityDto member, Oauth2UserInfo oauth2UserInfo) {
		this.member = member;
		System.out.println("<<<<<PrincipalDetailsMember : " + this.member);
		this.oauth2UserInfo = oauth2UserInfo;
		System.out.println("<<<<<PrincipalDetailsOauth2UserInfo : " + this.oauth2UserInfo);
	}
	
	//해당 유저의 권한 목록 반환
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(member.getMemberRole().name()));
	}
	
	//비밀번호 리턴
	@Override
	public String getPassword() {
		return member.getMemberPassword();
	}
	
	@Override
	public String getUsername() {
		return member.getMemberId();
	}
	
	
	public Long getmemberNo() {
		return member.getMemberNo();
	}
	
	@Override
	public String getName() {
		return member.getMemberName();
	}
	
	/*
	 * 계정 만료 여부
	 * true : 만료 안됨
	 * false : 만료됨
	 */
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	/*
	 * 계정 잠김 여부
	 * true : 잠기지 않음
	 * false : 잠김
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	/*
	 * 비밀번호 만료 여부
	 * true : 만료 안됨
	 * false : 만료됨
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	/*
	 * 계정 활성화 여부
	 * true : 활성화됨
	 * false : 활성화 안됨
	 */
	@Override
	public boolean isEnabled() {
		return member.getMemberStatus() == 1; // 1이면 활성화된 계정으로 간주
	}

	
	/*
	 * OAuth2 로그인 시, 인증 서버(예: Google, Kakao, Naver 등)로부터 
	 * 제공받은 사용자 정보를 반환.
	 */
	@Override
	public Map<String, Object> getAttributes() {
		return oauth2UserInfo.getAttributes();
	}
	
	/* JWT 토큰을 생성하는데 필요한 사용자 정보 */
	public Map<String, Object> getClaims(){
		Map<String, Object> dataMap = new HashMap<>();
		
		dataMap.put("id", member.getMemberId());
		dataMap.put("email", member.getMemberEmail());
		dataMap.put("password", member.getMemberPassword());
		dataMap.put("name", member.getMemberName());
		dataMap.put("role", member.getMemberRole());
		dataMap.put("status", member.getMemberStatus());
		dataMap.put("provider", member.getMemberProvider());
		
		return dataMap;
		
	}
	
	
}
