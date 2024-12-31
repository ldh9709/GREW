package com.itwill.jpa.repository.member_information;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.data.domain.Pageable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.itwill.jpa.service.member_information.FollowService;

import jakarta.transaction.Transactional;

@SpringBootTest
class FollowReporitoryTest {
	
	@Autowired
	private FollowReporitory followReporitory;
	
	@Test
	void testfindByMenteeMember_MemberNoAndMentorMember_MemberNo() {
		System.out.println("팔로우 번호" + followReporitory.findByMenteeMember_MemberNoAndMentorMember_MemberNo(6L, 8L));
	}

	@Transactional
	@Test
	void testFindFollowMentors() {
		Pageable pageable = PageRequest.of(0, 10);
//		System.out.println("6번 멘티팔로우 리스트 "+followReporitory.findByMenteeMemberWithDetails(6L,pageable).getTotalElements());
		
	}

//	@Test
	void testCountBymentorMember_MemberNo() {
	}

//	@Test
	void testFindMenteeByMentor() {
		fail("Not yet implemented");
	}

}
