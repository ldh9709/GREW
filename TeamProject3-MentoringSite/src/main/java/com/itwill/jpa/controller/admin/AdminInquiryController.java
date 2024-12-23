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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.bullentin_board.InquiryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/admin/inquiry")
public class AdminInquiryController {
	
	@Autowired
	private InquiryService inquiryService;
	
	/************************* 질문 *******************************/
	
	/* (질문)게시글 전체출력 * */
	@SecurityRequirement(name = "BearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "질문 게시글 전체 조회(최신순)")
	@GetMapping("/board")
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
	@SecurityRequirement(name = "BearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "질문 게시글 전체 조회 (카테고리별, 최신 순)")
	@GetMapping("/category/{categoryNo}")
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
	
	/* 검색 내용 별 출력*/
	@SecurityRequirement(name = "BearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "질문 게시글 조회(검색어)")
	@GetMapping("/search/{search}")
	public ResponseEntity<Response> searchInquiriesForAdmin(@PathVariable(name = "search")String search
			,@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size) {
		
		// 검색어에 맞는 질문 목록을 가져옴
		Page<InquiryDto> inquiryDtos = inquiryService.getInquiryBySearch(search,page,size);
		// 응답 객체 생성 및 상태 코드 설정
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_INQUIRY_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_INQUIRY_LIST_SUCCESS);
		response.setData(inquiryDtos);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		// 응답 반환
	    ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	/* 삭제(상태변경) */
	@SecurityRequirement(name = "BearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "질문 삭제")
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
}
