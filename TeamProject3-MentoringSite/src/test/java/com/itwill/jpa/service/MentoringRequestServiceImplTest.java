package com.itwill.jpa.service;

import java.time.LocalDateTime;
import java.util.List;

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
						.requestStratDate(LocalDateTime.now())
						.requestEndDate(null)
						.menteeNo(3L)
						.mentorNo(5L)
						.build();
		mentoringRequestService.saveMentoringRequest(mentoringRequestDto1);
		
		MentoringRequestDto mentoringRequestDto2 = MentoringRequestDto.builder()
				.requestNo(4L)
				.requestStatus(1)
				.requestStratDate(LocalDateTime.now())
				.requestEndDate(null)
				.menteeNo(3L)
				.mentorNo(7L)
				.build();
		mentoringRequestService.saveMentoringRequest(mentoringRequestDto2);
	}
	@Test
	void getMentoringRequest() {
		MentoringRequestDto mentoringRequestDto = mentoringRequestService.getMentoringRequest(1L);
		System.out.println("select 1L : "+mentoringRequestDto);
	}
	@Test
	void updateActive(){
		
	}
	@Test
	void updateCompleted(){
		
	}
	@Test
	void updateRejected(){
		
	}
	@Test
	void updateCanceled(){
		
	}
	@Test
	void updateForceClosed(){
		
	}
	@Test
	void selectMentoringRequestAll(){
		List<MentoringRequestDto> mentoringRequestDtos = mentoringRequestService.selectMentoringRequestAll();
		System.out.println(mentoringRequestDtos);
	}
}
