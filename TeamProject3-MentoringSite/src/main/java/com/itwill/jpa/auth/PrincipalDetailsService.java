package com.itwill.jpa.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.MemberRepository;

import lombok.RequiredArgsConstructor;

/**** 일반 로그인 시 *****/
@Service
@RequiredArgsConstructor//final로 선언한 것들이 주입된다(Autowired 사용X)
public class PrincipalDetailsService implements UserDetailsService {
	
	//멤버리포지토리 의존성 주입
	private final MemberRepository memberRepository;
	
	@Override
	//로그인 요청 받을 시 호출
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//이메일로 멤버 찾기
		Member findMember = memberRepository.findByEmail(username).orElse(null);
		
		//멤버가 존재하면 찾은 멤버 반환
		if(findMember != null) {
			return new PrincipalDetails(findMember);
		}
		
		return null;
	}

}
