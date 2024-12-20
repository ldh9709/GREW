package com.itwill.jpa.service.member_information;

import java.util.ArrayList;
import java.util.Iterator;
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
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.dto.member_information.InterestDto;
import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.dto.member_information.MemberDto.JoinFormDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Interest;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.InterestRepository;
import com.itwill.jpa.entity.role.Role;
import com.itwill.jpa.repository.member_information.MemberRepository;
import com.itwill.jpa.util.CustomMailSender;

import jakarta.persistence.EntityManager;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	InterestRepository interestRepository;
	@Autowired
	//메일 발송을 위한 메소드 의존성 주입
	CustomMailSender customMailSender;
	
	@Autowired
	EntityManager entityManager;
	
	
	//이메일별 인증번호 저장
	private final Map<String, Integer> tempCode = new ConcurrentHashMap<>();
	
	/*****************회원가입******************
	 - 아이디 : 중복 확인 / 3 ~ 15글자 /  영문자, 숫자, '-' 만 허용 (공백 금지)
	 - 비밀번호 : 8자 이상, 대문자 소문자 숫자 특수문자 중 2가지 이상 허용 (공백 금지)
	 - 이메일 : 중복 확인 / 이메일 형식 (@)이 맞는지 체크(공백금지) / 도메인 유효성 검사
	 * */
	
//	
//	/***** 아이디 유효성(글자수, 포함글자) 체크 *****/
//	public Boolean checkIdValid(String memberId) {
//		boolean hasSpace = memberId.contains(" ");
//		/* 예외처리적용예정 */
//		if(memberId.length() < 3 || memberId.length() > 15) return false;
//		if(!memberId.matches("^[a-zA-Z0-9_]+$")) return false;
//		if(hasSpace) return false;
//		return true;
//	}
//	
//	
//	/***** 비밀번호 유효성(글자수, 포함글자) 체크 *****/
//	public Boolean checkPasswordValid(String memberPassword) {
//		// 1. 비밀번호 길이 검사 (3~15글자)
//		if(memberPassword.length() < 8) return false; //길이오류
//		
//		// 2. 두가지 이상 문자 조합 검사(대문자 소문자 숫자 특수문자 중 2가지 이상 허용 (공백 금지))
//	    boolean hasUppercase = memberPassword.matches(".*[A-Z].*");
//	    boolean hasLowercase = memberPassword.matches(".*[a-z].*");
//	    boolean hasDigit = memberPassword.matches(".*[0-9].*");
//	    boolean hasSpecialChar = memberPassword.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
//	    boolean hasSpace = memberPassword.contains(" ");
//
//	    // 공백이 있는 경우
//	    if (hasSpace) {
//	        return false; // 공백 오류
//	    }
//
//	    // 두 가지 이상의 문자 유형을 만족하는지 확인
//	    int typesCount = 0;
//	    if (hasUppercase) typesCount++;
//	    if (hasLowercase) typesCount++;
//	    if (hasDigit) typesCount++;
//	    if (hasSpecialChar) typesCount++;
//
//	    if (typesCount < 2) {
//	        return false; // 형식 오류 (두 가지 이상 충족하지 않음)
//	    }
//	    
//	    return true;
//	}
//	
//	/* 이메일 유효성 체크 */
//	public Boolean checkEmailValid(String memberEmail) {
//		if(!memberEmail.matches("\\\\.[A-Za-z]{2,}$")) return false;
//		return true;
//	}
	
	
	/***** 아이디 중복체크 *****/	
	public Boolean checkIdDupl(String memberId) {
		Boolean exitsMember = memberRepository.existsByMemberId(memberId);
		return exitsMember;
	}
	
	/* 이메일 중복 체크 */
	public Boolean checkEmailDupl(String memberEmail) {
		Boolean exitstEmail = memberRepository.existsByMemberEmail(memberEmail);
		return exitstEmail ? false : true;
	}
	
	/* 회원가입 */
	@Override
	@Transactional
	public Member saveMember(MemberDto memberDto) {
		String memberId = memberDto.getMemberId();
		String memberPassword = memberDto.getMemberPassword();
		String memberEmail = memberDto.getMemberEmail();
		
		checkIdDupl(memberId);
		checkEmailDupl(memberEmail);
		
		Member saveMember = Member.toEntity(memberDto);
		
		//비밀번호 암호화 추가
		
		
		for (InterestDto interest : memberDto.getInterests()) {
			Interest interestEntity = Interest.toEntity(interest);
			saveMember.addInterests(interestEntity);
		}
		saveMember.setMemberPassword(passwordEncoder.encode(memberPassword));
		System.out.println(">>>>>saveMember : " + saveMember);
		return memberRepository.save(saveMember);
	}
	
	/***** 아이디 찾기 *****/
	public String getMemberId(String memberEmail) {
		String memberId = memberRepository.findMemberIdByMemberEmail(memberEmail).get();
		return maskMemberId(memberId);
		
	}
	
	/***** 아이디 마스킹 처리 *****/
	public String maskMemberId(String memberId) {
		int length = memberId.length();
		
		if(length <=6) {
			//앞 2글자만 보임
			return memberId.substring(0,2) + "*".repeat(length -2);
		}else {
			//앞 2글자, 뒤 2글자만 보임
			return memberId.substring(0,2) + "*".repeat(length-4) + memberId.substring(length-2);
		}
		
	}
	
	/***** 비밀번호 재설정 *****/
	public void updateMemberPassword(Member member, String newPassword) {
		member.changePassword(newPassword);
	}
	
	/***** 회원 로그인 ****
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
	}*/
	
	/***** 회원 수정 ****/
