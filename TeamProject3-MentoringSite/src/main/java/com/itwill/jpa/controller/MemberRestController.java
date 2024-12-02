package com.itwill.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.user_information.MemberDto;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.service.MemberService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/member")
public class MemberRestController {
	@Autowired
	private MemberService memberService;
	
	public ResponseEntity<Response> saveMember(@RequestBody MemberDto memberDto) {
		
	}

}
