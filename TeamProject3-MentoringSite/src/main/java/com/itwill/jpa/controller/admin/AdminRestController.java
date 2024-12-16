package com.itwill.jpa.controller.admin;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.bullentin_board.AnswerService;
import com.itwill.jpa.service.bullentin_board.AnswerServiceImpl;
import com.itwill.jpa.service.bullentin_board.InquiryService;
import com.itwill.jpa.service.member_information.MemberService;
import com.itwill.jpa.service.member_information.MentorBoardService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
	
	@Autowired
    private MentorBoardService mentorBoardService;
	
	

		/* (멘토컨텐츠)게시글 전체출력 =>method추가시*/
	
	/* 검색내용 별 출력 (고려)=>List<MentorBoardDto> findMentorBoardBySearch(String search);*/
	
	/* 번호로 보기 수정 必
	@Operation(summary = "관리자 전용 회원 정보 보기 - MENTEE")
	@GetMapping("/{memberNo}")
	public ResponseEntity<Response> getAdminMentee(@PathVariable(name = "memberNo") Long memberNo) {
	    // 번호로 멤버 객체 찾기
	    Member member = memberService.getMember(memberNo);

	    // MENTEE일 경우 DTO 객체로 변환
	    MemberDto memberDto = MemberDto.toDto(member);
	    
	    // 응답 객체 설정
	    Response response = new Response();
	    response.setStatus(ResponseStatusCode.READ_MEMBER_SUCCESS);
	    response.setMessage("MENTEE 회원 정보 조회 성공");
	    response.setData(memberDto);

	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

	    return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
	}*/
	
	
}