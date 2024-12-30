package com.itwill.jpa.repository.member_information;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.role.Role;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	
	//아이디 중복 여부
	public Boolean existsByMemberId(String memberId);
	
	//이메일 중복 여부
	public Boolean existsByMemberEmail(String memberEmail);
	
	//이메일로 아이디 찾기
	@Query("SELECT m.memberId FROM Member m WHERE m.memberEmail = :memberEmail")
	public Optional<String> findMemberIdByMemberEmail(@Param("memberEmail") String memberEmail);
	
	//로그인 시 아이디와 비밀번호로 객체 찾기
	public Member findMemberByMemberIdAndMemberPassword(String memberId, String memberPassword);
	
	//아이디로 멤버 삭제
	public Member deleteMemberByMemberId(String memberId);
	
	//아이디로 멤버 조회
	public Member findMemberByMemberId(String memberId);
	
	//PK로 멤버 조회
	public Member findByMemberNo(Long memberNo);
	
	//이메일로 멤버 조회
	Member findByMemberEmail(String email);
	
	//신고 카운트 증가
	@Modifying
	@Query("UPDATE Member m SET m.memberReportCount =  m.memberReportCount + 1 "
			+ "WHERE m.memberNo = :memberNo")
	public void incrementReportCount(@Param("memberNo") Long memberNo);
	
	//멤버 조회 + 멘토프로필 특정 상태값
	Page<Member> findByMentorProfile_MentorStatus(Integer status, Pageable pageable);
	
	//멤버 전체 조회 회원번호 순
	// 1. 회원전체
	// 2. 역할에 따라  
	Page<Member> findAllByOrderByMemberNoDesc(Pageable pageable);
	Page<Member> findByMemberRoleOrderByMemberNoDesc(Role memberRole,Pageable pageable); 
	
	//멤버 전체 이름 순
	//1. 회원전체
	//2. 역할에 따라 
	Page<Member> findAllByOrderByMemberNameAsc(Pageable pageable);
    Page<Member> findByMemberRoleOrderByMemberNameAsc(Role memberRole,Pageable pageable);
}
