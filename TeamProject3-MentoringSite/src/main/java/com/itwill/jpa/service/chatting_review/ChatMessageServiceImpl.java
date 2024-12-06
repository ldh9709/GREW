package com.itwill.jpa.service.chatting_review;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.dto.chatting_review.ChatMessageDto;
import com.itwill.jpa.entity.chatting_review.ChatMessage;
import com.itwill.jpa.repository.chatting_review.ChatMessageRepository;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

	@Autowired
	private ChatMessageRepository chatMessageRepository;
	
	/*메시지 저장*/
	@Override
	public ChatMessage saveChatMessage(ChatMessageDto chatMessageDto) {
		ChatMessage message = ChatMessage.toEntity(chatMessageDto);
		
		
		return chatMessageRepository.save(message);
	}
	
	/*읽음상태 업데이트*/
	@Transactional
	@Override
	public ChatMessage updateChatMessageCheck(Long chatMessageNo) {
		ChatMessage chatMessage = chatMessageRepository.findChatMessageByChatMessageNo(chatMessageNo);
		
		chatMessage.setChatMessageCheck(0);
		return chatMessageRepository.save(chatMessage);
	}

	/*특정 메시지 조회(신고위해필요)*/
	@Override
	public ChatMessage selectChatMessageByNo(Long chatMessageNo) {
		
		return chatMessageRepository.findChatMessageByChatMessageNo(chatMessageNo);
	}

	/*채팅방의 메시지 리스트 조회*/
	@Override
	public List<ChatMessageDto> selectChatMessageByChatRoomNo(Long chatRoomNo) {
		List<ChatMessage> chatMessages = chatMessageRepository.findChatMessageByChatRoom_ChatRoomNo(chatRoomNo);
		List<ChatMessageDto> chatMessageList = new ArrayList<>();
		for (ChatMessage chatMessage : chatMessages) {
			chatMessageList.add(ChatMessageDto.toDto(chatMessage));
		}
		
		return chatMessageList;
	}

	/*특정 멤버 번호에 따른 채팅 리스트 조회*/
	@Override
	public List<ChatMessageDto> selectChatMessageByMemberNo(Long memberNo) {
		List<ChatMessage> chatMessages = chatMessageRepository.findChatMessageByMember_MemberNo(memberNo);
		List<ChatMessageDto> chatMessageList = new ArrayList<>();
		for (ChatMessage chatMessage : chatMessages) {
			chatMessageList.add(ChatMessageDto.toDto(chatMessage));
		}
		
		return chatMessageList;
		
	}

	/*모든 채팅 조회*/
	@Override
	public List<ChatMessageDto> selectChatMessageAll() {
		List<ChatMessage> chatMessages = chatMessageRepository.findAll();
		List<ChatMessageDto> chatMessageList = new ArrayList<>();
		for (ChatMessage chatMessage : chatMessages) {
			chatMessageList.add(ChatMessageDto.toDto(chatMessage));
		}
		
		return chatMessageList;
	}
	
}
