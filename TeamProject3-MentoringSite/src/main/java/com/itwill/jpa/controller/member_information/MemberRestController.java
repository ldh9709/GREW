package com.itwill.jpa.controller.member_information;

import java.nio.charset.Charset;

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
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.member_information.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/member")
public class MemberRestController {
	@Autowired
	private MemberService memberService;
	
	
	/* 회원 저장 */
	@Operation(summary = "회원가입")
	@PostMapping
	public ResponseEntity<Response> saveMember(@RequestBody MemberDto memberDto) {
		
		//저장메소드 실행
		Member saveMember = memberService.saveMember(memberDto);
		
		//응답 객체 생성
		Response response = new Response();
		
		if(saveMember != null) {
			//응답객체에 코드, 메시지, 객체 설정
			response.setStatus(ResponseStatusCode.CREATED_MEMBER_SUCCESS);
			response.setMessage(ResponseMessage.CREATED_MEMBER_SUCCESS);
			response.setData(saveMember);
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
	
	/* 회원 로그인 */
	@Operation(summary = "회원 로그인")
	@PostMapping("/login")
	public ResponseEntity<Response> loginMember(@RequestBody MemberDto memberDto, HttpSession session) {
		
		//로그인 메소드 실행
		Member loginMember = memberService.loginMember(memberDto.getMemberId(), memberDto.getMemberPassword());
		
		//(임시) 세션에 등록 => 추후 토큰으로 변경 예정
		session.setAttribute("loginMember", loginMember);
		
		//응답 객체 생성
		Response response = new Response();
		
		if(loginMember != null) {
			//응답객체에 코드, 메시지, 객체 설정
			response.setStatus(ResponseStatusCode.LOGIN_MEMBER_SUCCESS);
			response.setMessage(ResponseMessage.LOGIN_MEMBER_SUCCESS);
			response.setData(loginMember);
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
	
	/* 회원 로그아웃 */
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
	
	/* 회원 수정 */
	@Operation(summary = "회원 정보 수정")
	@PutMapping("/{memberNo}")
	public ResponseEntity<Response> updateMember(@RequestBody MemberDto memberDto) {
		
		//업데이트 메소드 실행
		Member updateMember = memberService.updateMember(memberDto);
		
		Response response = new Response();
		
		if(updateMember != null) {
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
	
}
