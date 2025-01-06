package com.itwill.jpa.repository.member_information;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.role.Role;

@SpringBootTest
class MemberRepositoryTest {
	
	@Autowired
	private MemberRepository memberRepository;

//	@Test
	void testExistByMemberId() {
		Boolean existId= memberRepository.existsByMemberId("aaa");
		assertTrue(existId);
	}

//	@Test
	void testExistByMemberEmail() {
		Boolean existEmail= memberRepository.existsByMemberEmail("aaa@naver.com");
		assertTrue(existEmail);
	}

//	@Test
	void testFindMemberIdByMemberEmail() {
	    System.out.println(memberRepository.findMemberIdByMemberEmail("bbb@naver.com").get());
	}

//	@Test
	void testFindMemberByMemberIdAndMemberPassword() {
		fail("Not yet implemented");
	}

//	@Test
	void testDeleteMemberByMemberId() {
		fail("Not yet implemented");
	}

//	@Test
	void testFindMemberByMemberId() {
		fail("Not yet implemented");
	}

//	@Test
	void testFindByMemberNo() {
	}
	
//	@Test
//	void findByMentorProfile_MentorStatusOrderByMemberNoDesc() {
//		Pageable pageable = PageRequest.of(0, 10);
//		Page<Member> memberList = memberRepository.findByMentorProfile_MentorStatusOrderByMemberNoDesc(3,pageable);
//		List<MemberDto> memberDtoList = new ArrayList<>();
//		
//		for (Member member : memberList) {
//			memberDtoList.add(MemberDto.toBasicDto(member));
//		}
//		
//		System.out.println(memberDtoList.get(0));
//		
//	}
	
//	@Test
	void testfindByMemberRoleOrderByMemberNameAsc() {
	}

}
