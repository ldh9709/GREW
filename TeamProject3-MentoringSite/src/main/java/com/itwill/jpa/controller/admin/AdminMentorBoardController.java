package com.itwill.jpa.controller.admin;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.member_information.MentorBoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/admin/mentor-board")
public class AdminMentorBoardController {
	
	@Autowired
	private MentorBoardService mentorBoardService;
	
	/************************* 멘토 보드(컨텐츠) *******************************/

	/* (멘토)게시글 전체출력 * */
	@SecurityRequirement(name = "BearerAuth")
	@PreAuthorize("hasRole('MENTEE')")
	@Operation(summary = "멘토 게시글 전체 조회(최신순)")
	@GetMapping()
	public ResponseEntity<Response> getAdminMentorBoardsForAdmin(
	        @RequestParam(name = "page", defaultValue = "0") int page,  // 기본값은 0 페이지
	        @RequestParam(name = "size", defaultValue = "10") int size) { // 기본값은 10 개 항목

	    // 게시글 목록을 최신순으로 가져오기
	    Page<MentorBoardDto> mentorBoardsPage = mentorBoardService.getMentorBoardsSortedByDate(page, size);

	    // 응답 객체 생성
	    Response response = new Response();
	    response.setStatus(ResponseStatusCode.READ_MENTOR_BOARD_LIST_SUCCESS);
	    response.setMessage(ResponseMessage.READ_MENTOR_BOARD_LIST_SUCCESS);
	    response.setData(mentorBoardsPage); // 여러 게시글 목록을 응답 데이터로 설정

	    // HTTP 헤더 설정
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

	    // ResponseEntity로 반환
	    return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
	}
	
	@SecurityRequirement(name = "BearerAuth")
	@PreAuthorize("hasRole('MENTEE')")
	@Operation(summary = "멘토 게시글 조회(검색어)")
	@GetMapping("/search/{search}")
	public ResponseEntity<Response> getAdminMentorBoardsForAdminWithSearch(
	        @RequestParam(name = "search", required = false, defaultValue = "") String search,  // 검색어 (기본값은 빈 문자열)
	        @RequestParam(name = "page", defaultValue = "0") int page,  // 기본값은 0 페이지
	        @RequestParam(name = "size", defaultValue = "10") int size) { // 기본값은 10 개 항목

	    // 게시글 목록을 검색어와 함께 가져오기
	    Page<MentorBoardDto> mentorBoardsPage = mentorBoardService.findMentorBoardBySearch(search, page, size);

	    // 응답 객체 생성
	    Response response = new Response();
	    response.setStatus(ResponseStatusCode.READ_MENTOR_BOARD_LIST_SUCCESS);
	    response.setMessage(ResponseMessage.READ_MENTOR_BOARD_LIST_SUCCESS);
	    response.setData(mentorBoardsPage); // 검색된 게시글 목록을 응답 데이터로 설정

	    // HTTP 헤더 설정
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

	    // ResponseEntity로 반환
	    return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
	}
}
