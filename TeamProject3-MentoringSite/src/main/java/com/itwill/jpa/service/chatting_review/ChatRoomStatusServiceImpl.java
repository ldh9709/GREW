package com.itwill.jpa.service.chatting_review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.chatting_review.ChatRoomStatusDto;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.chatting_review.ChatRoomStatus;
import com.itwill.jpa.repository.chatting_review.ChatRoomRepository;
import com.itwill.jpa.repository.chatting_review.ChatRoomStatusRepository;

@Service
public class ChatRoomStatusServiceImpl implements ChatRoomStatusService{
	@Autowired
	private ChatRoomStatusRepository chatRoomStatusRepository;
	
	
	@Override
	public ChatRoomStatusDto getChatRoomStatus(Long chatRoomStatusNo) {
		ChatRoomStatus chatRoomStatus = chatRoomStatusRepository.findById(chatRoomStatusNo).get();
		ChatRoomStatusDto chatRoomStatusDto = ChatRoomStatusDto.toDto(chatRoomStatus);
		return chatRoomStatusDto;
	}
	@Override
	public void saveChatRoomStatus(ChatRoomStatus mentorChatRoomStatus, ChatRoomStatus menteeChatRoomStatus) {
			chatRoomStatusRepository.save(mentorChatRoomStatus);
			chatRoomStatusRepository.save(menteeChatRoomStatus);	
	}
	@Override
	public ChatRoomStatus updateChatRoomStatus(Long chatRoomStatusNo) {
		
		return null;
	}
}
