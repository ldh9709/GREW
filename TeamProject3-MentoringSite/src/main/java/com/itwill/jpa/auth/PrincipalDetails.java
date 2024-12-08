package com.itwill.jpa.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.itwill.jpa.auth.userinfo.Oauth2UserInfo;
import com.itwill.jpa.entity.member_information.Member;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class PrincipalDetails implements UserDetails, OAuth2User {
	
	private Member member;//일반 사용자 정보
	private Oauth2UserInfo oauth2UserInfo;//SNS 사용자 정보
	
	
	//일반 로그인 시 사용
	public PrincipalDetails(Member member) {
		this.member = member;
	}
	//SNS 로그인 시 사용
	public PrincipalDetails(Member member, Oauth2UserInfo oauth2UserInfo) {
		this.member = member;
		this.oauth2UserInfo = oauth2UserInfo;
	}
	
	//해당 유저의 권한 목록 반환
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return member.getMemberRole().toString();
			}
		});
		return collect;
	}
	
	//비밀번호 리턴
	@Override
	public String getPassword() {
		return member.getMemberPassword();
	}
	
	//PK값 반환
	@Override
	public String getUsername() {
		return member.getMemberEmail();
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
		return true;
	}
	
	/*
	 * OAuth2 로그인 시, 인증 서버(예: Google, Kakao, Naver 등)로부터 
	 * 제공받은 사용자 정보를 반환.
	 */
	@Override
	public Map<String, Object> getAttributes() {
		return oauth2UserInfo.getAttributes();
	}
	
	/*
	 * OAuth2 사용자 고유 식별자(Provider ID)를 반환
	 * OAuth2 인증 서버에서 사용자 고유 식별자로 지정한 값(예: sub 필드)을 가져온다
	 */
	@Override
	public String getName() {
		return oauth2UserInfo.getProviderId();
	}
	
	
	
}
