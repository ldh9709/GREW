package com.itwill.jpa.service;

import com.itwill.jpa.dto.MemberDto;
import com.itwill.jpa.entity.Member;

public interface MemberService {
	
	//회원가입
	Member saveMember(MemberDto memberDto);
	
	//로그인
	Member loginMember(String memberId, String memberPassword);
	
	//회원수정
	Member updateMember(Member member);
	
	//회원삭제
	Member deleteMember(String memberId);
	
	//회원상세
	Member getMember(String memberId);
	
}
