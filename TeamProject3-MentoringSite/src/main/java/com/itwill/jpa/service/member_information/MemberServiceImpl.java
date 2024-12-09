package com.itwill.jpa.service.member_information;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.member_information.InterestDto;
import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Interest;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.MemberRepository;
import com.itwill.jpa.util.CustomMailSender;

@Service
public class MemberServiceImpl implements MemberService {
	
	
	//메소드 사용을 위한 리포지토리 의존성 주입
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	//메일 발송을 위한 메소드 의존성 주입
	CustomMailSender customMailSender;
	
	//@Autowired
	//비밀번호 암호화를 위한 메소드 의존성 주입
	//BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	/***** 회원 가입 *****/
	@Override
	public Member saveMember(MemberDto memberDto) {
		
		//MemberDto Entity로 변경
		Member saveMember = Member.toEntity(memberDto);
		
		for (InterestDto interest : memberDto.getInterests()) {
			
			Interest interestEntity = Interest.toEntity(interest);
			
			saveMember.addInterests(interestEntity);
		}
		
		//객체 저장
		return memberRepository.save(saveMember);
	}
	
	/***** 회원 로그인 *****/
	@Override
	public Member loginMember(String memberId, String memberPassword) {
		
		//아이디와 비밀번호로 멤버 객체 찾기
		Member member = memberRepository.findMemberByMemberIdAndMemberPassword(memberId, memberPassword);
		
		//member가 존재하지 않을 시
		if(member == null) {
			String msg = "존재하지 않는 아이디입니다.";
		}
		
		//member의 비밀번호가 일치하지 않을 시
		if(!member.getMemberPassword().equals(memberPassword)) {
			String msg = "비밀번호가 일치하지 않습니다.";
		}
		
		//다 통과하면 member반환
		return member;
	}
	
	/***** 회원 수정 ****/
	@Override
	public Member updateMember(MemberDto memberDto) {
		Member member = memberRepository.findByMemberNo(memberDto.getMemberNo());
		
		List<Interest> interests = new ArrayList<>(); 
		
		for (InterestDto interestDto : memberDto.getInterests()) {
			Interest interest = Interest.toEntity(interestDto);
			member.addInterests(interest);
		}
		
		member.setMemberName(memberDto.getMemberName());
		member.setMemberPassword(memberDto.getMemberPassword());
		member.setMemberEmail(memberDto.getMemberEmail());
		
		//DB에 객체 업데이트(기존 객체 존재 시 업데이트됨)
		return memberRepository.save(member);
	}
	
	/***** 회원 상태 수정 *****/
	@Override
	public Member updateMemberStatus(MemberDto memberDto, Integer statusNo) {
		//아이디로 회원 찾기
		Member member = memberRepository.findByMemberNo(memberDto.getMemberNo());
		
		//회원 상태 변경
		member.setMemberStatus(statusNo);
		
		//수정한 객체 반환
		return memberRepository.save(member);
	}
	
	
	
	/***** 회원 삭제 *****/
	@Override
	public Member deleteMember(Long memberNo) {
		//ID로 멤버 찾기
		Member findMember = memberRepository.findByMemberNo(memberNo);
		
		//DB에서 멤버 객체 삭제
		memberRepository.deleteById(memberNo);;
		
		//삭제한 멤버 객체 반환
		return findMember;
	}
	
	/***** 회원 상세 *****/
	@Override
	public Member getMember(Long memberNo) {
		
		//ID로 멤버 탐색
		return memberRepository.findByMemberNo(memberNo);
	}


	/********************************* Interest CRUD **************************************/
	

	/********************************* 이메일 발송 **************************************/
	@Override
	public void findPassword(MemberDto.findPassword memberDto) {
		Member member = memberRepository.findByMemberEmail(memberDto.getEmail());
		
		UUID uid = UUID.randomUUID();
		String tempPassword = uid.toString().substring(0, 10) + "p2$";
		customMailSender.sendFindPasswordMail(memberDto, tempPassword);
		
		//tempPassword = bCryptPasswordEncoder.encode(tempPassword);
		
		member.changePassword(tempPassword);
		
	}
	
	
	
}
