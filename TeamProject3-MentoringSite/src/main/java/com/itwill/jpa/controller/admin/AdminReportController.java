package com.itwill.jpa.controller.admin;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.auth.PrincipalDetails;
import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.dto.report.ReportDto;
import com.itwill.jpa.repository.bullentin_board.AnswerRepository;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.bullentin_board.AnswerService;
import com.itwill.jpa.service.bullentin_board.InquiryService;
import com.itwill.jpa.service.member_information.MemberService;
import com.itwill.jpa.service.member_information.MentorBoardService;
import com.itwill.jpa.service.report.ReportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/admin/report")
public class AdminReportController {


	@Autowired
	private ReportService reportService;

	/************************* 신고 *******************************/

	/* 신고 출력(전체회원) */
	@SecurityRequirement(name = "BearerAuth")
	@PreAuthorize("hasRole('MENTEE')")
	@Operation(summary = "전체 신고 목록 조회")
	@GetMapping()
	public ResponseEntity<Response> getAdminReportList(Authentication authentication,
			@Parameter(name = "filter", description = "필터링 역할(1: 전체, 2: 신고접수 순)", required = true, example = "1")
	        @RequestParam(name = "filter") Integer filter,
	        @RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "10") int size) {
	    
	    // 신고 목록을 가져오는 서비스 메서드 호출
	    List<ReportDto> reports = reportService.getReportAll(filter, page, size); 

	    // 응답 객체 생성
	    Response response = new Response();
	    response.setStatus(ResponseStatusCode.READ_REPORT_LIST_SUCCESS);
	    response.setMessage(ResponseMessage.READ_REPORT_LIST_SUCCESS);
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

	    response.setData(reports);

	    // HTTP 헤더 설정
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

	    // ResponseEntity로 반환
	    ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
	    
	    return responseEntity;
	}

	
	/* 신고 상태 변경 (검토중, 완료 등) */
	@SecurityRequirement(name = "BearerAuth")
	@PreAuthorize("hasRole('MENTEE')")
	@Operation(summary = "신고상태 변경")
	@PutMapping("{reportNo}/status")
	public ResponseEntity<Response> updateReportStatusForAdmin(
	        @PathVariable(name = "reportNo") Long reportNo,
	        @Parameter(name = "status", description = "변경할 상태(IN_PROGRESS, RESOLVED, FALSE_REPORT)", required = true, example = "IN_PROGRESS")
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
	    }

	    // 응답 객체 생성
	    Response response = new Response();
	    response.setStatus(ResponseStatusCode.UPDATE_REPORT_SUCCESS);
	    response.setMessage("신고 상태가 '" + status + "'으로 변경되었습니다.");
	    response.setData(updatedReport);

	    // HTTP 헤더 설정
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));

	    // ResponseEntity로 반환
	    ResponseEntity<Response> responseEntity = new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

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



	

}
