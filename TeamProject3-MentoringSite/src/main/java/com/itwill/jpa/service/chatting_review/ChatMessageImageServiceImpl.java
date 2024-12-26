package com.itwill.jpa.service.chatting_review;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.dto.chatting_review.ChatMessageImageDto;
import com.itwill.jpa.dto.chatting_review.ChatRoomDto;
import com.itwill.jpa.entity.chatting_review.ChatMessage;
import com.itwill.jpa.entity.chatting_review.ChatMessageImage;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.repository.chatting_review.ChatMessageImageRepository;
import com.itwill.jpa.repository.chatting_review.ChatMessageRepository;

@Service
public class ChatMessageImageServiceImpl implements ChatMessageImageService {

    @Autowired
    private ChatMessageImageRepository chatMessageImageRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    
    @Autowired
    private ChatRoomService chatRoomService;

    @Transactional
    @Override
    public ChatMessageImageDto createImage(ChatMessageImageDto chatMessageImageDto) {
        System.out.println("Received ChatMessageImageDto: " + chatMessageImageDto);
        // 이미지 이름과 Base64 데이터를 받아온다.
        System.out.println("Received Base64 Image Data: " + chatMessageImageDto.getImageName());

        String base64Image = chatMessageImageDto.getBase64Image();
        
        System.out.println("Base64 Image Data: " + base64Image);

        // Base64로부터 이미지 데이터를 디코딩 (이미지가 null 또는 빈 문자열인 경우 처리)
        if (base64Image == null || base64Image.isEmpty()) {
            throw new IllegalArgumentException("64이미지 데이터가 비어있습니다.");
        }

        // "data:image/png;base64," 부분 제거 (이미지 포맷에 따라 변경 가능)
        if (base64Image.contains("data:image/png;base64,")) {
            base64Image = base64Image.split(",")[1];
        }

        // 이미지 엔티티 생성
        ChatMessageImage chatMessageImage = new ChatMessageImage();
        chatMessageImage.setImageName("이미지");  // 이미지 이름 설정
        chatMessageImage.setBase64Image(base64Image);

        // ChatMessage 객체를 새로 생성하거나 기존 ChatMessage를 가져옴
        // 예시로 chatMessageDto.getChatMessageNo()로 기존 ChatMessage 객체를 찾는 방법
        ChatMessage existingChatMessage = chatMessageRepository.findById(chatMessageImageDto.getChatMessageNo())
            .orElseThrow(() -> new IllegalArgumentException("ChatMessage not found"));

        chatMessageImage.setChatMessage(existingChatMessage); // 기존 ChatMessage 연결
        
        // ChatRoom 가져오기
        ChatRoom chatRoom = existingChatMessage.getChatRoom(); // 이제 chatRoom을 null이 아닌 값으로 설정
        if (chatRoom == null) {
            throw new IllegalStateException("ChatRoom is null.");
        }

        // 새 ChatMessage 생성 (이미지 메시지로 연결)
        ChatMessage newChatMessage = new ChatMessage();
        newChatMessage.setChatMessageContent("이미지");  // 이미지 이름을 메시지 내용으로 설정
        newChatMessage.setChatMessageCheck(1);  // 예시로 기본값 설정 (필요에 따라 수정)
        newChatMessage.setChatRoom(chatRoom);
        newChatMessage.setChatMessageDate(existingChatMessage.getChatMessageDate());
        newChatMessage.setChatMessageNo(existingChatMessage.getChatMessageNo());
        newChatMessage.setMember(existingChatMessage.getMember());

        // 새로운 ChatMessage 저장
        ChatMessage savedChatMessage = chatMessageRepository.save(newChatMessage);

        // ChatMessage와 이미지 연결
        chatMessageImage.setChatMessage(savedChatMessage);

        // 이미지 저장
        ChatMessageImage savedImage = chatMessageImageRepository.save(chatMessageImage);

        // DTO로 변환하여 반환
        return ChatMessageImageDto.toDto(savedImage);  // 저장된 이미지 DTO 반환
    }

    @Override
    public String GetBase64ImageByChatImageNo(Long imageNo) {
    		ChatMessageImage chatMessageImage = chatMessageImageRepository.findByImageNo(imageNo);
    		
    	return chatMessageImage.getImageName();
    }
    
    @Override
    public ChatMessageImageDto GetImageByChatMessageNo(Long chatMessageNo){
    	ChatMessageImage images = chatMessageImageRepository.findByChatMessageChatMessageNo(chatMessageNo);
    	ChatMessageImageDto imagesDto = ChatMessageImageDto.toDto(images);
    	
    	
    	return imagesDto;
    	
    	}
    	
}
