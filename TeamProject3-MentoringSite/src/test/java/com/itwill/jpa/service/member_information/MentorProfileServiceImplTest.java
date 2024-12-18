package com.itwill.jpa.service.member_information;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MentorProfileServiceImplTest {
	
	@Autowired
	private MentorProfileService mentorProfileService;
	
//	@Test
	void testUpdateMentoringCount() {
		mentorProfileService.updateMentoringCount(6L);
	}
	
//	@Test
	void testgetMentorProfilesByCategoryNo() {
		System.out.println(mentorProfileService.getMentorProfilesByCategoryNo(2L,0,10).getTotalElements());
		
	}

	@Test
	void testUpdateAcitityCount() {
		mentorProfileService.updateAcitityCount(6L);
	}

}
