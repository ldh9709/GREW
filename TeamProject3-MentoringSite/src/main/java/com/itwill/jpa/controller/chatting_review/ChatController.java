package com.itwill.jpa.controller.chatting_review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.itwill.jpa.dto.chatting_review.ChatMessageDto;
import com.itwill.jpa.service.chatting_review.ChatMessageService;



@Controller
public class ChatController {
	@Autowired
	private ChatMessageService chatMessageService;

	@MessageMapping("/chat/{roomId}") 	// html에서 정보를 받음
	@SendTo("/topic/messages/{roomId}")	// roomid로 정보를 다시 보냄
	public ChatMessageDto sendMessage(@DestinationVariable("roomId") String roomId, @Payload ChatMessageDto message) {
	    if (message.getMemberName() != null) {
	        // 실제로 메시지가 들어왔을 때 확인
	    	System.out.println(message);
	        System.out.println("memberName : " + message.getMemberName());
	        System.out.println("ChatContent : " + message.getChatMessageContent());
	        chatMessageService.createChatMessage(message);
	    }
	    return message;
	}
}