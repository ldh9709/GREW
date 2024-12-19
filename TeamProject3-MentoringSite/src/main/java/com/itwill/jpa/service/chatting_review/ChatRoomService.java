package com.itwill.jpa.service.chatting_review;

import java.util.List;

import com.itwill.jpa.dto.chatting_review.ChatMessageDto;
import com.itwill.jpa.dto.chatting_review.ChatRoomDto;

public interface ChatRoomService {
	ChatRoomDto getChatRoom(Long chatRoomNo);
	void saveChatRoom(ChatRoomDto ChatRoomDto);
	ChatRoomDto updateActive(Long chatRoomNo) throws Exception;
	ChatRoomDto updateCompleted(Long chatRoomNo) throws Exception;
	ChatRoomDto updateRejected(Long chatRoomNo) throws Exception;
	ChatRoomDto updateCanceled(Long chatRoomNo) throws Exception;
	ChatRoomDto updateForceClosed(Long chatRoomNo) throws Exception;
	List<ChatRoomDto> selectChatRoomAll(Long MemberNo);
	List<ChatMessageDto> selectChatMessages(Long chatRoomNo);
}