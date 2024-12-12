package com.itwill.jpa.controller.bullentin_board;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.bullentin_board.InquiryService;
import com.itwill.jpa.util.ClientIp;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/inquiry")
public class InquiryRestController {
	@Autowired
	private InquiryService inquiryService;

	// 질문등록
	@Operation(summary = "질문 등록")
	@PostMapping
	public ResponseEntity<Response> createInquiry(@RequestBody InquiryDto inquiryDto) {
		
		Response response = new Response();
		
		InquiryDto createInguiryDto = inquiryService.createInquiry(inquiryDto);
		response.setStatus(ResponseStatusCode.CREATED_INQUIRY_SUCCESS);
		response.setMessage(ResponseMessage.CREATED_INQUIRY_SUCCESS);
		response.setData(createInguiryDto);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);

		return responseEntity;
	}

	// 질문수정
	@Operation(summary = "질문 수정")
	@PutMapping("/update/{inquiryNo}")
	public ResponseEntity<Response> updateInquiry(@PathVariable(name = "inquiryNo") Long inquiryNo,@RequestBody InquiryDto inquiryDto) throws Exception {
		inquiryDto.setInquiryNo(inquiryNo);
		InquiryDto updateInquiryDto = inquiryService.updateInquiry(inquiryDto);
		Response response = new Response();
		response.setStatus(ResponseStatusCode.UPDATE_INQUIRY_SUCCESS);
		response.setMessage(ResponseMessage.UPDATE_INQUIRY_SUCCESS);
		response.setData(updateInquiryDto);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.OK);

		return responseEntity;
	}

	// 질문삭제
	@Operation(summary = "질문삭제")
	@PutMapping("/delete/{inquiryNo}")
	public ResponseEntity<Response> deleteInquiry(@PathVariable(name = "inquiryNo") Long inquiryNo) throws Exception {
		;
		InquiryDto inquiryDto = inquiryService.deleteInquiry(inquiryNo);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.DELETE_INQUIRY_SUCCESS);
		response.setMessage(ResponseMessage.DELETE_INQUIRY_SUCCESS);
		response.setData(inquiryDto);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.OK);

		return responseEntity;
	}

	// 질문보기
	@Operation(summary = "질문보기")
	@GetMapping("/view/{inquiryNo}")
	public ResponseEntity<Response> viewInquiry(@PathVariable(name = "inquiryNo") Long inquiryNo) {
		Response response = new Response();

		InquiryDto inquiryDto = inquiryService.getInquiry(inquiryNo);
		response.setStatus(ResponseStatusCode.READ_INQUIRY_SUCCESS);
		response.setMessage(ResponseMessage.READ_INQUIRY_SUCCESS);
		response.setData(inquiryDto);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.OK);

		return responseEntity;

	}

	// 조회수 증가
	@Operation(summary = "조회수증가")
	@PutMapping("/increase/{inquiryNo}")
	public ResponseEntity<Response> increaseViewInquiry(@PathVariable(name = "inquiryNo") Long inquiryNo,HttpServletRequest httpServletRequest) throws Exception {
		Response response = new Response();
		String clientIp = new ClientIp().getClientIp(httpServletRequest);
		InquiryDto inquiryDto = inquiryService.increaseViewInquiry(inquiryNo,clientIp);
		response.setStatus(ResponseStatusCode.INCREASE_VIEW_INQUIRY_SUCCESS);
		response.setMessage(ResponseMessage.INCREASE_VIEW_INQUIRY_SUCCESS);
		response.setData(inquiryDto);
		response.setAddData(clientIp);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.OK);

		return responseEntity;
	}
	@Operation(summary = "답변수 많은 순으로 카테고리별 질문 출력")
	@GetMapping("/{categoryNo}/answer-count")
	public ResponseEntity<Response> getByCategoryInquiryOrderByAnswer(@PathVariable(name = "categoryNo") Long categoryNo,
			@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size) {
	    Page<InquiryDto> inquiryDtos = inquiryService.getByCategoryInquiryOrderByAnswer(categoryNo,page,size);
	    
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
	@Operation(summary = "조회수 많은 순으로 카테고리별 질문 출력")
	@GetMapping("/{categoryNo}/view-count")
	public ResponseEntity<Response> getByCategoryInquiryOrderByView(@PathVariable(name = "categoryNo") Long categoryNo,
			@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size) {
		Page<InquiryDto> inquiryDtos = inquiryService.getByCategoryInquiryOrderByView(categoryNo,page,size);
		
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
	@Operation(summary = "최신 순으로 카테고리별 질문 출력")
	@GetMapping("/{categoryNo}/date")
	public ResponseEntity<Response> getByCategoryInquiryOrderByDate(@PathVariable(name = "categoryNo") Long categoryNo,
			@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
			@RequestParam(name = "size",defaultValue = "10") int size) {
		Page<InquiryDto> inquiryDtos = inquiryService.getByCategoryInquiryOrderByDate(categoryNo,page,size);
		
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
	@Operation(summary = "답변수 많은 순으로 전체 질문 출력")
	@GetMapping("/answer-count")
	public ResponseEntity<Response> getByAllInquiryOrderByAnswer(
			@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size) {
		Page<InquiryDto> inquiryDtos = inquiryService.getByAllInquiryOrderByAnswer(page,size);
		
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
	@Operation(summary = "조회수 많은 순으로 전체 질문 출력")
	@GetMapping("/view-count")
	public ResponseEntity<Response> getByAllInquiryOrderByView(
			@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size) {
		Page<InquiryDto> inquiryDtos = inquiryService.getByAllInquiryOrderByView(page,size);
		
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
	@Operation(summary = "최신 순으로 전체 질문 출력")
	@GetMapping("/date")
	public ResponseEntity<Response> getByAllInquiryOrderByDate(
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
	@Operation(summary = "질문검색기능")
	@GetMapping("/search/{search}")
	public ResponseEntity<Response> getInquiryBySearch(@PathVariable(name = "search")String search
			,@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size) {
		Page<InquiryDto> inquiryDtos = inquiryService.getInquiryBySearch(search,page,size);
		
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
	
	@Operation(summary = "내가 작성한 질문내역")
	@GetMapping("/{memberNo}")
	public ResponseEntity<Response> getInquiryByMember(@PathVariable(name = "memberNo") Long memberNo
			,@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size) {
		Page<InquiryDto> inquiryDtos = inquiryService.getInquiryByMember(memberNo, page, size);
		
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
}