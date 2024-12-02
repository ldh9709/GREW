package com.itwill.jpa.controller.report;


import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.controller.Response;
import com.itwill.jpa.controller.ResponseMessage;
import com.itwill.jpa.controller.ResponseStatusCode;
import com.itwill.jpa.dto.report.ReportDto;
import com.itwill.jpa.service.ReportService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/report")
public class ReportRestController {
	
	@Autowired
	private ReportService reportService;
	
	/* 신고등록 */
	@Operation(summary = "신고 등록")
	@PostMapping
	public ResponseEntity<Response> insertReport(@RequestBody ReportDto reportDto){
	    
	    // 1. 서비스 호출: 신고 데이터를 저장
		reportService.saveReport(reportDto);
		
		// 2. 응답 데이터(Response 객체) 생성
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CREATED_REPORT_SUCCESS);
		response.setMessage(ResponseMessage.CREATED_REPORT_SUCCESS);
		response.setData(response);
		
		 // 3. 응답 헤더 설정
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		// 4. ResponseEntity 생성
	    // - HTTP 상태 코드와 헤더, 응답 데이터를 포함한 ResponseEntity를 반환합니다.
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.CREATED);
		
		return responseEntity;
	}
	/* [어드민] 신고 상태변경(검토중) */
	
	/* [어드민] 신고 상태변경(처리완료) */
	
	/* 신고 출력(특정회원) */
	
	/* [어드민] 신고 출력(전체회원) */

}
