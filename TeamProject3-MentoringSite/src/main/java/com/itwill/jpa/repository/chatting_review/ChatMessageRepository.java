package com.itwill.jpa.repository.chatting_review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.dto.chatting_review.ChatMessageDto;
import com.itwill.jpa.entity.chatting_review.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{
	
	// 신고먹일 채팅 위해 필요
	ChatMessage findChatMessageByChatMessageNo(Long chatMessageNo);
	
	// 채팅방 번호에 해당하는 채팅 리스트 조회
	List<ChatMessage> findChatMessageByChatRoom_ChatRoomNo(Long chatRoomNo);
	
	// 유저구분위해 필요
	List<ChatMessage> findChatMessageByMember_MemberNo(Long memberNo);

	
	
	
}
