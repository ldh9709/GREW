package com.itwill.jpa.service.chatting_review;

import java.util.List;

import com.itwill.jpa.dto.chatting_review.ChatMessageDto;

public interface ChatMessageService {
	// 메시지 저장
	void saveChatMessage(ChatMessageDto chatMessageDto);
	// 메시지 상태 변경(읽지않음 : 1, 읽음 : 0 디폴트를 1로 두면 서비스 하나만으로 변경가능할듯)
	void updateChatMessageCheck(Long chatMessageNo);
	// 특정 메시지 찾기(필요한가? 진영이깨면 물어보기)
	public ChatMessageDto selectChatMessageByNo(Long chatMessageNo);
	// 채팅방의 모든 채팅 불러오기
	public List<ChatMessageDto> selectChatMessageByChatRoomNo(Long chatRoomNo);
	// 특정 유저의 채팅내역 불러오기(필요없을거같긴한데 일단 보류)
	public List<ChatMessageDto> selectChatMessageByMemberNo(Long memberNo);
	// 모든 채팅 불러오기
	public List<ChatMessageDto> selectChatMessageAll();

}
