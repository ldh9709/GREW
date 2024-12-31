package com.itwill.jpa.repository.chatting_review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.jpa.entity.chatting_review.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{
	
	// 신고먹일 채팅 위해 필요
	ChatMessage findChatMessageByChatMessageNo(Long chatMessageNo);
	
	// 채팅방 번호에 해당하는 채팅 리스트 조회
	List<ChatMessage> findChatMessageByChatRoom_ChatRoomNo(Long chatRoomNo);
	
	// 유저구분위해 필요
	List<ChatMessage> findChatMessageByMember_MemberNo(Long memberNo);
	
//	@Query("SELECT cm FROM ChatMessage cm WHERE cm.chatRoom.chatRoomNo = :chatRoomNo ORDER BY cm.messageNo ASC")
//    List<ChatMessage> findByChatRoomNoOrderByMessageNoAsc(@Param("chatRoomNo") Long chatRoomNo);
	//채팅방 안읽은 메시지 갯수
	int countChatMessageByChatMessageCheckAndChatRoom_ChatRoomNo(int ChatMessageCheck, Long chatRoomNo);
}
