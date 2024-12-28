package com.itwill.jpa.repository.member_information;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.dto.member_information.FollowResponseDto;
import com.itwill.jpa.entity.member_information.Follow;

@Repository
public interface FollowReporitory extends JpaRepository<Follow, Long>{
	
	/* 멘티, 멘토 번호가 존재하는지 확인 */ 
	boolean existsByMenteeMember_MemberNoAndMentorMember_MemberNo(Long menteeMemberNo, Long mentorMemberNo);

	/* 팔로워 리스트 */
	Page<Follow> findByMentorMember_MemberNo(Long memberNo,Pageable pageable);
	
	/* 팔로워 멘티 수 찾기 */
	public Integer countBymentorMember_MemberNo(Long mentorMemberNo);
	
	/* 멘토보드생성알림을 위한 팔로워찾기 */
	@Query("SELECT f.menteeMember.memberNo "
			+ "FROM Follow f "
			+ "WHERE f.mentorMember.memberNo = :mentorNo")
	List<Long> findMenteeByMentor(@Param("mentorNo") Long mentorNo);
	
	/* 멘티, 멘토 팔로우 일치하는 팔로우 값 찾기 */
	public Follow findByMenteeMember_MemberNoAndMentorMember_MemberNo(Long menteeNo, Long MentorNo);
}
