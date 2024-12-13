package com.itwill.jpa.service.member_information;

import java.util.List;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.role.Role;

public interface MemberService {
	
//	//아이디유효성 체크
//	Boolean checkIdValid(String memberId);
//	
//	//비밀번호 유효성 체크
//	Boolean checkPasswordValid(String memberPassword);
//	
//	//이메일 유효성 체크
//	Boolean checkEmailValid(String memberEamil);
	
	//아이디 중복 체크
	Boolean checkIdDupl(String memberId);
	
	//이메일 중복 체크
	Boolean checkEmailDupl(String memeberEmail);
	
	//아이디 찾기
	String getMemberId(String memberId);
	
	//아이디 마스킹 처리
	String maskMemberId(String memberId);
	
	//비밀번호 변경
	void updateMemberPassword(Member member, String newPassword);
	
	//회원가입
	Member saveMember(MemberDto memberDto);
	
	//회원수정
	Member updateMember(MemberDto memberDto);
	
	//회원삭제
	Member deleteMember(Long memberNo);
	
	//회원상세
	Member getMember(Long memberNo);
	
	//회원 전체 출력
	List<MemberDto> getMemberAll(String roleStr, Integer order);
	
	//회원 상태 수정
	Member updateMemberStatus(Long memberNo, Integer statusNo);
	
	//회원 신고 카운트 증가
	Member incrementReportCount(Long MemberNo);
}
