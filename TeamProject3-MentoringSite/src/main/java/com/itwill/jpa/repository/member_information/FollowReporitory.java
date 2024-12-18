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
	 
	/* FollowResponseDto 활용하여 원하는 데이터만 출력 */
	@Query("SELECT new com.itwill.jpa.dto.member_information.FollowResponseDto("
			+ "f.followNo "
			+ ",m.memberName "
			+ ",mp.mentorImage "
			+ ",c2.categoryName"
			+ ",c1.categoryName) "
			+ "FROM Follow f "
			+ "JOIN f.mentorMember m "
			+ "JOIN m.mentorProfile mp "
			+ "JOIN mp.category c1 "
			+ "JOIN c1.parentCategory c2 "
			+ "WHERE f.menteeMember.memberNo = :memberNo "
			+ "ORDER BY m.memberName ASC"
			)
	public Page<FollowResponseDto> findFollowMentors(@Param("memberNo") Long memberNo, Pageable pageable);
	
	/* 팔로워 멘티 수 찾기 */
	public Integer countBymentorMember_MemberNo(Long mentorMemberNo);

	
	/* 멘토보드생성알림을 위한 팔로워찾기 */
	@Query("SELECT f.menteeMember.memberNo "
			+ "FROM Follow f "
			+ "WHERE f.mentorMember.memberNo = :mentorNo")
	List<Long> findMenteeByMentor(@Param("mentorNo") Long mentorNo);
	
}
