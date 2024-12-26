package com.itwill.jpa.service.chatting_review;

import java.util.List;

import com.itwill.jpa.dto.chatting_review.ChatMessageImageDto;
import com.itwill.jpa.entity.chatting_review.ChatMessageImage;

public interface ChatMessageImageService {
	
	public ChatMessageImageDto createImage(ChatMessageImageDto chatMassageImageDto);
	
	public String GetBase64ImageByChatImageNo(Long imageNo);
	
	ChatMessageImageDto GetImageByChatMessageNo(Long chatMessageNo);
	
}
