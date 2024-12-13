package com.itwill.jpa.controller.admin;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.itwill.jpa.dto.report.ReportDto;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.report.ReportService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/admin")
public class AdminSelectReportController {
	
	@Autowired
	private ReportService reportService;
	
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
	@PutMapping("/report/{reportNo}/status")
	public ResponseEntity<Response> updateReportStatusForAdmin(
	        @PathVariable(name = "reportNo") Long reportNo,
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
	    httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));

	    // ResponseEntity로 반환
	    ResponseEntity<Response> responseEntity = new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

	    return responseEntity;
	}


}
