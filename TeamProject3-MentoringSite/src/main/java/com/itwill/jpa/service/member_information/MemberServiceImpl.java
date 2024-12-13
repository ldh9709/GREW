package com.itwill.jpa.service.member_information;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.member_information.InterestDto;
import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Interest;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberRepository memberRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
	
	/*****************회원가입******************
	 - 아이디 : 중복 확인 / 3 ~ 15글자 /  영문자, 숫자, '-' 만 허용 (공백 금지)
	 - 비밀번호 : 8자 이상, 대문자 소문자 숫자 특수문자 중 2가지 이상 허용 (공백 금지)
	 - 이메일 : 중복 확인 / 이메일 형식 (@)이 맞는지 체크(공백금지) / 도메인 유효성 검사
	 * */
	
	
	/***** 아이디 유효성(글자수, 포함글자) 체크 *****/
	public Boolean checkIdValid(String memberId) {
		boolean hasSpace = memberId.contains(" ");
		
		/* 예외처리적용예정 */
		if(memberId.length() < 3 || memberId.length() > 15) return false;
		if(!memberId.matches("^[a-zA-Z0-9_]+$")) return false;
		if(hasSpace) return false;
		return true;
	}
	
	/***** 아이디 중복체크 *****/	
	public Boolean checkIdDupl(String memberId) {
		Boolean exitsMember = memberRepository.existByMemberId(memberId);
		return exitsMember ? false : true;
	}
	
	/***** 비밀번호 유효성(글자수, 포함글자) 체크 *****/
	public Boolean checkPasswordValid(String memberPassword) {
		// 1. 비밀번호 길이 검사 (3~15글자)
		if(memberPassword.length() < 8) return false; //길이오류
		
		// 2. 두가지 이상 문자 조합 검사(대문자 소문자 숫자 특수문자 중 2가지 이상 허용 (공백 금지))
	    boolean hasUppercase = memberPassword.matches(".*[A-Z].*");
	    boolean hasLowercase = memberPassword.matches(".*[a-z].*");
	    boolean hasDigit = memberPassword.matches(".*[0-9].*");
	    boolean hasSpecialChar = memberPassword.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
	    boolean hasSpace = memberPassword.contains(" ");

	    // 공백이 있는 경우
	    if (hasSpace) {
	        return false; // 공백 오류
	    }

	    // 두 가지 이상의 문자 유형을 만족하는지 확인
	    int typesCount = 0;
	    if (hasUppercase) typesCount++;
	    if (hasLowercase) typesCount++;
	    if (hasDigit) typesCount++;
	    if (hasSpecialChar) typesCount++;

	    if (typesCount < 2) {
	        return false; // 형식 오류 (두 가지 이상 충족하지 않음)
	    }
	    
	    return true;
	}
	
	/* 이메일 유효성 체크 */
	public Boolean checkEmailValid(String memberEmail) {
		if(!memberEmail.matches("\\\\.[A-Za-z]{2,}$")) return false;
		return true;
	}
	
	/* 이메일 중복 체크 */
	public Boolean checkEmailDupl(String memberEmail) {
		Boolean exitstEmail = memberRepository.existByMemberEmail(memberEmail);
		return exitstEmail ? false : true;
	}
	
	/* 회원가입 */
	@Override
	public Member saveMember(MemberDto memberDto) {
		String id = memberDto.getMemberId();
		String pw = memberDto.getMemberPassword();
		String email = memberDto.getMemberEmail();
		
		//아이디 유효성 체크
		checkIdValid(id);
		checkIdDupl(id);
		
		//비밀번호 유효성 체크
		checkPasswordValid(pw);
		
		//이메일 유효성 체크 
		checkEmailValid(email);
		checkEmailDupl(email);
		
		Member saveMember = Member.toEntity(memberDto);
		//비밀번호 암호화 추가
		
		
		for (InterestDto interest : memberDto.getInterests()) {
			Interest interestEntity = Interest.toEntity(interest);
			saveMember.addInterests(interestEntity);
		}
		
		return memberRepository.save(saveMember);
	}
	
	/***** 아이디 찾기 *****/
	
	/***** 비밀번호 재설정 *****/
	
	/***** 회원 로그인 ****
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
	}*/
	
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

	/***** 신고 카운트 증가 *****/
	@Override
	public Member incrementReportCount(Long MemberNo) {
		Member member = memberRepository.findById(MemberNo).get();
		member.setMemberReportCount(member.getMemberReportCount()+1);
		return memberRepository.save(member);
	}

	/********************************* Interest CRUD **************************************/
	

}
