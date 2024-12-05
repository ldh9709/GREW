package com.itwill.jpa.service.chatting_review;

import java.util.List;

import com.itwill.jpa.dto.chatting_review.ChatRoomDto;
import com.itwill.jpa.entity.chatting_review.ChatRoom;

public interface ChatRoomService {
	ChatRoomDto getMentoringRequest(Long chatRoomNo);
	ChatRoom saveMentoringRequest(ChatRoomDto ChatRoomDto);
	ChatRoom updateActive(Long chatRoomNo) throws Exception;
	ChatRoom updateCompleted(Long chatRoomNo) throws Exception;
	ChatRoom updateRejected(Long chatRoomNo) throws Exception;
	ChatRoom updateCanceled(Long chatRoomNo) throws Exception;
	ChatRoom updateForceClosed(Long chatRoomNo) throws Exception;
	List<ChatRoomDto> selectMentoringRequestAll();
}