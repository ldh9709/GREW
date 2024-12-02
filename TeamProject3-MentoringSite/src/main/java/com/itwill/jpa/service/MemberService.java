package com.itwill.jpa.service;

import com.itwill.jpa.dto.user_information.MemberDto;
import com.itwill.jpa.entity.user_information.Member;

public interface MemberService {
	
	//회원가입
	Member saveMember(MemberDto memberDto);
	
	//로그인
	Member loginMember(String memberId, String memberPassword);
	
	//회원수정
	Member updateMember(MemberDto memberDto);
	
	//회원삭제
	Member deleteMember(Long memberNo);
	
	//회원상세
	Member getMember(Long memberNo);
	
}
