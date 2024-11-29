package com.itwil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.TeamProject3MentoringSiteApplication;
import com.itwill.jpa.dto.MemberDto;
import com.itwill.jpa.entity.Member;
import com.itwill.jpa.service.MemberServiceImpl;

@SpringBootTest(classes = TeamProject3MentoringSiteApplication.class)
class TeamProject3MentoringSiteApplicationTests {
	@Autowired
	MemberServiceImpl memberServiceImpl;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void saveMember() {
		MemberDto memberDto = MemberDto.builder()
				
				.memberId("mem1")
				.memberPassword("111")
				.memberName("mem")
				.memberEmail("m@m")
				.build();
		memberServiceImpl.saveMember(memberDto);
	}
	
}
