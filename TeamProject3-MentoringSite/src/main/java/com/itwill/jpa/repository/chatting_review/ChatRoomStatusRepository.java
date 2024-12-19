package com.itwill.jpa.repository.chatting_review;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.chatting_review.ChatRoomStatus;

public interface ChatRoomStatusRepository extends JpaRepository<ChatRoomStatus , Long>{
	ChatRoomStatus findByChatRoom_ChatRoomNoAndMember_MemberNo(Long chatRoomNo, Long memberNo);
}
