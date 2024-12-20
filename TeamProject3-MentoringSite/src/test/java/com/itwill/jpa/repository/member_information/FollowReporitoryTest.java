package com.itwill.jpa.repository.member_information;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.data.domain.Pageable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.itwill.jpa.service.member_information.FollowService;

@SpringBootTest
class FollowReporitoryTest {
	
	@Autowired
	private FollowReporitory followReporitory;
	
//	@Test
	void testExistsByMenteeMember_MemberNoAndMentorMember_MemberNo() {
		fail("Not yet implemented");
	}

	@Test
	void testFindFollowMentors() {
		Pageable pageable = PageRequest.of(0, 1);
	}

//	@Test
	void testCountBymentorMember_MemberNo() {
		fail("Not yet implemented");
	}

//	@Test
	void testFindMenteeByMentor() {
		fail("Not yet implemented");
	}

}
