package com.itwill.jpa.service.chatting_review;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.jpa.dto.chatting_review.ChatRoomDto;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.service.chatting_review.ChatRoomService;
import com.itwill.jpa.service.member_information.MentorProfileService;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ChatRoomServiceImplTest {
	@Autowired
	private ChatRoomService chatRoomService;
	@Autowired
	private ChatRoomStatusService chatRoomStatusService;
	@Autowired
	private MentorProfileService mentorProfileService;
	
	//@Test
	void saveMentoringRequest() {
		ChatRoomDto chatRoomDto1 = ChatRoomDto.builder()
						.chatRoomNo(7L)
						.chatRoomStatus(1)
						.chatRoomStartDate(LocalDateTime.now())
						.chatRoomEndDate(null)
						.menteeNo(3L)
						.mentorNo(5L)
						.build();
		chatRoomService.saveChatRoom(chatRoomDto1);
		
		ChatRoomDto chatRoomDto2 = ChatRoomDto.builder()
				.chatRoomNo(8L)
				.chatRoomStatus(1)
				.chatRoomStartDate(LocalDateTime.now())
				.chatRoomEndDate(null)
				.menteeNo(3L)
				.mentorNo(7L)
				.build();
		chatRoomService.saveChatRoom(chatRoomDto2);
	}
	//@Test
	void getMentoringRequest() {
		System.out.println("select 1L : "+chatRoomService.getChatRoom(1L));
	}
	//@Transactional
	//@Test
	void updateChattingName() {
		chatRoomStatusService.updateChatRoomName(1L, 5L, "코딩 수업 채팅방");
	}

	//@Transactional
	//@Test
	void updateActive() throws Exception{
		chatRoomService.updateActive(1L);
		System.out.println("멘토링 진행 중 1L : "+chatRoomService.updateActive(1L));
	}
//	@Test
	void updateCompleted() throws Exception{
		System.out.println("멘토링 완료 1L : "+chatRoomService.updateCompleted(1L));
	}
	//@Test
	void updateRejected() throws Exception{
		System.out.println("요청 거절 1L : "+chatRoomService.updateRejected(1L));
	}
	//@Test
	void updateCanceled() throws Exception{
		System.out.println("요청 취소 1L : "+chatRoomService.updateCanceled(1L));
	}
	//@Test
	void updateForceClosed() throws Exception{
		System.out.println("강제 종료 1L : "+chatRoomService.updateForceClosed(1L));
	}
	@Test
	void selectMentoringRequestAll(){
		System.out.println("유저 번호 1번 채팅방 전체 출력"+chatRoomService.selectChatRoomAll(1L));
	}
	//@Test
	void updateChattingStatus() {
		chatRoomStatusService.updateChatRoomStatus(1L, 5L);
	}
	//@Transactional
	//@Test
	void selectChatMessage() {
		System.out.println("1번방 채팅내역"+chatRoomService.selectChatMessages(1L));
	}
}
