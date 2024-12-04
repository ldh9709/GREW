package com.itwill.jpa.repository.member_information;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.dto.member_information.FollowResponseDto;
import com.itwill.jpa.entity.member_information.Follow;

@Repository
public interface FollowReporitory extends JpaRepository<Follow, Long>{
	
	@Query("SELECT new com.itwill.jpa.dto.member_information.FollowResponseDto("
			+ "m.memberName"
			+ ",c1.categoryName"
			+ ",c2.categoryName) "
			+ "FROM Follow f "
			+ "JOIN f.mentorMember m "
			+ "JOIN m.mentorProfile mp "
			+ "JOIN mp.category c1 "
			+ "JOIN c1.parentCategory c2 "
			+ "WHERE f.menteeMember.memberNo = :menteeMemberNo"
			)
	public List<FollowResponseDto> findFollowMentors(@Param("menteeMemberNo") Long menteeMemberNo); 
	
//	@EntityGraph(attributePaths = {"followedMember.mentorProfile.category"})
//	@Query("SELECT f FROM Follow f WHERE f.followerMember.memberNo = :menteeMemberNo")
//	List<Follow> findFollowedMentorsWithGraph(@Param("menteeMemberNo") Long menteeMemberNo);
	
	public Integer countBymenteeMember_MemberNo(Long mentorMemberNo);
}
