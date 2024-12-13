package com.itwill.jpa.repository.member_information;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.member_information.Member;


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
	
}
