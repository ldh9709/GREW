package com.itwill.jpa.controller.member_information;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.bullentin_board.AnswerService;
import com.itwill.jpa.service.bullentin_board.InquiryService;
import com.itwill.jpa.service.chatting_review.ChatRoomService;
import com.itwill.jpa.service.chatting_review.ReviewService;
import com.itwill.jpa.service.member_information.FollowService;
import com.itwill.jpa.service.member_information.MemberService;
import com.itwill.jpa.service.member_information.MentorBoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/member")
public class MemberRestController {
	
	@Autowired
	private MemberService memberService;
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
	
	
	/* 회원 가입(멘티) */
	@Operation(summary = "회원가입(멘티)")
	@PostMapping
	public ResponseEntity<Response> saveMember(@RequestBody MemberDto memberDto) {
		
		//저장메소드 실행
		Member saveMember = memberService.saveMember(memberDto);
		
		MemberDto saveMemberDto = MemberDto.toDto(saveMember);
		
		//응답 객체 생성
		Response response = new Response();
		
		if(saveMemberDto != null) {
			//응답객체에 코드, 메시지, 객체 설정
			response.setStatus(ResponseStatusCode.CREATED_MEMBER_SUCCESS);
			response.setMessage(ResponseMessage.CREATED_MEMBER_SUCCESS);
			response.setData(saveMemberDto);
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
	
	/* 회원 로그인
	@Operation(summary = "회원 로그인")
	@PostMapping("/login")
	public ResponseEntity<Response> loginMember(
			@RequestBody MemberDto memberDto
			, HttpSession session) {
		
		//로그인 메소드 실행
		Member loginMember = memberService.loginMember(memberDto.getMemberId(), memberDto.getMemberPassword());
		
		//DTO로 변경
		MemberDto loginMemberDto = MemberDto.toDto(loginMember);
		
		//(임시) 세션에 등록 => 추후 토큰으로 변경 예정
		session.setAttribute("loginMember", loginMemberDto);
		
		//응답 객체 생성
		Response response = new Response();
		
		if(loginMemberDto != null) {
			//응답객체에 코드, 메시지, 객체 설정
			response.setStatus(ResponseStatusCode.LOGIN_MEMBER_SUCCESS);
			response.setMessage(ResponseMessage.LOGIN_MEMBER_SUCCESS);
			response.setData(loginMemberDto);
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
	 */
	
	/* 회원 로그아웃 
	@Operation(summary = "회원 로그아웃")
	@GetMapping("/logout")
	public ResponseEntity<Response> logoutMember(HttpSession session) {
		
		//세션 제거
		session.invalidate();
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.LOGOUT_MEMBER_SUCCESS);
		response.setMessage(ResponseMessage.LOGOUT_MEMBER_SUCCESS);
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	*/
	
	/* 회원 정보 수정 */
	@Operation(summary = "회원 정보 수정")
	@PutMapping("/{memberNo}")
	public ResponseEntity<Response> updateMember(
			@RequestBody MemberDto memberDto,
			@PathVariable("memberNo") Long memberNo
			) {
		
		System.out.println(memberDto);
		
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
	
	/* 회원 정보 보기 */
	@Operation(summary = "회원 정보 상세보기")
	@GetMapping("/{memberNo}")
	public ResponseEntity<Response> getMember(@PathVariable(name = "memberNo") Long memberNo) {
		
		//번호로 멤버 객체 찾기
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
	
	/* 멘티 회원 활동 요약 */
	@Operation(summary = "멘티 활동 내역 요약")
	@GetMapping("/mentee-summary/{menteeNo}")
	public ResponseEntity<Response> getMenteeSummary(
			@PathVariable(name ="menteeNo") Long menteeNo){
		
		Integer inquiryCount = (int)inquiryService.getInquiryByMember(menteeNo, 0, 10).getTotalElements();
		Integer counselCount = (int)chatRoomService.selectChatRoomAll(menteeNo).size();
		Integer followCount = (int)followService.getMentorList(menteeNo, 0, 10).getTotalElements();
		
		Map<String, Integer> dataMap = new HashMap<>();
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
	@GetMapping("/mentor-summary/{mentorNo}")
	public ResponseEntity<Response> getMentorSummary(
			@PathVariable(name ="mentorNo") Long mentorNo){
		
		Integer answerCount = (int)answerService.getAnswerByMember(mentorNo, 0, 10).getTotalElements();
		Integer counselCount = (int)chatRoomService.selectChatRoomAll(mentorNo).size();
		Integer followCount = (int)followService.countFollower(mentorNo);
		Integer borardCount = (int)boardService.findByMember(mentorNo, 0, 10).getTotalElements();
		
		Map<String, Integer> dataMap = new HashMap<>();
		dataMap.put("answerCount", answerCount);
		dataMap.put("counselCount", counselCount);
		dataMap.put("followCount", followCount);
		dataMap.put("borardCount", borardCount);
		
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
