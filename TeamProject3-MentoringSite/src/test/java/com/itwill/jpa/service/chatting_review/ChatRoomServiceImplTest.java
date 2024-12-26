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
		chatRoomService.saveChatRoom(2L, 6L);
		
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
		chatRoomService.updateActive(19L);
		System.out.println("멘토링 진행 중 1L : "+chatRoomService.updateActive(19L));
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
	//@Test
	void selectChatRoomByMenteeNo(){
		System.out.println("유저 번호 6번 멘티 채팅방 전체 출력"+chatRoomService.selectChatRoomByMenteeNo(6L,0,10).getContent());
	}
	@Transactional
	@Test
	void selectChatRoomByMentorNo(){
		System.out.println("유저 번호 6번 멘토 채팅방 전체 출력"+chatRoomService.activeChatRoomAll(6L,0,7).getContent());
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