//	@Override
//	public Member updateMember(MemberDto memberDto) {
//		Member member = memberRepository.findByMemberNo(memberDto.getMemberNo());
//		
//		List<Interest> interests = new ArrayList<>(); 
//	 	// 관심사 업데이트
//        for (InterestDto interestDto : memberDto.getInterests()) {
//            Interest interest = Interest.toEntity(interestDto);
//            member.addInterests(interest);
//	    }
//		
//		member.setMemberName(memberDto.getMemberName());
//		member.setMemberPassword(memberDto.getMemberPassword());
//		member.setMemberEmail(memberDto.getMemberEmail());
//		
//		return memberRepository.save(member);
//	}
	
	
	/***** 회원 수정 ****/
	@Override
	@Transactional
	public Member updateMember(MemberDto memberDto) {
		Member member = memberRepository.findByMemberNo(memberDto.getMemberNo());
		
		member.getInterests().clear();
		
	 	// 관심사 업데이트
        for (InterestDto interestDto : memberDto.getInterests()) {
            Interest interest = Interest.toEntity(interestDto);
            member.addInterests(interest);
	    }
		
		member.setMemberName(memberDto.getMemberName());
		member.setMemberPassword(memberDto.getMemberPassword());
		member.setMemberEmail(memberDto.getMemberEmail());
		
		return memberRepository.save(member);
	}
	
	/***** 회원 상태 수정 *****/
	@Override
	public Member updateMemberStatus(Long memberNo, Integer statusNo) {
		Member member = memberRepository.findByMemberNo(memberNo);
		member.setMemberStatus(statusNo);
		
		return memberRepository.save(member);
	}
	
	
	/***** 회원 삭제 *****/
	@Override
	public Member deleteMember(Long memberNo) {
		Member findMember = memberRepository.findByMemberNo(memberNo);
		memberRepository.deleteById(memberNo);;
		
		return findMember;
	}
	
	/**** 회원정보 상세 보기 ****/
	@Override
	public Member getMember(Long memberNo) {
		return memberRepository.findByMemberNo(memberNo);
	}

	/********************************* Interest CRUD **************************************/
	/***** 회원 전체 출력 ****
	 * 필터 : 멘티, 멘토 
	 * 정렬 : 1(초기) - 가입 순, 2-이름 순 
	 * */
	public List<MemberDto> getMemberAll(String roleStr, Integer order){
		
		Role role = Role.ROLE_MENTEE; 
		if(roleStr.equals("ROLE_MENTOR")) {
			role = Role.ROLE_MENTOR;
		}
		
		List<Member> memberList = new ArrayList<>();
		List<MemberDto> memberDtoList = new ArrayList<>();
		
		switch (order) {
			case 1: {
				memberList = memberRepository.findByMemberRoleOrderByMemberJoinDateAsc(role);
				break;
			}
			case 2: {
				memberList = memberRepository.findByMemberRoleOrderByMemberNameAsc(role);
				break;
			}
		}
		
		for (Member member : memberList) {
			memberDtoList.add(MemberDto.toBasicDto(member));
		}
		
		return memberDtoList;
	}
	
	
	/***** 신고 카운트 증가 *****/
	@Override
	public Member incrementReportCount(Long MemberNo) {
		Member member = memberRepository.findById(MemberNo).get();
		member.setMemberReportCount(member.getMemberReportCount()+1);
		return memberRepository.save(member);
	}

	/********************************* 이메일 발송 **************************************/
	//회원가입 시 인증번호 메일 전송
	@Override
	public Integer sendJoinCode(MemberDto.JoinFormDto joinForm) {
		//랜덤 숫자 객체 생성
		Random random = new Random();
		
		//6자리 숫자 임시번호 발급
		Integer tempNo = random.nextInt(900000) + 100000;
		
		//메일 발송
		customMailSender.sendJoinMaill(joinForm, tempNo);
		
		//인증번호 저장
		tempCode.put(joinForm.getEmail(), tempNo);
		
		System.out.println("저장된 인증번호: " + tempNo + ", 이메일: " + joinForm.getEmail());
		
		return tempNo;
	}
	
	//인증번호 확인
	@Override
	public boolean certificationCode(String memberEmail, Integer inputCode) {
		//입력받은 이메일로 저장된 인증번호 반환
		Integer storedCode = tempCode.get(memberEmail);
		
		System.out.println("storedCode : <<<" + storedCode);
		System.out.println("email : <<<" + memberEmail);
		System.out.println("inputCode : <<<" + inputCode);
		//유효성 검사 후 안맞으면 false 반환
		if(storedCode == null || !storedCode.equals(inputCode)) {
			return false;
		}
		
		//맞으면 데이터 삭제 후 true 반환
		tempCode.remove(memberEmail);
		return true;
		
	}
	
	@Override
	public Integer getTempCode(String email) {
		//이메일에 해당하는 인증번호 가져오기
		return tempCode.get(email);
	}
	/***** 아이디 찾기 *****/
	@Override
	//아이디 찾기 시 사용
	public void findId(MemberDto.findId memberDto) {
		Member member = memberRepository.findByMemberEmail(memberDto.getEmail());
		
		if(member == null) {
			System.out.println("존재하지 않는 계정입니다.");
		}
		
		if(!member.getMemberName().equals(memberDto.getName())) {
			System.out.println("성함이 일치하지 않습니다.");
		}
		
		//랜덤 숫자 객체 생성
		Random random = new Random();
		
		//6자리 숫자 임시번호 발급
		Integer tempNo = random.nextInt(900000) + 100000;
		
		//인증번호 저장
		tempCode.put(memberDto.getEmail(), tempNo);
		
		//메일 발송
		customMailSender.sendFindIdMail(memberDto, tempNo);
	}
	
	//아이디 찾기 인증번호 확인
	@Override
	public boolean certificationCodeByFindId(String email, Integer inputCode) {
		//입력받은 이메일로 저장된 인증번호 반환
		Integer storedCode = tempCode.get(email);
		
		System.out.println("storedCode : <<<" + storedCode);
		System.out.println("email : <<<" + email);
		System.out.println("inputCode : <<<" + inputCode);
		//유효성 검사 후 안맞으면 false 반환
		if(storedCode == null || !storedCode.equals(inputCode)) {
			return false;
		}
		
		//맞으면 데이터 삭제 후 true 반환
		tempCode.remove(email);
		
		return true;
		
	}
	
	//이메일로 멤버 찾기
	@Override
	public Member getMemberByMemberEmail(String memberEmail) {
		return memberRepository.findByMemberEmail(memberEmail);
	}
    /**************************************************************************************/
	
	@Override
	//비밀번호 찾기 이메일 전송
	public void findPassword(MemberDto.findPassword memberDto) {
		Member member = memberRepository.findByMemberEmail(memberDto.getEmail());
		
		UUID uid = UUID.randomUUID();
		String tempPassword = uid.toString().substring(0, 10) + "p2$";
		customMailSender.sendFindPasswordMail(memberDto, tempPassword);
		
		tempPassword = passwordEncoder.encode(tempPassword);
		
		member.changePassword(tempPassword);
		
	}

	@Override
	public Member updateMemberStatus(MemberDto memberDto, Integer statusNo) {
		return null;
	}

	
	
	
	
}
