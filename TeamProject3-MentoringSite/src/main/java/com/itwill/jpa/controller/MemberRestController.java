package com.itwill.jpa.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.user_information.MemberDto;
import com.itwill.jpa.entity.user_information.Member;
import com.itwill.jpa.service.MemberService;

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
		
		Member loginMember = memberService.loginMember(memberDto.getMemberId(), memberDto.getMemberPassword());
		
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
		Member loginMember = memberService.getMember(memberNo);
		
		Response response = new Response();
		
		if(loginMember != null) {
			//응답객체에 코드, 메시지, 객체 설정
			response.setStatus(ResponseStatusCode.LOGIN_MEMBER_SUCCESS);
			response.setMessage(ResponseMessage.LOGIN_MEMBER_SUCCESS);
			response.setData(loginMember);
		}
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	
	
}
