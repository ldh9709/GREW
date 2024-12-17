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
	
	//이메일로 회원 찾기
	Member getMemberByMemberEmail(String memberEmail);
		
	//회원 상태 수정
	Member updateMemberStatus(MemberDto memberDto, Integer statusNo);
	
	//회원가입 시 인증번호 메일 발송
	Integer sendJoinCode(MemberDto.JoinFormDto joinFormDto);
	
	//이메일에 해당하는 인증번호 가져오기
	Integer getTempCode(String memberEmail);
	
	//인증번호가 일치하는지 확인
	boolean certificationCode(String memberEmail, Integer inputCode);

	//아이디 찾기 시 메일 발송
	void findId(MemberDto.findId memberDto);
	
	//아이디 찾기 시 인증번호가 일치하는지 확인
	boolean certificationCodeByFindId(String memberEmail, Integer inputCode);
	
	//비밀번호 찾기 시 임시 비밀번호 메일 발송
	void findPassword(MemberDto.findPassword memberDto);

}
