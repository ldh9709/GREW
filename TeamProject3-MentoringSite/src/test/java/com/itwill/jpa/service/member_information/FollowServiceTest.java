package com.itwill.jpa.service.member_information;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.jpa.repository.member_information.CategoryRepository;
import com.itwill.jpa.repository.member_information.FollowReporitory;

@SpringBootTest
class FollowServiceTest {
	
	@Autowired
	private FollowReporitory followReporitory;
	
//	@Test
//	void test() {
//		System.out.println(followReporitory.findFollowMentors(1L));
//	}
//	@Test
//	void test2() {
//		System.out.println(followReporitory.findFollowedMentorsWithGraph(1L));
//	}
	
	@Test
	void test4() {
		System.out.println(followReporitory.existsByMenteeMember_MemberNoAndMentorMember_MemberNo(6L, 8L));
	}
	
}
