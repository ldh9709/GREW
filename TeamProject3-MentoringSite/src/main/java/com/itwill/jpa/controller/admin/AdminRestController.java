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
import com.itwill.jpa.service.member_information.MemtorBoardService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
	
	@Autowired
	private InquiryService inquiryService;
	
	@Autowired
	private  AnswerService answerService;

	@Autowired
    private MemtorBoardService mentorBoardService;
	
	@Autowired
	private MemberService memberService;
	
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
	@Operation(summary = "관리자가 카테고리별 최신 순으로 질문을 출력")
	@GetMapping("/Date/{categoryNo}")
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
	@Operation(summary = "관리자 질문 검색 기능")
	@GetMapping("/Search/{search}")
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
	  
	/* 검색 내용 별 출력 (answer)
	@Operation(summary = "관리자 답변 검색 기능")
	@GetMapping("/answerSearch/{search}")
	public ResponseEntity<Response> searchAnswersForAdmin(@PathVariable(name = "search") String search,
	        @RequestParam(name = "page", defaultValue = "0") int page,  // 기본값은 0 페이지
	        @RequestParam(name = "size", defaultValue = "10") int size) {
	    
	    // 검색어에 맞는 답변 목록을 가져옴
	    Page<AnswerDto> answerDtos = answerService.getByInquiryAnswerOrderByDate(null, page, size);
	    Response response = new Response();
		HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		 // 응답 반환
		ResponseEntity<Response> responseEntity = new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
		return responseEntity;
	    		
	}*/
	
	/* 삭제(상태변경) */
	@Operation(summary = "관리자 질문 삭제")
	@PutMapping("/Delete/{inquiryNo}")
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

	/* (멘토컨텐츠)게시글 번호로출력 수정 必*/
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
	}	/* (멘토컨텐츠)게시글 전체출력 =>method추가시*/
	
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