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
import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.repository.bullentin_board.AnswerRepository;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.bullentin_board.AnswerService;
import com.itwill.jpa.service.bullentin_board.InquiryService;
import com.itwill.jpa.service.member_information.MemberService;
import com.itwill.jpa.service.member_information.MentorBoardService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/admin")
public class AdminSelectBoardController {
	
	@Autowired
	private InquiryService inquiryService;
	
	@Autowired 
	private AnswerService answerService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MentorBoardService mentorBoardService;

	/************* 질문 ************/
	/* (질문)게시글 전체출력 * */
	@Operation(summary = "질문 게시글 전체 출력(최신순)")
	@GetMapping("/Inquiry")
	public ResponseEntity<Response> getAdminInquiriesOrderByDate(
			@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
			@RequestParam(name = "size",defaultValue = "10") int size) {
		Page<InquiryDto> inquiryDtos = inquiryService.getByAllInquiryOrderByDate(page,size);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_INQUIRY_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_INQUIRY_LIST_SUCCESS);
		response.setData(inquiryDtos);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	/* 카테고리별 출력 */
	@Operation(summary = "질문을 최신 순으로 출력")
	@GetMapping("/date/{categoryNo}")
	public ResponseEntity<Response> getAdminCategoryInquiriesSortedByDate(@PathVariable(name = "categoryNo") Long categoryNo,
			@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
			@RequestParam(name = "size",defaultValue = "10") int size) {
			// 카테고리별 최신 순으로 질문 목록을 가져옴
		Page<InquiryDto> inquiryDtos = inquiryService.getByCategoryInquiryOrderByDate(categoryNo,page,size);
		 // 응답 객체 생성 및 상태 코드 설정
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_INQUIRY_LIST_SUCCESS);// 상태 코드: 성공
		response.setMessage(ResponseMessage.READ_INQUIRY_LIST_SUCCESS);// 메시지: 성공
		response.setData(inquiryDtos);// 데이터 설정
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		// 응답 반환
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	/* 검색 내용 별 출력 (inquiry)*/
	@Operation(summary = "질문 검색 기능")
	@GetMapping("/search/{search}")
	public ResponseEntity<Response> searchInquiriesForAdmin(@PathVariable(name = "search")String search
			,@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size) {
		// 검색어에 맞는 질문 목록을 가져옴
		Page<InquiryDto> inquiryDtos = inquiryService.getInquiryBySearch(search,page,size);
		// 응답 객체 생성 및 상태 코드 설정
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_INQUIRY_LIST_SUCCESS);// 상태 코드: 성공
		response.setMessage(ResponseMessage.READ_INQUIRY_LIST_SUCCESS+"검색 결과를 성공적으로 불러왔습니다.");// 더 명확한 메시지
		response.setData(inquiryDtos);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		// 응답 반환
	    ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	/* 삭제(상태변경) */
	@Operation(summary = "질문 삭제(상태변경)")
	@PutMapping("/delete/{inquiryNo}")
	public ResponseEntity<Response> deleteInquiryForAdmin(@PathVariable(name = "inquiryNo") Long inquiryNo) throws Exception {
	    // inquiryNo에 해당하는 질문을 삭제하는 서비스 호출
	    InquiryDto inquiryDto = inquiryService.deleteInquiry(inquiryNo);

	    // 응답 객체 생성
	    Response response = new Response();
	    response.setStatus(ResponseStatusCode.DELETE_INQUIRY_SUCCESS);
	    response.setMessage(ResponseMessage.DELETE_INQUIRY_SUCCESS);
	    response.setData(inquiryDto);

	    // HTTP 헤더 설정
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

	    // ResponseEntity로 반환
	    ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
	    return responseEntity;
	}
	
	/************* 답변 ***********
	@Operation(summary = "답변 게시글 번호로 출력(최신순)")
	@GetMapping("/answer/{inquiryNo}")
	public ResponseEntity<Response> getAdminAnswersOrderByDate(@PathVariable(name = "inquiryNo") Long inquiryNo,
	        @RequestParam(name = "page", defaultValue = "0") int page,  // 기본값은 0 페이지
	        @RequestParam(name = "size", defaultValue = "10") int size) {

	    // 전체 답변을 최신순으로 가져옴
	    Page<AnswerDto> answerDtos = answerService.getByInquiryAnswerOrderByDate(inquiryNo, page, size);

	    Response response = new Response();
	    response.setStatus(ResponseStatusCode.READ_ANSWER_LIST_SUCCESS);  // 예시로 성공 코드 설정
	    response.setMessage(ResponseMessage.READ_ANSWER_LIST_SUCCESS);     // 성공 메시지
	    response.setData(answerDtos);  // 데이터를 Response에 세팅

	    // HTTP 헤더 설정
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

	    // ResponseEntity로 반환
	    ResponseEntity<Response> responseEntity = new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
	    return responseEntity;
	}*/
	
	/************* 질문 ************
	 (멘토컨텐츠)게시글 번호로출력 수정 必
	@Operation(summary = "멘토 게시글 번호로 출력(최신순) 관리자용")
	@GetMapping("/admin/mentorBoards")
	public ResponseEntity<Response> getAdminMentorBoardsOrderByDate(@PathVariable(name= "mentorBoardNo") Long mentorBoardNo) {
        MentorBoardDto mentorBoard = mentorBoardService.getMemtorBoard(mentorBoardNo);

	    // 응답 객체 생성
	    Response response = new Response();
	    response.setStatus(ResponseStatusCode.READ_MENTOR_BOARD_LIST_SUCCESS);
	    response.setMessage(ResponseMessage.READ_MENTOR_BOARD_LIST_SUCCESS);
	    response.setData(mentorBoard);

	    // HTTP 헤더 설정
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

	    // ResponseEntity로 반환
	    ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
	    return responseEntity;
	}*/

	/************* 컨텐츠 ************/
	/* (멘토)게시글 전체출력 * */
	@Operation(summary = "멘토 게시글 최신순 목록")
	@GetMapping("/mentorBoards")
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
	
	@Operation(summary = "멘토 게시글 검색 기능")
	@GetMapping("/mentorBoards/search")
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
