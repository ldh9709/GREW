package com.itwill.jpa.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.itwill.jpa.auth.userinfo.Oauth2UserInfo;
import com.itwill.jpa.entity.member_information.Member;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class PrincipalDetails implements UserDetails {
	
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

	@Override
	public String getPassword() {
		return member.getMemberPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
