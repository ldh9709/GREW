package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.member_information.Member;

public interface MemberService {
	
	//아이디유효성 체크
	Boolean checkIdValid(String memberId);
	
	//아이디 중복 체크
	Boolean checkIdDupl(String memberId);
	
	//비밀번호 유효성 체크
	Boolean checkPasswordValid(String memberPassword);
	
	//이메일 유효성 체크
	Boolean checkEmailValid(String memberEamil);
	
	//이메일 중복 체크
	Boolean checkEmailDupl(String memeberEmail);
	
	//아이디 찾기
	String getMemberId(String memberId);
	
	//아이디 찾기
	Member updateMemberPassword(String memberPassword);
	
	//회원가입
	Member saveMember(MemberDto memberDto);
	
	//회원수정
	Member updateMember(MemberDto memberDto);
	
	//회원삭제
	Member deleteMember(Long memberNo);
	
	//회원상세
	Member getMember(Long memberNo);
	
	//회원 상태 수정
	Member updateMemberStatus(MemberDto memberDto, Integer statusNo);
	
	//회원 신고 카운트 증가
	Member incrementReportCount(Long MemberNo);
}
