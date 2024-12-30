package com.itwill.jpa.service.member_information;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.role.Role;

public interface MemberService {
	
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
	
	//이메일로 회원 찾기
	Member getMemberByMemberEmail(String memberEmail);
		
	//회원 상태 수정
	Member updateMemberStatus(Long memberNo, Integer statusNo);
	
	//회원 권한 수정
	Member updateMemberRole(Long memberNo, String role);
	
	//멘토 상태별 회원 조회
	public Page<MemberDto> getMemberAllByMentorStatus(Integer status, int pageNumber, int pageSize);
	
	//회원 전체 출력
	Page<MemberDto> getMemberAll(String roleStr, Integer order, int pageNumber, int pageSize);
	
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

	//회원 신고 카운트 증가
	Member incrementReportCount(Long MemberNo);
	
	//토큰 재생성
	Map<String, String> regenerateTokens(Authentication authentication, Member updateMember);
}
