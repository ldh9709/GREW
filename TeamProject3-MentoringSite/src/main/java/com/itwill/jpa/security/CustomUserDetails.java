package com.itwill.jpa.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.itwill.jpa.dto.member_information.MemberDto;

/* 사용자 정보를 표현 */
public class CustomUserDetails implements UserDetails{
	
	private final MemberDto member;
	
	public CustomUserDetails(MemberDto member) {
		this.member = member;
	}
	
	// Role을 GrantedAuthority로 변환(단일인 경우)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(member.getMemberRole()+""));
	}

	@Override
	public String getPassword() {
		return member.getMemberPassword();
	}

	@Override
	public String getUsername() {
		return member.getMemberId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false; // 계정 만료 여부
	}

	@Override
	public boolean isAccountNonLocked() {
		return false; // 계정 잠금 여부
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false; // 자격 증명 만료 여부
	}

	@Override
	public boolean isEnabled() {
		return member.getMemberStatus() == 1; // 1이면 활성화된 계정으로 간주
	}

}
