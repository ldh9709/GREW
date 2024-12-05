package com.itwill.jpa.repository.chatting_review;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.chatting_review.ChatRoom;

public interface MentoringRequestRepository extends JpaRepository<ChatRoom, Long>{
	
}