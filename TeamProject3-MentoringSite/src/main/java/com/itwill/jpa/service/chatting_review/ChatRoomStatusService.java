package com.itwill.jpa.service.chatting_review;

import com.itwill.jpa.dto.chatting_review.ChatRoomStatusDto;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.chatting_review.ChatRoomStatus;

public interface ChatRoomStatusService {
	ChatRoomStatusDto getChatRoomStatus(Long chatRoomStatusNo);
	void saveChatRoomStatus(ChatRoomStatus mentorChatRoomStatus, ChatRoomStatus menteeChatRoomStatus);
	ChatRoomStatus updateChatRoomStatus(Long chatRoomStatusNo);
}
