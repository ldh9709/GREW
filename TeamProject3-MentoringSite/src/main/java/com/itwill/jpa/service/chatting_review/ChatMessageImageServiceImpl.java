package com.itwill.jpa.service.chatting_review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.chatting_review.ChatMessageImageDto;
import com.itwill.jpa.entity.chatting_review.ChatMessageImage;
import com.itwill.jpa.repository.chatting_review.ChatMessageImageRepository;

@Service
public class ChatMessageImageServiceImpl implements ChatMessageImageService{
	
	@Autowired
	private ChatMessageImageRepository chatMessageImageRepository;
	
	@Override
	public void saveImage(ChatMessageImageDto chatMessageImageDto) {
		ChatMessageImage chatMessageImage = ChatMessageImage.toEntity(chatMessageImageDto);
		chatMessageImageRepository.save(chatMessageImage);
	}
	
}
