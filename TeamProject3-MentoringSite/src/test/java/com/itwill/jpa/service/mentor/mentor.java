package com.itwill.jpa.service.mentor;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.jpa.dto.member_information.CareerDto;
import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.dto.member_information.MentorProfileDto;
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
		
		@Test
		void updateMentorProfile() {
			List<CareerDto> careerDtos = new ArrayList<>();
			careerDtos.add(CareerDto.builder().build());
			MentorProfileDto mentorProfileDto = MentorProfileDto.builder()
															.categoryNo(7L)
															.mentorHeadline("반가워용")
															.mentorIntroduce("안녕하세용! 이도현이에용.")
															.careerDtos(careerDtos)
															.build();
			
			
			mentorProfileService.updateMentorProfile(1L, mentorProfileDto);
		}

	
	
}
