package com.itwill.jpa.repository.chatting_review;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.jpa.entity.chatting_review.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>{
	@Query("SELECT c FROM ChatRoom c WHERE c.mentee.memberNo = :memberNo")
	Page<ChatRoom> findByMenteeNo(@Param("memberNo") Long memberNo,Pageable pageable);
	@Query("SELECT c FROM ChatRoom c WHERE c.mentor.memberNo = :memberNo")
	Page<ChatRoom> findByMentorNo(@Param("memberNo") Long memberNo,Pageable pageable);
	@Query("SELECT c FROM ChatRoom c WHERE (c.mentee.memberNo = :memberNo OR c.mentor.memberNo = :memberNo) AND (c.chatRoomStatus = 7100 OR c.chatRoomStatus = 7200)")
	Page<ChatRoom> findByMemberNoAndStatus1(@Param("memberNo") Long memberNo, Pageable pageable);
	@Query("SELECT c FROM ChatRoom c WHERE (c.mentee.memberNo = :memberNo OR c.mentor.memberNo = :memberNo) AND c.chatRoomStatus = 7000")
	Page<ChatRoom> findByMemberNoAndStatus2(@Param("memberNo") Long memberNo, Pageable pageable);
	@Query("SELECT c FROM ChatRoom c WHERE c.mentee.memberNo = :menteeNo AND c.mentor.memberNo = :mentorNo")
	ChatRoom findByMenteeAndMentor(@Param("menteeNo") Long menteeNo, @Param("mentorNo") Long mentorNo);
}