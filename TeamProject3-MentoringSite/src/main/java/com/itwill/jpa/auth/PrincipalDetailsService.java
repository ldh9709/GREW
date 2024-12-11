package com.itwill.jpa.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.dto.member_information.MemberSecurityDto;
import com.itwill.jpa.repository.member_information.MemberRepository;

import lombok.RequiredArgsConstructor;

/**** 일반 로그인 시 요청한 username을 기반으로 데이터를 가져와 인증 객체 반환 *****/
@Service
@RequiredArgsConstructor//final로 선언한 것들이 주입된다(Autowired 사용X)
public class PrincipalDetailsService implements UserDetailsService {
	
	//멤버리포지토리 의존성 주입
	@Autowired
	private final MemberRepository memberRepository;
	
	@Override
	//로그인 요청 받을 시 호출
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(">>>>>username : " + username);
		//아이디로 멤버 찾기
		MemberSecurityDto findMember = MemberSecurityDto.toDto(memberRepository.findMemberByMemberId(username));
		System.out.println("findMember >>>>>" + findMember);
		//멤버가 존재하면 찾은 멤버 반환
		if(findMember != null) {
			return new PrincipalDetails(findMember);
		}
		return null;
	}

}
