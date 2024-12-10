package com.itwill.jpa.service.member_information;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.member_information.InterestDto;
import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.dto.member_information.MemberDto.JoinFormDto;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//이메일별 인증번호 저장
	private final Map<String, Integer> tempCode = new ConcurrentHashMap<>();
	
	/***** 회원 가입 *****/
	@Override
	public Member saveMember(MemberDto memberDto) {
		
		//MemberDto Entity로 변경
		Member saveMember = Member.toEntity(memberDto);
		
		for (InterestDto interest : memberDto.getInterests()) {
			
			Interest interestEntity = Interest.toEntity(interest);
			
			saveMember.addInterests(interestEntity);
		}
		saveMember.setMemberPassword(passwordEncoder.encode(memberDto.getMemberPassword()));
		
		//객체 저장
		return memberRepository.save(saveMember);
	}
	
	/***** 회원 로그인 *****/
	@Override
	public Member loginMember(String memberId, String memberPassword) {
		
		//아이디와 비밀번호로 멤버 객체 찾기
		Member member = memberRepository.findMemberByMemberIdAndMemberPassword(memberId, memberPassword);
		
//		//member가 존재하지 않을 시
//		if(member == null) {
//			String msg = "존재하지 않는 아이디입니다.";
//		}
//		
//		//member의 비밀번호가 일치하지 않을 시
//		if(!member.getMemberPassword().equals(memberPassword)) {
//			String msg = "비밀번호가 일치하지 않습니다.";
//		}
		
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
	//회원가입 시 인증번호 메일 전송
	@Override
	public void sendJoinCode(MemberDto.JoinFormDto joinForm) {
		//랜덤 숫자 객체 생성
		Random random = new Random();
		
		//6자리 숫자 임시번호 발급
		Integer tempNo = random.nextInt(900000) + 100000;
		
		//메일 발송
		customMailSender.sendJoinMaill(joinForm, tempNo);
		
		//인증번호 저장
		tempCode.put(joinForm.getEmail(), tempNo);
		
		System.out.println("저장된 인증번호: " + tempNo + ", 이메일: " + joinForm.getEmail());
	}
	
	//인증번호 확인
	@Override
	public boolean certificationCode(String email, Integer inputCode) {
		//입력받은 이메일로 저장된 인증번호 반환
		Integer storedCode = tempCode.get(email);
		
		//유효성 검사 후 안맞으면 false 반환
		if(storedCode == null || !storedCode.equals(inputCode)) {
			return false;
		}
		
		//맞으면 데이터 삭제 후 true 반환
		tempCode.remove(email);
		return true;
		
	}
	
	@Override
	public Integer getTempCode(String email) {
		//이메일에 해당하는 인증번호 가져오기
		return tempCode.get(email);
	}
	
	@Override
	//비밀번호 찾기 이메일 전송
	public void findPassword(MemberDto.findPassword memberDto) {
		Member member = memberRepository.findByMemberEmail(memberDto.getEmail());
		
		UUID uid = UUID.randomUUID();
		String tempPassword = uid.toString().substring(0, 10) + "p2$";
		customMailSender.sendFindPasswordMail(memberDto, tempPassword);
		
		//tempPassword = bCryptPasswordEncoder.encode(tempPassword);
		
		//member.changePassword(tempPassword);
		
	}
	
	
	
}
