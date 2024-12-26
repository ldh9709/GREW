package com.itwill.jpa.service.chatting_review;

import java.util.List;

import org.springframework.data.domain.Page;

import com.itwill.jpa.dto.chatting_review.ChatMessageDto;
import com.itwill.jpa.dto.chatting_review.ChatRoomDto;

public interface ChatRoomService {
	ChatRoomDto getChatRoom(Long chatRoomNo);
	ChatRoomDto saveChatRoom(Long mentorNo, Long menteeNo);
	ChatRoomDto updateActive(Long chatRoomNo) throws Exception;
	ChatRoomDto updateCompleted(Long chatRoomNo) throws Exception;
	ChatRoomDto updateRejected(Long chatRoomNo) throws Exception;
	ChatRoomDto updateCanceled(Long chatRoomNo) throws Exception;
	ChatRoomDto updateForceClosed(Long chatRoomNo) throws Exception;
	Page<ChatRoomDto> selectChatRoomByMenteeNo(Long MemberNo, int pageNumber, int pageSize);
	Page<ChatRoomDto> selectChatRoomByMentorNo(Long MemberNo, int pageNumber, int pageSize);
	Page<ChatRoomDto> activeChatRoomAll(Long MemberNo, int pageNumber, int pageSize);
	Page<ChatRoomDto> waitChatRoomAll(Long MemberNo, int pageNumber, int pageSize);
	List<ChatMessageDto> selectChatMessages(Long chatRoomNo);
}