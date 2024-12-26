package com.itwill.jpa.repository.chatting_review;

import com.itwill.jpa.entity.chatting_review.ChatMessageImage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageImageRepository extends JpaRepository<ChatMessageImage ,Long>{
	
	 ChatMessageImage findByImageNo(Long imageNo);
		
	    ChatMessageImage findByChatMessageChatMessageNo(Long chatMessageNo);
	    
	
}
