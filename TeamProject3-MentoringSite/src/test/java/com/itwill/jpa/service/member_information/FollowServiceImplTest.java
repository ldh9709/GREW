package com.itwill.jpa.service.member_information;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

@SpringBootTest
class FollowServiceImplTest {
	
	@Autowired
	private FollowService followService;

//	@Test
	void testIsExistFollow() {
		fail("Not yet implemented");
	}

//	@Test
	void testCreateFollow() {
		fail("Not yet implemented");
	}

//	@Test
	void testDeleteFollow() {
		fail("Not yet implemented");
	}

	@Transactional
	@Test
	void testGetMentorList() {
		System.out.println(followService.getMentorList(6L, 0, 10).getTotalElements());
	}

//	@Test
	void testCountFollower() {
		fail("Not yet implemented");
	}

}
