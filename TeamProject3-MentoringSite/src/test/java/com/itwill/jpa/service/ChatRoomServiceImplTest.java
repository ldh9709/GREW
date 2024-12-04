package com.itwill.jpa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.jpa.dto.chatting_review.ChatRoomDto;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.service.chatting_review.ChatRoomService;

@SpringBootTest
public class ChatRoomServiceImplTest {
	@Autowired
	private ChatRoomService mentoringRequestService;
	
	@Test
	void saveMentoringRequest() {
		ChatRoomDto chatRoomDto1 = ChatRoomDto.builder()
						.chatRoomNo(3L)
						.chatRoomStatus(1)
						.chatRoomStratDate(LocalDateTime.now())
						.chatRoomEndDate(null)
						.menteeNo(3L)
						.mentorNo(5L)
						.build();
		mentoringRequestService.saveMentoringRequest(chatRoomDto1);
		
		ChatRoomDto chatRoomDto2 = ChatRoomDto.builder()
				.chatRoomNo(4L)
				.chatRoomStatus(1)
				.chatRoomStratDate(LocalDateTime.now())
				.chatRoomEndDate(null)
				.menteeNo(3L)
				.mentorNo(7L)
				.build();
		mentoringRequestService.saveMentoringRequest(chatRoomDto2);
	}
	@Test
	void getMentoringRequest() {
		ChatRoomDto mentoringRequestDto = mentoringRequestService.getMentoringRequest(1L);
		System.out.println("select 1L : "+mentoringRequestDto);
	}
	@Test
	void updateActive() throws Exception{
		mentoringRequestService.updateActive(1L);
		System.out.println("멘토링 진행 중 1L : "+mentoringRequestService.getMentoringRequest(1L));
	}
	@Test
	void updateCompleted() throws Exception{
		mentoringRequestService.updateCompleted(1L);
		System.out.println("멘토링 완료 1L : "+mentoringRequestService.getMentoringRequest(1L));
	}
	@Test
	void updateRejected() throws Exception{
		mentoringRequestService.updateRejected(1L);
		System.out.println("요청 거절 1L : "+mentoringRequestService.getMentoringRequest(1L));
	}
	@Test
	void updateCanceled() throws Exception{
		mentoringRequestService.updateCanceled(1L);
		System.out.println("요청 취소 1L : "+mentoringRequestService.getMentoringRequest(1L));
	}
	@Test
	void updateForceClosed() throws Exception{
		mentoringRequestService.updateForceClosed(1L);
		System.out.println("강제 종료 1L : "+mentoringRequestService.getMentoringRequest(1L));
	}
	@Test
	void selectMentoringRequestAll(){
		List<ChatRoomDto> mentoringRequestDtos = mentoringRequestService.selectMentoringRequestAll();
		System.out.println("전체 출력"+mentoringRequestDtos);
	}
}
