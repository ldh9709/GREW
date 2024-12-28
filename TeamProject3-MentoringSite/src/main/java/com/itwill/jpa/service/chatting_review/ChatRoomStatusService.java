package com.itwill.jpa.service.chatting_review;

import com.itwill.jpa.dto.chatting_review.ChatRoomStatusDto;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.chatting_review.ChatRoomStatus;

public interface ChatRoomStatusService {
	ChatRoomStatusDto getChatRoomStatus(Long chatRoomStatusNo);
	ChatRoomStatusDto getChatRoomStatus(Long chatRoomNo, Long memberNo);
	void saveFirstChatRoomStatus(ChatRoomStatus mentorChatRoomStatus, ChatRoomStatus menteeChatRoomStatus);
	void ResetChatRoomStatus(ChatRoom chatRoom);
	void updateChatRoomStatus(Long chatRoomNo);
	ChatRoomStatusDto updateChatRoomStatus(Long chatRoomNo, Long memberNo);
	ChatRoomStatusDto updateChatRoomName(Long chatRoomNo, Long memberNo, String chatRoomName);
}
