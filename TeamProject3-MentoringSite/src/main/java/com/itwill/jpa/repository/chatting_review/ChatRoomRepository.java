package com.itwill.jpa.repository.chatting_review;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.jpa.entity.chatting_review.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>{
	@Query("SELECT c FROM ChatRoom c WHERE c.mentee.memberNo = :memberNo OR c.mentor.memberNo = :memberNo")
	Page<ChatRoom> findByMemberNo(@Param("memberNo") Long memberNo, Pageable pageable);
}