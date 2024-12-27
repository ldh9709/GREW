package com.itwill.jpa.service.chatting_review;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.dto.chatting_review.ChatMessageDto;
import com.itwill.jpa.entity.chatting_review.ChatMessage;
import com.itwill.jpa.entity.chatting_review.ChatMessageImage;
import com.itwill.jpa.exception.CustomException;
import com.itwill.jpa.repository.chatting_review.ChatMessageImageRepository;
import com.itwill.jpa.repository.chatting_review.ChatMessageRepository;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

	@Autowired
	private ChatMessageRepository chatMessageRepository;
	@Autowired
	private ChatMessageImageRepository chatMessageImageRepository;
	
	
	/*메시지 저장*/
	@Override
	public ChatMessage createChatMessage(ChatMessageDto chatMessageDto) {
		try {
		
			ChatMessage message = ChatMessage.toEntity(chatMessageDto);
		
		
		return chatMessageRepository.save(message);
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.SEND_CHATTING_FAIL, ResponseMessage.SEND_CHATTING_FAIL, e);
		}
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
	public ChatMessage getChatMessageByNo(Long chatMessageNo) {
		
		return chatMessageRepository.findChatMessageByChatMessageNo(chatMessageNo);
	}

	
	/*모든 채팅 조회*/
	@Override
	public List<ChatMessageDto> getChatMessageAll() {
		List<ChatMessage> chatMessages = chatMessageRepository.findAll();
		List<ChatMessageDto> chatMessageList = new ArrayList<>();
		for (ChatMessage chatMessage : chatMessages) {
			chatMessageList.add(ChatMessageDto.toDto(chatMessage));
		}
		
		return chatMessageList;
	}
	@Override
	public ChatMessage createChatMessageWithImage(ChatMessage chatMessage, ChatMessageImage chatMessageImage) {
		 ChatMessage savedChatMessage = chatMessageRepository.save(chatMessage);
		chatMessageImage.setChatMessage(savedChatMessage);
        chatMessageImageRepository.save(chatMessageImage);
        
        return savedChatMessage;
	}
	
}
