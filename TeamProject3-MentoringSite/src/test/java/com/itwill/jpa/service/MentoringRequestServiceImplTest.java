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
						.requestNo(1L)
						.requestStatus(1)
						.requestDate(null)
						.menteeNo(3L)
						.mentorNo(5L)
						.build();
		mentoringRequestService.saveMentoringRequest(mentoringRequestDto1);
		System.out.println(mentoringRequestDto1);
		MentoringRequestDto mentoringRequestDto2 = MentoringRequestDto.builder()
				.requestNo(2L)
				.requestStatus(1)
				.requestDate(null)
				.menteeNo(3L)
				.mentorNo(7L)
				.build();
		mentoringRequestService.saveMentoringRequest(mentoringRequestDto2);
		System.out.println(mentoringRequestDto2);
	}
}
