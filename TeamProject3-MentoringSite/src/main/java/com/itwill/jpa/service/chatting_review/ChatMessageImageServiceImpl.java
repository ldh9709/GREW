package com.itwill.jpa.service.chatting_review;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.dto.chatting_review.ChatMessageDto;
import com.itwill.jpa.dto.chatting_review.ChatMessageImageDto;
import com.itwill.jpa.entity.chatting_review.ChatMessage;
import com.itwill.jpa.entity.chatting_review.ChatMessageImage;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.chatting_review.ChatMessageImageRepository;
import com.itwill.jpa.repository.chatting_review.ChatMessageRepository;
import com.itwill.jpa.repository.chatting_review.ChatRoomRepository;
import com.itwill.jpa.repository.member_information.MemberRepository;

@Service
public class ChatMessageImageServiceImpl implements ChatMessageImageService{
	
	@Autowired
	private ChatMessageImageRepository chatMessageImageRepository;
	@Autowired
    private ChatMessageRepository chatMessageRepository;
	@Autowired
	private ChatRoomRepository chatRoomRepository;
	@Autowired
	private MemberRepository memberRepository;
	
	
	@Transactional
	@Override
	public ChatMessageImageDto createImage(ChatMessageImageDto chatMessageImageDto) {
	    // 이미지 엔티티로 변환
	    ChatMessageImage chatMessageImage = new ChatMessageImage();
	    chatMessageImage.setImageName(chatMessageImageDto.getImageName());
	    
	    // 새 ChatMessage 생성
	    ChatMessage newChatMessage = new ChatMessage();
	    newChatMessage.setChatMessageContent(chatMessageImage.getImageName());  // 이미지 이름을 ChatMessage content로 설정
	    newChatMessage.setChatMessageCheck(1);  // 예시로 설정한 기본값 (필요에 따라 설정)

	    ChatRoom chatRoom = chatRoomRepository.findById(chatMessageImageDto.getChatRoomId())
	            .orElseThrow(() -> new IllegalArgumentException("ChatRoom not found"));
	    newChatMessage.setChatRoom(chatRoom);
	    
	    Member member = memberRepository.findByMemberNo(chatMessageImageDto.getMemberNo());
	    newChatMessage.setMember(member); 
	    
	    // 새로운 ChatMessage 저장
	    ChatMessage savedChatMessage = chatMessageRepository.save(newChatMessage);

	    // 새로 생성된 ChatMessage의 ID를 사용하여 이미지의 chatMessage와 연결
	    chatMessageImage.setChatMessage(savedChatMessage);  // chatMessage와 연결
	    
	    // 이미지 저장
	    ChatMessageImage savedImage = chatMessageImageRepository.save(chatMessageImage);

	    // DTO 반환
	    return ChatMessageImageDto.toDto(savedImage);  // 저장된 이미지 DTO 반환
	}


	
}
