package com.itwill.jpa.service.member_information;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

//    @Test
    void testFindPasswordAndSendEmail() {
        // 테스트 데이터 저장
        Member mockMember = Member.builder()
                .memberNo(1L)
                .memberId("qqq")
                .memberPassword("oldPassword")
                .memberEmail("do16_@naver.com") // 실제 이메일 주소 입력
                .memberName("QQQ")
                .build();

        memberRepository.save(mockMember); // 테스트 데이터 저장

        // 테스트용 DTO 생성
        MemberDto.findPassword findPasswordDto = MemberDto.findPassword.builder()
                .email("do16_@naver.com")
                .MemberId("qqq")
                .build();
        
        // 서비스 호출 (이메일 발송 포함)
        memberService.findPassword(findPasswordDto);
        
        // 결과 검증 (DB의 비밀번호가 변경되었는지 확인)
        Member updatedMember = memberRepository.findByMemberEmail("do16_@naver.com");
        assertEquals("zszz5434@gmail.com", updatedMember.getMemberEmail());
        System.out.println("새로운 비밀번호가 이메일로 발송되었습니다.");
    }
    
//    @Test
    void findMemberByMemberId() {
    	System.out.println(MemberSecurityDto.toDto(memberRepository.findMemberByMemberId("aaa")));
    }
}
