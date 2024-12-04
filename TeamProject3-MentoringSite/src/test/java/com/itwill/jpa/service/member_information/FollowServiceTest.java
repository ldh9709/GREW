package com.itwill.jpa.service.member_information;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.jpa.repository.member_information.FollowReporitory;

@SpringBootTest
class FollowServiceTest {
	
	@Autowired
	private FollowReporitory followReporitory;
	
	@Test
	void test() {
		System.out.println(followReporitory.findByFollowerMemberWithDetails(1L));
	}

}
