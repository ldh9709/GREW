package com.itwill.jpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.jpa.dto.chatting_review.MentoringRequestDto;
import com.itwill.jpa.entity.chatting_review.MentoringRequest;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.service.member_information.MentoringRequestService;

@SpringBootTest
public class MentoringRequestServiceImplTest {
	@Autowired
	private MentoringRequestService mentoringRequestService;
	
	@Test
	void saveMentoringRequest() {
		MentoringRequestDto mentoringRequestDto1 = MentoringRequestDto.builder()
						.requestNo(3L)
						.requestStatus(1)
						.requestDate(null)
						.menteeNo(3L)
						.mentorNo(5L)
						.build();
		MentoringRequest mentoringRequest1 = mentoringRequestService.saveMentoringRequest(mentoringRequestDto1);
		System.out.println("mentoringRequest1 : ");
		MentoringRequestDto mentoringRequestDto2 = MentoringRequestDto.builder()
				.requestNo(4L)
				.requestStatus(1)
				.requestDate(null)
				.menteeNo(3L)
				.mentorNo(7L)
				.build();
		MentoringRequest mentoringRequest2 = mentoringRequestService.saveMentoringRequest(mentoringRequestDto2);
		System.out.println("mentoringRequest2 : ");
	}
	@Test
	void getMentoringRequest() {
		MentoringRequest mentoringRequest3 = mentoringRequestService.getMentoringRequest(1L);
		System.out.println("select 1L : ");
	}
}
