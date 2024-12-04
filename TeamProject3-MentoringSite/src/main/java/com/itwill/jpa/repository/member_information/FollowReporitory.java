package com.itwill.jpa.repository.member_information;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.member_information.Follow;

@Repository
public interface FollowReporitory extends JpaRepository<Follow, Long>{
	@Query("SELECT new com.example.dto.FollowDto(f.followerMember.memberNo, f.followedMember.memberName) " +
		       "FROM Follow f WHERE f.followerMember.memberNo = :followerMemberNo")
	List<Follow> findByFollowerMemberWithDetails(@Param("followerMemberNo") Long followerMemberNo);
}
