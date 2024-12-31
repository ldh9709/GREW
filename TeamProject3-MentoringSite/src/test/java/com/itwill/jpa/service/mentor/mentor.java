package com.itwill.jpa.service.mentor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.jpa.service.chatting_review.ChatRoomService;
import com.itwill.jpa.service.member_information.MentorProfileService;

import jakarta.transaction.Transactional;
@SpringBootTest
public class mentor {
	@Autowired
	private MentorProfileService mentorProfileService;
	
		//@Transactional
		//@Test
		void updateChattingName() {
			mentorProfileService.updateMentorRating(1L);
		}

//		@Test
//		void getcareer() {
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+mentorProfileService.getMentorProfileDetail(11L));
//		System.out.println(mentorProfileService.getCareerByMentorProfileNo(11L));
//			
//		}


	
	
}
