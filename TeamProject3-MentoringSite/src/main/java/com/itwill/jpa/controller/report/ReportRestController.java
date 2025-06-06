package com.itwill.jpa.controller.report;


import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.dto.report.ReportDto;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.alarm.AlarmService;
import com.itwill.jpa.service.report.ReportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/report")
public class ReportRestController {
	
	@Autowired
	private ReportService reportService;
	@Autowired
	private AlarmService alarmService;
	
	/* 신고등록 */
	@Operation(summary = "신고 등록")
	@SecurityRequirement(name = "BearerAuth")//API 엔드포인트가 인증을 요구한다는 것을 문서화(Swagger에서 JWT인증을 명시
	@PreAuthorize("hasRole('MENTEE') or hasRole('MENTOR') or hasRole('ADMIN')" )//ROLE이 MENTEE인 사람만 접근 가능
	@PostMapping
	public ResponseEntity<Response> createReport(@RequestBody ReportDto reportDto){
	    
	    // 1. 서비스 호출: 신고 데이터를 저장
		reportService.createReport(reportDto);
		
		// 2. 응답 데이터(Response 객체) 생성
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CREATED_REPORT_SUCCESS);
		response.setMessage(ResponseMessage.CREATED_REPORT_SUCCESS);
		response.setData(reportDto);
		
		 // 3. 응답 헤더 설정
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		// 4. ResponseEntity 생성
	    // - HTTP 상태 코드와 헤더, 응답 데이터를 포함한 ResponseEntity를 반환합니다.
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	/* [어드민] 신고 상태변경(검토중)
	@Operation(summary = "신고 상태 '검토중'으로 변경")
	@PutMapping("{report_no}/in-progress")
	public ResponseEntity<Response> updateReportStatusToInPorgress(@PathVariable (value="report_no") Long reportNo){
		
		reportService.updateReportStatusToInProgress(reportNo);
		Response response = new Response();
		response.setStatus(ResponseStatusCode.UPDATE_REPORT_SUCCESS);
		response.setMessage(ResponseMessage.UPDATE_REPORT_SUCCESS);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	*/
	
	/* [어드민] 신고 상태변경(처리완료) */
	@Operation(summary = "신고 상태 '처리완료'로 변경")
	@PutMapping("{report_no}/resolved")
	public ResponseEntity<Response> updateReportStatusToResolved(@PathVariable (value="report_no") Long reportNo) {
		reportService.updateReportStatusToResolved(reportNo);
		AlarmDto alarmDto = alarmService.createAlarmByReport(reportNo);
		Response response = new Response();
		response.setStatus(ResponseStatusCode.UPDATE_REPORT_SUCCESS);
		response.setMessage(ResponseMessage.UPDATE_REPORT_SUCCESS);
		response.setAddData(alarmDto);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	/* [어드민] 신고 상태변경(무고처리) */
	@Operation(summary = "신고 상태 '무고처리'로 변경")
	@PutMapping("{report_no}/false-report")
	public ResponseEntity<Response> updateReportStatusToFalseReport(@PathVariable (value="report_no") Long reportNo) {
		reportService.updateReportStatusToFalseReport(reportNo);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.UPDATE_REPORT_SUCCESS);
		response.setMessage(ResponseMessage.UPDATE_REPORT_SUCCESS);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	/* 신고 출력(1개) */
	@Operation(summary = "특정 신고 상세정보 조회")
	@GetMapping("/detail/{report_no}")
	public ResponseEntity<Response> getReportByReportNo(@PathVariable(value = "report_no") Long reportNo){
		ReportDto report = reportService.getReportByreportNo(reportNo);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_REPORT_SUCCESS);
		response.setMessage(ResponseMessage.READ_REPORT_SUCCESS);
		response.setData(report);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}

	/* [어드민] 신고 출력(전체회원) */
	@Operation(summary = "[어드민] 전체 신고 목록 조회")
	@GetMapping()
	public ResponseEntity<Response> getReportAll(
			@Parameter(name = "filter", description = "필터링 역할(1: 전체, 2: 신고접수 순)", required = true, example = "1")
			@RequestParam(name="filter") Integer filter,
			@RequestParam(name = "page", defaultValue ="0") int page,
			@RequestParam(name = "size", defaultValue ="10") int size){
		Page<ReportDto> reports = reportService.getReportAll(filter, page, size);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_REPORT_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_REPORT_LIST_SUCCESS);
		response.setData(reports);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}

}
