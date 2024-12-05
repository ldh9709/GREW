package com.itwill.jpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.jpa.dto.chatting_review.ChatRoomStatusDto;
import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.chatting_review.ChatRoomStatus;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.service.chatting_review.ChatRoomStatusService;

@SpringBootTest
public class ChatRoomStatusServiceImplTest {
	@Autowired
	private ChatRoomStatusService chatRoomStatusService;
	
	@Test
	void svaeChatRoomStatus() {
		chatRoomStatusService.saveChatRoomStatus(null, null);
	}
}
