package com.itwill.jpa.service.member_information;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.auth.PrincipalDetails;
import com.itwill.jpa.dto.member_information.InterestDto;
import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.dto.member_information.MemberDto.JoinFormDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Interest;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.InterestRepository;
import com.itwill.jpa.entity.role.Role;
import com.itwill.jpa.exception.CustomException;
import com.itwill.jpa.repository.member_information.MemberRepository;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.util.CustomMailSender;
import com.itwill.jpa.util.JWTUtil;

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
		//아이디가 중복이면 true, 아니면 false
		return exitsMember;
	}
	
	/* 이메일 중복 체크 */
	public Boolean checkEmailDupl(String memberEmail) {
		Boolean exitstEmail = memberRepository.existsByMemberEmail(memberEmail);
		//이메일이 중복이면 false, 중복이 아니면 true
		return exitstEmail ? false : true;
	}
	
	/* 회원가입 */
	@Override
	@Transactional
	public Member saveMember(MemberDto memberDto) {
		try {
			//매개변수가 null이면 오류
			if(memberDto == null) {
				throw new CustomException(ResponseStatusCode.NOT_FOUND_MEMBER, ResponseMessage.NOT_FOUND_MEMBER, null);
			}
			
			//멤버DTO 객체에서 속성 분리
			String memberId = memberDto.getMemberId();
			String memberPassword = memberDto.getMemberPassword();
			String memberEmail = memberDto.getMemberEmail();
			
			//ID중복 체크
			boolean checkId = checkIdDupl(memberId);
			
			//ID중복 시 에러
			if(checkId) {
				throw new CustomException(ResponseStatusCode.DUPLICATION_MENBER_ID, ResponseMessage.DUPLICATION_MENBER_ID, null);
			}
			
			//이메일 중복 체크
			boolean checkEmail = checkEmailDupl(memberEmail);
			
			//이메일 중복 시 에러
			if(!checkEmail) {
				throw new CustomException(ResponseStatusCode.DUPLICATION_MENBER_EMAIL, ResponseMessage.DUPLICATION_MENBER_EMAIL, null);
			}
			
			//멤버 생성
			Member saveMember = Member.toEntity(memberDto);
			
			//비밀번호 암호화 추가
			saveMember.setMemberPassword(passwordEncoder.encode(memberPassword));
			
			//관심사 생성
			for (InterestDto interest : memberDto.getInterests()) {
				
				Interest interestEntity = Interest.toEntity(interest);
				System.out.println("회원가입 interestEntity : " + interestEntity);
				
				saveMember.addInterests(interestEntity);
			}
			
			System.out.println(">>>>>saveMember : " + saveMember);
			return memberRepository.save(saveMember);
			
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.CREATED_MEMBER_FAIL, ResponseMessage.CREATED_MEMBER_FAIL, e);
		}
		
		
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
        
		if(memberDto.getMemberName() != null) {
			member.setMemberName(memberDto.getMemberName());
		}
		
		if(memberDto.getMemberPassword() != null) {
			member.setMemberPassword(memberDto.getMemberPassword());
		} else {
			member.setMemberPassword(member.getMemberPassword());
		}
		
		if(memberDto.getMemberEmail() != null) {
			member.setMemberEmail(memberDto.getMemberEmail());
		}
		
		return memberRepository.save(member);
	}
	
	/***** 회원 상태 수정 *****/
	@Override
	public Member updateMemberStatus(Long memberNo, Integer statusNo) {
		Member member = memberRepository.findByMemberNo(memberNo);
		member.setMemberStatus(statusNo);
		
		return memberRepository.save(member);
	}
	
	/***** 회원 권한 변경 *****/
	public Member updateMemberRole(Long memberNo, String role) {
		Member member = memberRepository.findByMemberNo(memberNo);
		
		if (role.equals("ROLE_MENTEE")) {
		    member.setMemberRole(Role.ROLE_MENTEE);
		} else {
		    member.setMemberRole(Role.ROLE_MENTOR);
		}
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
	 * 필터 : 전체,멘티, 멘토 
	 * 정렬 : 1(초기) - 가입 순, 2-이름 순 
	 * */
	public Page<MemberDto> getMemberAll(String roleStr, Integer order, int pageNumber, int pageSize){
		try {
			
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			
			 Role role = null; 
	        if (!roleStr.equals("ALL")) { 
	            if (roleStr.equals("ROLE_MENTOR")) {
	                role = Role.ROLE_MENTOR;
	            } else {
	                role = Role.ROLE_MENTEE;
	            }
	        }
			
			Page<Member> memberList = null;
			List<MemberDto> memberDtoList = new ArrayList<>();
			
			switch (order) {
				// 가입순(회원번호순)
				case 1: {
					if(role == null) {
						memberList = memberRepository.findAllByOrderByMemberNoDesc(pageable);
					}else {
						memberList = memberRepository.findByMemberRoleOrderByMembeNoDesc(role, pageable);
					}
					break;
				}
				// 가입순(이름 순)
				case 2: {
					if(role == null) {
						memberList = memberRepository.findAllByOrderByMemberNameAsc(pageable);
					}else {
						memberList = memberRepository.findByMemberRoleOrderByMemberNameAsc(role, pageable);
					}
					break;
				}
			}
			
			for (Member member : memberList) {
				memberDtoList.add(MemberDto.toBasicDto(member));
			}
			
			return new PageImpl<>(memberDtoList, pageable, memberList.getTotalElements());
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_MEMBER_FAIL, ResponseMessage.READ_MEMBER_FAIL, e);
		}
		
	}
	
	
	/***** 신고 카운트 증가 *****/
	@Override
	@Transactional
	public Member incrementReportCount(Long memberNo) {
		System.out.println("번호넘어오긴함" + memberNo);
		Member member = memberRepository.findByMemberNo(memberNo);
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
		Member member = memberRepository.findByMemberEmail(memberDto.getMemberEmail());
		
		if(member == null) {
			throw new CustomException(ResponseStatusCode.NOT_FOUND_MEMBER, ResponseMessage.NOT_FOUND_MEMBER, null);
		}
		
		if(!member.getMemberName().equals(memberDto.getMemberName())) {
			throw new CustomException(ResponseStatusCode.NOT_AGREEMENT_MEMBER_NAME, ResponseMessage.NOT_AGREEMENT_MEMBER_NAME, null);
		}
		
		//랜덤 숫자 객체 생성
		Random random = new Random();
		
		//6자리 숫자 임시번호 발급
		Integer tempNo = random.nextInt(900000) + 100000;
		
		//인증번호 저장
		tempCode.put(memberDto.getMemberEmail(), tempNo);
		
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
		
		if(!member.getMemberName().equals(memberDto.getMemberName())) {
			throw new CustomException(ResponseStatusCode.AUTHENTICATION_FAILED, ResponseMessage.AUTHENTICATION_FAILED, null);
		}
		
		UUID uid = UUID.randomUUID();
		String tempPassword = uid.toString().substring(0, 10) + "p2$";
		customMailSender.sendFindPasswordMail(memberDto, tempPassword);
		
		tempPassword = passwordEncoder.encode(tempPassword);
		
		member.changePassword(tempPassword);
		
	}
	
	
	/* 토큰 재생성 메소드 */
	public Map<String, String> regenerateTokens(Authentication authentication, Member updatedMember) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Map<String, Object> claims = principalDetails.getClaims();
        
        // 필요한 권한 정보 갱신
        claims.put("memberRole", updatedMember.getMemberRole());

        // 새 토큰 생성
        String newAccessToken = JWTUtil.generateToken(claims, 60); // 60분
        String newRefreshToken = JWTUtil.generateToken(claims, 60 * 24); // 24시간

        // 토큰을 반환
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", newAccessToken);
        tokens.put("refreshToken", newRefreshToken);

        return tokens;
	}
	
}
