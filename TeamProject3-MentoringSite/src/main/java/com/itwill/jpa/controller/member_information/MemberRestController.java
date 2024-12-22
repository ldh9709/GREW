package com.itwill.jpa.controller.member_information;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.auth.PrincipalDetails;
import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.dto.member_information.MemberDtoAndTempCode;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.member_information.MentorProfile;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.member_information.EmailService;
import com.itwill.jpa.service.bullentin_board.AnswerService;
import com.itwill.jpa.service.bullentin_board.InquiryService;
import com.itwill.jpa.service.chatting_review.ChatRoomService;
import com.itwill.jpa.service.chatting_review.ReviewService;
import com.itwill.jpa.service.member_information.FollowService;
import com.itwill.jpa.service.member_information.MemberService;
import com.itwill.jpa.service.member_information.MentorBoardService;
import com.itwill.jpa.service.member_information.MentorProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/member")
public class MemberRestController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MentorProfileService mentorProfileService;
	@Autowired
	private MentorBoardService boardService;
	@Autowired
	private InquiryService inquiryService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private FollowService followService;
	@Autowired
	private ChatRoomService chatRoomService;
	@Autowired
	private ReviewService reviewService;
	
	
	
	/* 아이디 중복 */
	@Operation(summary = "아이디 중복 검사")
	@GetMapping("/check-memberId")
	public ResponseEntity<Response> checkIdDupl(@RequestParam(name = "memberId") String memberId){
		
		Boolean checkIdDupl = memberService.checkIdDupl(memberId);

		Response response = new Response();
		
		//응답 객체 생성
		
		if(checkIdDupl == true) {
			//응답객체에 코드, 메시지, 객체 설정
			response.setStatus(ResponseStatusCode.CREATED_MEMBER_SUCCESS);
			response.setMessage("삐빅 아이디 중복입니다");
			response.setData(null);
		}
		
		//인코딩 타입 설정
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		//반환할 응답Entity 생성
		ResponseEntity<Response> responseEntity =
				 new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		//반환
		return responseEntity;
				
	}
	
	@Autowired
	private EmailService emailService;
	
	
	@Operation(summary = "인증번호 발송")
	@PostMapping("/sendJoinCode")
	public ResponseEntity<Response> sendJoinCode(@RequestBody MemberDto.JoinFormDto joinFormDto) {
		
		Integer tempNo = memberService.sendJoinCode(joinFormDto);
		
		Response response = new Response();
		
		response.setStatus(ResponseStatusCode.EMAIL_SEND_SUCCESS);
		response.setMessage(ResponseMessage.EMAIL_SEND_SUCCESS);
		response.setData(tempNo);
		//인코딩 타입 설정
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		//반환할 응답Entity 생성
		ResponseEntity<Response> responseEntity =
				 new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	
	/* 회원 저장 */
	@Operation(summary = "회원가입/관심사 입력")
	@PostMapping("/createMember")
	public ResponseEntity<Response> createMember(@RequestBody MemberDtoAndTempCode memberJoinDto) {
		
		MemberDto memberDto = memberJoinDto.getMemberDto();
		System.out.println("MEMBERDTO : >>> " + memberDto);
		Integer tempCode = memberJoinDto.getTempCode();
		System.out.println("TEMPCODE : >>>" + tempCode);
		
		Response response = new Response();
		
		//인증번호가 맞는지 확인
		boolean isChecked = memberService.certificationCode(memberDto.getMemberEmail(), tempCode);
		
		System.out.println("isChecked : " + isChecked);
		if(!isChecked) {
			response.setStatus(ResponseStatusCode.CREATED_MEMBER_FAIL);
			response.setMessage(ResponseMessage.CREATED_MEMBER_FAIL);
		}
		System.out.println(">>>>>saveMember memberDto : " + memberDto);
		Member member = memberService.saveMember(memberDto);
		System.out.println(">>>>>MEMBER member : " + member);
		
		response.setStatus(ResponseStatusCode.CREATED_MEMBER_SUCCESS);
		response.setMessage(ResponseMessage.CREATED_MEMBER_SUCCESS);
		
		//인코딩 타입 설정
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		//반환할 응답Entity 생성
		ResponseEntity<Response> responseEntity =
				 new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
			
			return responseEntity;
		}
	
	/*** 
	 * 회원 저장(멘토까지)
	 ***/
	@Operation(summary = "회원가입/관심사 입력/멘토더미데이터")
	@PostMapping("/createMember/mentor")
	public ResponseEntity<Response> createMemberMentor(@RequestBody MemberDtoAndTempCode memberJoinDto) {
		
		MemberDto memberDto = memberJoinDto.getMemberDto();
		System.out.println("MEMBERDTO : >>> " + memberDto);
		Integer tempCode = memberJoinDto.getTempCode();
		System.out.println("TEMPCODE : >>>" + tempCode);
		
		Response response = new Response();
		
		//인증번호가 맞는지 확인
		boolean isChecked = memberService.certificationCode(memberDto.getMemberEmail(), tempCode);
		
		System.out.println("isChecked : " + isChecked);
		if(!isChecked) {
			response.setStatus(ResponseStatusCode.CREATED_MEMBER_FAIL);
			response.setMessage(ResponseMessage.CREATED_MEMBER_FAIL);
		}
		System.out.println(">>>>>saveMember memberDto : " + memberDto);
		Member member = memberService.saveMember(memberDto);
		System.out.println(">>>>>MEMBER member : " + member);
		MentorProfile mentor =	mentorProfileService.saveMentorDummyProfile(member.getMemberNo());
		
		//System.out.println("mentor : >>>>>" + mentor);
		
		response.setStatus(ResponseStatusCode.CREATED_MEMBER_SUCCESS);
		response.setMessage(ResponseMessage.CREATED_MEMBER_SUCCESS);
		
		//인코딩 타입 설정
		HttpHeaders httpHeaders = new HttpHeaders();	
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		//반환할 응답Entity 생성
		ResponseEntity<Response> responseEntity =
				 new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
			
			return responseEntity;
		}
	
	/* 회원 정보 보기 */
	@SecurityRequirement(name = "BearerAuth")//API 엔드포인트가 인증을 요구한다는 것을 문서화(Swagger에서 JWT인증을 명시
	@Operation(summary = "회원 정보 보기")
	@GetMapping("/member-info")
	public ResponseEntity<Response> getMember(@RequestParam(name = "memberNo") Long memberNo) {
		
		//번호로 멤버 객체 찾기
		Member member = memberService.getMember(memberNo);
		
		//DTO객체로 변환
		MemberDto memberDto = MemberDto.tofindDto(member);
		
		Response response = new Response();
		
		if(memberDto != null) {
			//응답객체에 코드, 메시지, 객체 설정
			response.setStatus(ResponseStatusCode.READ_MEMBER_SUCCESS);
			response.setMessage(ResponseMessage.READ_MEMBER_SUCCESS);
			response.setData(memberDto);
		}
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	
	/***** 회원 정보 보기(토큰) *****/
	@Operation(summary = "회원 정보 보기(토큰)")
	@SecurityRequirement(name = "BearerAuth")//API 엔드포인트가 인증을 요구한다는 것을 문서화(Swagger에서 JWT인증을 명시
	@PreAuthorize("hasRole('MENTEE') or hasRole('MENTOR') ")//ROLE이 MENTEE인 사람만 접근 가능
	@GetMapping("/profile")
	public ResponseEntity<Response> getMember(Authentication authentication) {
		
		//PrincipalDetails에서 memberNo를 가져옴
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		Long memberNo = principalDetails.getMemberNo();
		System.out.println(">>>>> getAuthorities : " + authentication.getAuthorities());
		System.out.println(">>>>> authentica  tion : " + authentication);
		System.out.println(">>>>> au	thentication.getName() : " + authentication.getName());
		System.out.println(">>> Granted Authorities: " + authentication.getAuthorities());
		System.out.println(">>> PrincipalDetails Authorities: " + principalDetails.getAuthorities());
		
		// 번호로 멤버 객체 찾기
        Member loginMember = memberService.getMember(memberNo);
        
		//DTO객체로 변환
		MemberDto loginMemberDto = MemberDto.toDto(loginMember);
		
		Response response = new Response();
		
		if(loginMemberDto != null) {
			//응답객체에 코드, 메시지, 객체 설정
			response.setStatus(ResponseStatusCode.READ_MEMBER_SUCCESS);
			response.setMessage(ResponseMessage.READ_MEMBER_SUCCESS);
			response.setData(loginMemberDto);
		}
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	
	/* 회원 정보 수정 */
	
	@Operation(summary = "회원 정보 수정")
	@PutMapping("/profile/edit/{memberNo}")
	@SecurityRequirement(name = "BearerAuth")//API 엔드포인트가 인증을 요구한다는 것을 문서화(Swagger에서 JWT인증을 명시
	public ResponseEntity<Response> updateMember(
			@RequestBody MemberDto memberDto,
			@PathVariable("memberNo") Long memberNo
			) {
		System.out.println("회원 정보 수정 : >>>>>" + memberDto);
		System.out.println("회원 정보 수정 : >>>>>" + memberNo);
		log.info(">>>>> 컨트롤러에 요청 도달: memberNo={}", memberNo);
	    log.info(">>>>> 수정 요청 데이터: {}", memberDto);
		//Authentication authentication =	SecurityContextHolder.getContext().getAuthentication();
		//PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		
		//Long memberNo = principalDetails.getMemberNo();
		
		//클라이언트에서 보낸 데이터 무시하고 인증된 사용자 정보로 덮어씀(생략가능, 명시적으로 입력)
		//memberDto.setMemberNo(memberNo);
		
		//업데이트 메소드 실행
		Member updateMember = memberService.updateMember(memberDto);
		 
		MemberDto updateMemberDto = MemberDto.toDto(updateMember);
		
		Response response = new Response();
		
		if(updateMemberDto != null) {
			//응답객체에 코드, 메시지, 객체 설정
			response.setStatus(ResponseStatusCode.UPDATE_MEMBER_SUCCESS);
			response.setMessage(ResponseMessage.UPDATE_MEMBER_SUCCESS);
			response.setData(updateMemberDto);
		}
		System.out.println("반환 객체 : " + response.getData());
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	/* 회원 상태 수정 */
	@Operation(summary = "회원 상태 수정")
	@PutMapping("/{memberNo}/status/{statusNo}")
	public ResponseEntity<Response> updateMemberStatus(
			@PathVariable(name="memberNo") Long memberNo,
			@PathVariable(name = "statusNo") Integer statusNo) {
		
		//업데이트 메소드 실행
		Member updateMember = memberService.updateMemberStatus(memberNo, statusNo);
		
		MemberDto updateMemberDto = MemberDto.toDto(updateMember);
		
		Response response = new Response();
		
		if(updateMemberDto != null) {
			//응답객체에 코드, 메시지, 객체 설정
			response.setStatus(ResponseStatusCode.UPDATE_MEMBER_SUCCESS);
			response.setMessage(ResponseMessage.UPDATE_MEMBER_SUCCESS);
			response.setData(updateMember);
		}
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		 
		return responseEntity;
	}
	
	/***** 아이디 찾기: 인증번호 발송 *****/
	@Operation(summary = "1. 아이디 찾기 : 이메일 발송")
	@PostMapping("/findId/sendEmail")
	public ResponseEntity<Response> findId(@RequestBody MemberDto.findId memberDto) {
	    
		Response response = new Response();
	    
	    try {
	    	memberService.findId(memberDto);
	        response.setStatus(ResponseStatusCode.EMAIL_SEND_SUCCESS);
	        response.setMessage(ResponseMessage.EMAIL_SEND_SUCCESS);
	    } catch (Exception e) {
	        response.setStatus(ResponseStatusCode.EMAIL_SEND_FAIL);
	        response.setMessage(ResponseMessage.EMAIL_SEND_FAIL);
	    }
	    
	    return ResponseEntity.ok(response);
	}
	
	/***** 아이디 찾기: 인증번호 확인 후 아이디 반환 *****/
	@Operation(summary = "2. 아이디 찾기 : 인증번호 확인 후 아이디 반환 ")
	@PostMapping("/findId/certificationCode")
	public ResponseEntity<Response> certificationFindId(@RequestParam(name = "memberEmail") String memberEmail, @RequestParam(name = "inputCode") Integer inputCode) {
	    
		Boolean isChecked =	memberService.certificationCodeByFindId(memberEmail, inputCode);
		
		Response response = new Response();
	    
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
		
	    //인증번호가 틀리면 실패 반환
		if(!isChecked) {
			response.setStatus(ResponseStatusCode.INPUTCODE_CONFIRM_FAIL);
			response.setMessage(ResponseMessage.INPUTCODE_CONFIRM_FAIL);
			ResponseEntity<Response> responseEntity = 
					new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
			return responseEntity;
		}
		
		//인증 제공자가 Email이 아니면
		if(!memberService.getMemberByMemberEmail(memberEmail).getMemberProvider().equals("Email")) {
			response.setStatus(ResponseStatusCode.MEMBER_IS_NOT_EMAIL);
			response.setMessage(ResponseMessage.MEMBER_IS_NOT_EMAIL);
			ResponseEntity<Response> responseEntity = 
					new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
			return responseEntity;
		}
		
		String memberId = memberService.getMemberByMemberEmail(memberEmail).getMemberId();
		response.setStatus(ResponseStatusCode.INPUTCODE_CONFIRM_SUCCESS);
		response.setMessage(ResponseMessage.INPUTCODE_CONFIRM_SUCCESS);
		response.setData(memberId);
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
	    return responseEntity;
	}
	
	/***** 비밀번호 찾기 : 이메일 발송 *****/
	@Operation(summary = "비밀번호 찾기")
	@PostMapping("/findPassword")
	public ResponseEntity findPassword(@RequestBody MemberDto.findPassword memberDto) {
		
		Response response = new Response();
	    
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
		if(memberDto == null) {
			response.setStatus(ResponseStatusCode.PASSWORD_RESET_FAIL);
			response.setMessage(ResponseMessage.PASSWORD_RESET_FAIL);
			ResponseEntity<Response> responseEntity = 
					new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
			
		    return responseEntity;
		}
		
		memberService.findPassword(memberDto);
		
		response.setStatus(ResponseStatusCode.PASSWORD_RESET_SUCCESS);
		response.setMessage(ResponseMessage.PASSWORD_RESET_SUCCESS);
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
	    return responseEntity;
	}
	
	
	/* 멘티 회원 활동 요약 */
	@Operation(summary = "멘티 활동 내역 요약")
	@SecurityRequirement(name = "BearerAuth")//API 엔드포인트가 인증을 요구한다는 것을 문서화(Swagger에서 JWT인증을 명시
	@PreAuthorize("hasRole('MENTEE')")//ROLE이 MENTEE인 사람만 접근 가능
	@GetMapping("/mentee-summary")
	public ResponseEntity<Response> getMenteeSummary(Authentication authentication){
		
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		Long menteeNo = principalDetails.getMemberNo(); 
		
		Long inquiryCount =  inquiryService.getInquiryByMember(menteeNo, 0, 10).getTotalElements();
		Long counselCount = chatRoomService.selectChatRoomByMenteeNo(menteeNo, 0, 10).getTotalElements();
		Long followCount = followService.getMentorList(menteeNo, 0, 10).getTotalElements();
		
		Map<String, Long> dataMap = new HashMap<>();
		dataMap.put("inquiryCount", inquiryCount);
		dataMap.put("counselCount", counselCount);
		dataMap.put("followCount", followCount);
		
		Response response = new Response();
		
		//응답객체에 코드, 메시지, 객체 설정
		response.setStatus(ResponseStatusCode.READ_MEMBER_SUCCESS);
		response.setMessage(ResponseMessage.READ_MEMBER_SUCCESS);
		response.setData(dataMap);
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	/* 멘토 회원 활동 요약 */
	@Operation(summary = "멘토 활동 내역 요약")
	@SecurityRequirement(name = "BearerAuth")//API 엔드포인트가 인증을 요구한다는 것을 문서화(Swagger에서 JWT인증을 명시
	@GetMapping("/mentor-summary")
	public ResponseEntity<Response> getMentorSummary(
			Authentication authentication){
		
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		Long mentorNo = principalDetails.getMemberNo();
		
		Integer answerCount = (int)answerService.getAnswerByMember(mentorNo, 0, 10).getTotalElements();
		Integer counselCount = (int)chatRoomService.selectChatRoomByMentorNo(mentorNo,0,10).getTotalElements();
		Integer followCount = (int)followService.countFollower(mentorNo);
		Integer boardCount = (int)boardService.findByMember(mentorNo, 0, 10).getTotalElements();
		
		Map<String, Integer> dataMap = new HashMap<>();
		dataMap.put("answerCount", answerCount);
		dataMap.put("counselCount", counselCount);
		dataMap.put("followCount", followCount);
		dataMap.put("boardCount", boardCount);
		
		Response response = new Response();
		
		//응답객체에 코드, 메시지, 객체 설정
		response.setStatus(ResponseStatusCode.READ_MEMBER_SUCCESS);
		response.setMessage(ResponseMessage.READ_MEMBER_SUCCESS);
		response.setData(dataMap);
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	
	/* 회원 전체 출력 */
	@Operation(summary = "회원 전체 출력")
	@GetMapping()
	public ResponseEntity<Response> getMemberAll(
			@Parameter(name = "role", description = "필터링할 역할 (ROLE_MENTEE, ROLE_MENTOR)", required = true, example = "ROLE_MENTEE") 
			@RequestParam(name ="role") String role, 
			@Parameter(name = "order", description = "정렬 종류 (1: 가입 순, 2: 이름 순)", required = true, example = "1") 
			@RequestParam(name ="order") Integer order
			){
		
		List<MemberDto> memberList = memberService.getMemberAll(role, order);
		
		Response response = new Response();
		
		response.setStatus(ResponseStatusCode.READ_MEMBER_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_MEMBER_LIST_SUCCESS);
		response.setData(memberList);
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
		
		
		
	}
	
	
}
