package com.itwill.jpa.controller.member_information;

import java.nio.charset.Charset;
import java.security.Principal;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.auth.PrincipalDetails;
import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.dto.member_information.MemberJoinDto;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.member_information.EmailService;
import com.itwill.jpa.service.member_information.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/member")
public class MemberRestController {
	@Autowired
	private MemberService memberService;
	
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
	public ResponseEntity<Response> createMember(@RequestBody MemberJoinDto memberJoinDto) {
		
		MemberDto memberDto = memberJoinDto.getMemberDto();
		System.out.println("MEMBERDTO : >>> " + memberDto);
		Integer tempCode = memberJoinDto.getTempCode();
		System.out.println("TEMPCODE : >>>" + tempCode);
		
		Response response = new Response();
		
		
		//인증번호가 맞는지 확인
		boolean isChecked = memberService.certificationCode(memberDto.getMemberEmail(), tempCode);
		
		System.out.println("isChecked : " + isChecked);
		if(!isChecked) {
			response.setMessage("인증번호가 일치하지 않습니다.");
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//반환할 응답Entity 생성
			ResponseEntity<Response> responseEntity =
					 new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
			
			return responseEntity;
		}
		
		//저장메소드 실행
		Member saveMember = memberService.saveMember(memberDto);
		
		MemberDto saveMemberDto = MemberDto.toDto(saveMember);
		
		//응답 객체 생성
		
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
		
		System.out.println("로그인 시도 : " + response);
		
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
//	/* 회원 로그아웃 */
//	@Operation(summary = "회원 로그아웃")
//	@GetMapping("/logout")
//	public ResponseEntity<Response> logoutMember(HttpSession session) {
//		
//		//세션 제거
//		session.invalidate();
//		
//		Response response = new Response();
//		response.setStatus(ResponseStatusCode.LOGOUT_MEMBER_SUCCESS);
//		response.setMessage(ResponseMessage.LOGOUT_MEMBER_SUCCESS);
//		
//		HttpHeaders httpHeaders=new HttpHeaders();
//		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
//		
//		ResponseEntity<Response> responseEntity = 
//				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
//		
//		return responseEntity;
//	}
	
	/* 회원 정보 보기 */
	@Operation(summary = "회원 정보 보기")
	@GetMapping("/{memberNo}")
	public ResponseEntity<Response> getMember(@PathVariable(name = "memberNo") Long memberNo, HttpSession session) {
		
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
	
	/***** 회원 정보 보기(토큰) *****/
	@Operation(summary = "회원 정보 보기(토큰)")
	@SecurityRequirement(name = "BearerAuth")//API 엔드포인트가 인증을 요구한다는 것을 문서화(Swagger에서 JWT인증을 명시
	@PreAuthorize("hasRole('MENTEE')")//ROLE이 MENTEE인 사람만 접근 가능
	@GetMapping("/profile")
	public ResponseEntity<Response> getMember(Authentication authentication) {
		
		//PrincipalDetails에서 memberNo를 가져옴
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		Long memberNo = principalDetails.getMemberNo();
		
		System.out.println(">>>>> getAuthorities : " + authentication.getAuthorities());
		System.out.println(">>>>> authentica  tion : " + authentication);
		System.out.println(">>>>> authentication.getName() : " + authentication.getName());
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
	
	
	/* 회원 수정 */
	@Operation(summary = "회원 정보 수정")
	@PutMapping
	public ResponseEntity<Response> updateMember(@RequestBody MemberDto memberDto) {
		
//		Authentication authentication =	SecurityContextHolder.getContext().getAuthentication();
//		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//		
//		Long memberNo = principalDetails.getMemberNo();
//		
//		//클라이언트에서 보낸 데이터 무시하고 인증된 사용자 정보로 덮어씀(생략가능, 명시적으로 입력)
//		memberDto.setMemberNo(memberNo);
		
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
	
	/***** 회원 수정(토큰) *****/
	@Operation(summary = "회원 정보 수정(토큰)")
	@SecurityRequirement(name = "BearerAuth")
	@PutMapping("/modify")
	public ResponseEntity<Response> updateMemberByToken(@RequestBody MemberDto memberDto) {
		
		//인증객체 가져옴
		Authentication authentication =	SecurityContextHolder.getContext().getAuthentication();
		System.out.println(">>>>> updateMemberByToken authentication : " + authentication);
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		System.out.println(">>>>> updateMemberByToken principalDetails : " + principalDetails);
		
		Long memberNo = principalDetails.getMemberNo();
		
		//클라이언트에서 보낸 데이터 무시하고 인증된 사용자 정보로 덮어씀(생략가능, 명시적으로 입력)
		memberDto.setMemberNo(memberNo);
		
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
	
	/* 회원 수정 */
	@Operation(summary = "회원 상태 수정")
	@PutMapping("/{memberNo}/status/{statusNo}")
	public ResponseEntity<Response> updateMemberStatus(
			@RequestBody MemberDto memberDto, 
			@PathVariable(name = "statusNo") Integer statusNo) {
		
		//업데이트 메소드 실행
		Member updateMember = memberService.updateMemberStatus(memberDto, statusNo);
		
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
	
	/***** 이메일 발송 (테스트) *****/
	@Operation(summary = "비밀번호 찾기")
	@PostMapping("/findPassword")
	public ResponseEntity findPassword(@RequestBody MemberDto.findPassword memberDto) {
		memberService.findPassword(memberDto);
		return ResponseEntity.status(HttpStatus.OK).body("ok");
	}
	
	
}
