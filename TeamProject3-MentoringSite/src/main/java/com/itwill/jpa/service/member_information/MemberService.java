package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.member_information.Member;

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
	
	//회원 상태 수정
	Member updateMemberStatus(MemberDto memberDto, Integer statusNo);
	
}
