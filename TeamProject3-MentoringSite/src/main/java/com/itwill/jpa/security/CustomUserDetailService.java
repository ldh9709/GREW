package com.itwill.jpa.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.repository.member_information.MemberRepository;

/* 사용자 정보를 로드, 반환된 userDetails는 인증 매커니즘에 사용 */
public class CustomUserDetailService implements UserDetailsService{

	private final MemberRepository memberRepository;
	
	public CustomUserDetailService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberDto member = MemberDto.toDto(memberRepository.findMemberByMemberId(username));
		
		return new CustomUserDetails(member);
	}

}
