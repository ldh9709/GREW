package com.itwill.jpa.controller.admin;

import java.nio.charset.Charset;
import java.util.List;

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
<<<<<<< HEAD
import com.itwill.jpa.dto.report.ReportDto;
=======
import com.itwill.jpa.entity.member_information.Member;
>>>>>>> refs/heads/master
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.bullentin_board.AnswerService;
import com.itwill.jpa.service.bullentin_board.AnswerServiceImpl;
import com.itwill.jpa.service.bullentin_board.InquiryService;
import com.itwill.jpa.service.member_information.MemberService;
import com.itwill.jpa.service.member_information.MemtorBoardService;
import com.itwill.jpa.service.report.ReportService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
	
	@Autowired
	private InquiryService inquiryService;
	
	@Autowired
	private AnswerService answerService;

	@Autowired
    private MemtorBoardService mentorBoardService;
	
	@Autowired
<<<<<<< HEAD
	private ReportService reportService;
=======
	private MemberService memberService;
>>>>>>> refs/heads/master
	
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
	@GetMapping("/MentorBoards")
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
	
<<<<<<< HEAD
	/* [어드민] 신고 출력(전체회원) */
	@Operation(summary = "[어드민] 전체 신고 목록 조회")
	@GetMapping("/Reports")
	public ResponseEntity<Response> getAdminReportList(
	        @RequestParam(name = "filter") Integer filter,
	        @RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "10") int size) {
	    
	    // 신고 목록을 가져오는 서비스 메서드 호출
	    List<ReportDto> reports = reportService.getReportAll(filter, page, size); 

	    // 응답 객체 생성
	    Response response = new Response();
	    response.setStatus(ResponseStatusCode.READ_REPORT_LIST_SUCCESS);
	    response.setMessage(ResponseMessage.READ_REPORT_LIST_SUCCESS);
	    response.setData(reports);

	    // HTTP 헤더 설정
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

	    // ResponseEntity로 반환
	    ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
	    
	    return responseEntity;
	}

	/* [어드민] 신고 상태 변경 (검토중, 완료 등) */
	@Operation(summary = "관리자 신고 상태 변경")
	@PutMapping("/Reports/{report_no}/status")
	public ResponseEntity<Response> updateReportStatusForAdmin(
	        @PathVariable(name = "report_no") Long reportNo,
	        @RequestParam(name = "status") String status) {
	    
	    // 신고 상태 변경: 상태에 따라 '검토중', '완료' 등으로 설정
		ReportDto updatedReport = null;

	    // 상태에 따른 로직 분기
	    switch (status.toUpperCase()) {
	        case "IN_PROGRESS":
	            // 신고 상태 '접수중'으로 변경
	            updatedReport = reportService.updateReportStatusToInProgress(reportNo);
	            break;
	        case "RESOLVED":
	            // 신고 상태 '처리완료'로 변경
	            updatedReport = reportService.updateReportStatusToResolved(reportNo);
	            break;
	        case "FALSE_REPORT":
	            // 신고 상태 '무고처리'로 변경
	            updatedReport = reportService.updateReportStatusToFalseReport(reportNo);
	            break;
	        default:
	            // 올바르지 않은 상태 값이 들어왔을 경우 예외 처리
	            throw new IllegalArgumentException("알 수 없는 상태 값입니다: " + status);
	    }

	    // 응답 객체 생성
	    Response response = new Response();
	    response.setStatus(ResponseStatusCode.UPDATE_REPORT_SUCCESS);
	    response.setMessage("신고 상태가 '" + status + "'으로 변경되었습니다.");
	    response.setData(updatedReport);

	    // HTTP 헤더 설정
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

	    // ResponseEntity로 반환
	    ResponseEntity<Response> responseEntity = new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

	    return responseEntity;
	}


	
}
