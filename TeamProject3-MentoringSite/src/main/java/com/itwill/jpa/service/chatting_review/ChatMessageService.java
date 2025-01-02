package com.itwill.jpa.service.chatting_review;

import java.util.List;

import com.itwill.jpa.dto.chatting_review.ChatMessageDto;
import com.itwill.jpa.entity.chatting_review.ChatMessage;
import com.itwill.jpa.entity.chatting_review.ChatMessageImage;

public interface ChatMessageService {
	// 메시지 저장
	public ChatMessage createChatMessage(ChatMessageDto chatMessageDto);
	// 메시지 상태 변경(읽지않음 : 1, 읽음 : 0 디폴트를 1로 두면 서비스 하나만으로 변경가능할듯)
	public ChatMessage updateChatMessageCheck(Long chatMessageNo);
	// 특정 메시지 찾기(신고기능때문에 필요할듯)
	public ChatMessage getChatMessageByNo(Long chatMessageNo);
	// 모든 채팅 불러오기
	public List<ChatMessageDto> getChatMessageAll();

	public ChatMessage createChatMessageWithImage(ChatMessage chatMessage, ChatMessageImage chatMessageImage);
	public int countChatMessageIsRead(Long chatRoomNo,Long memberNo);
}
