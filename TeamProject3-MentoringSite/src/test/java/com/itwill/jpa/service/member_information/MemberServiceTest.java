package com.itwill.jpa.service.member_information;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.jpa.dto.member_information.InterestDto;
import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.dto.member_information.MemberSecurityDto;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.MemberRepository;
import com.itwill.jpa.service.member_information.MemberServiceImpl;

@SpringBootTest // SpringBootTest로 실제 스프링 컨텍스트를 로드
class MemberServiceTest {

    @Autowired
    private MemberServiceImpl memberService; // 실제 서비스 객체 주입

    @Autowired
    private MemberRepository memberRepository; // 테스트 데이터 저장용

    @Test
    //비밀번호 찾기 코드 전송
    void testFindPasswordAndSendEmail() {
        // 테스트 데이터 저장
        Member mockMember = Member.builder()
                .memberNo(1L)
                .memberId("qqq")
                .memberPassword("oldPassword")
                .memberEmail("zszz5434@gmail.com") // 실제 이메일 주소 입력
                .memberName("QQQ")
                .build();

        memberRepository.save(mockMember); // 테스트 데이터 저장

        // 테스트용 DTO 생성
        MemberDto.findPassword findPasswordDto = MemberDto.findPassword.builder()
                .email("zszz5434@gmail.com")
                .build();
        
        // 서비스 호출 (이메일 발송 포함)
        memberService.findPassword(findPasswordDto);
        
        // 결과 검증 (DB의 비밀번호가 변경되었는지 확인)
        Member updatedMember = memberRepository.findByMemberEmail("zszz5434@gmail.com");
        assertEquals("zszz5434@gmail.com", updatedMember.getMemberEmail());
        System.out.println("새로운 비밀번호가 이메일로 발송되었습니다.");
    }
    
//    @Test
    void findMemberByMemberId() {
    	System.out.println(MemberSecurityDto.toDto(memberRepository.findMemberByMemberId("aaa")));
    }
    //@Test
    void testSendJoinCode() {
    	
    	 MemberDto.JoinFormDto joinFormDto = MemberDto.JoinFormDto.builder()
                 .email("zszz5434@gmail.com") // 실제 이메일 주소
                 .build();
    	 
    	 memberService.sendJoinCode(joinFormDto);
    	 System.out.println("메일보낸 이메일 : " + joinFormDto);
    	 
    	 Integer sendCode = memberService.getTempCode(joinFormDto.getEmail());
    	 
    	 System.out.println("저장된 인증번호: " + sendCode);
    	 System.out.println("입력한 이메일 : " + joinFormDto.getEmail());
    	 
    	 
    	 boolean isChecked = memberService.certificationCode(joinFormDto.getEmail(), sendCode);
    	 assertEquals(true, isChecked, "인증번호가 일치하지 않습니다!");
    	 
    	// 관심사 리스트 생성
	    List<InterestDto> interestList = new ArrayList<>();
	    interestList.add(InterestDto.builder().categoryNo(2L).build()); // 카테고리 번호 2
	    interestList.add(InterestDto.builder().categoryNo(3L).build()); // 카테고리 번호 3
    	    
	    MemberDto memberDto = MemberDto.builder()
	            .memberId("testuser")
	            .memberPassword("password123!")
	            .memberEmail("do16_@naver.com")
	            .memberName("Test User")
	            .interests(interestList) // 관심사 추가
                .build();
    	 
    	 Member savedMember = memberService.saveMember(memberDto);
    	
    	 // 결과 검증
         assertEquals("testuser", savedMember.getMemberId());
         assertEquals("do16_@naver.com", savedMember.getMemberEmail());
         System.out.println("회원가입 성공: " + savedMember);
    	 
    }
    
}
