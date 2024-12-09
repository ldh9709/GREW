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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/inquiry")
public class InquiryRestController {
	@Autowired
	private InquiryService inquiryService;

	// 질문등록
	@Operation(summary = "질문 등록")
	@PostMapping
	public ResponseEntity<Response> insertInquiry(@RequestBody InquiryDto inquiryDto) {
		Response response = new Response();
		
		inquiryService.saveInquiry(inquiryDto);
		response.setStatus(ResponseStatusCode.CREATED_INQUIRY_SUCCESS);
		response.setMessage(ResponseMessage.CREATED_INQUIRY_SUCCESS);
		response.setData(inquiryDto);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);

		return responseEntity;
	}

	// 질문수정
	@Operation(summary = "질문 수정")
	@PutMapping("/update/{inquiryNo}")
	public ResponseEntity<Response> updateInquiry(@RequestBody InquiryDto inquiryDto) throws Exception {
		Response response = new Response();

		inquiryService.updateInquiry(inquiryDto);
		response.setStatus(ResponseStatusCode.UPDATE_INQUIRY_SUCCESS);
		response.setMessage(ResponseMessage.UPDATE_INQUIRY_SUCCESS);
		response.setData(inquiryDto);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);

		return responseEntity;
	}

	// 질문삭제
	@Operation(summary = "질문삭제")
	@PutMapping("/delete/{inquiryNo}")
	public ResponseEntity<Response> deleteInquiry(@RequestBody InquiryDto inquiryDto) throws Exception {
		Response response = new Response();

		inquiryService.deleteInquiry(inquiryDto);
		response.setStatus(ResponseStatusCode.DELETE_INQUIRY_SUCCESS);
		response.setMessage(ResponseMessage.DELETE_INQUIRY_SUCCESS);
		response.setData(inquiryDto);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);

		return responseEntity;
	}

	// 질문보기
	@Operation(summary = "질문보기")
	@GetMapping("/{inquiryNo}")
	public ResponseEntity<Response> viewInquiry(@PathVariable(name = "inquiryNo") Long inquiryNo) {
		Response response = new Response();

		InquiryDto inquiryDto = inquiryService.getInquiry(inquiryNo);
		response.setStatus(ResponseStatusCode.VIEW_INQUIRY_SUCCESS);
		response.setMessage(ResponseMessage.VIEW_INQUIRY_SUCCESS);
		response.setData(inquiryDto);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);

		return responseEntity;

	}

	// 조회수 증가
	@Operation(summary = "조회수증가")
	@PutMapping("/increase/{inquiryNo}")
	public ResponseEntity<Response> increaseViewInquiry(@RequestBody InquiryDto inquiryDto) throws Exception {
		Response response = new Response();

		inquiryService.increaseViewInquiry(inquiryDto);
		response.setStatus(ResponseStatusCode.INCREASE_VIEW_INQUIRY_SUCCESS);
		response.setMessage(ResponseMessage.INCREASE_VIEW_INQUIRY_SUCCESS);
		response.setData(inquiryDto);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);

		return responseEntity;
	}
	@Operation(summary = "답변수 많은 순으로 카테고리별 질문 출력")
	@GetMapping("/answerCount/{categoryNo}")
	public ResponseEntity<Response> findByCategoryInquiryOrderByAnswer(@PathVariable(name = "categoryNo") Long categoryNo) {
	    List<InquiryDto> inquiryDtos = inquiryService.findByCategoryInquiryOrderByAnswer(categoryNo);
	    
	    Response response = new Response();
	    response.setStatus(ResponseStatusCode.READ_INQUIRY_LIST_SUCCESS);
	    response.setMessage(ResponseMessage.READ_INQUIRY_LIST_SUCCESS);
	    response.setData(inquiryDtos);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@Operation(summary = "조회수 많은 순으로 카테고리별 질문 출력")
	@GetMapping("/viewCount/{categoryNo}")
	public ResponseEntity<Response> findByCategoryInquiryOrderByView(@PathVariable(name = "categoryNo") Long categoryNo) {
		List<InquiryDto> inquiryDtos = inquiryService.findByCategoryInquiryOrderByView(categoryNo);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_INQUIRY_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_INQUIRY_LIST_SUCCESS);
		response.setData(inquiryDtos);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@Operation(summary = "답변수 많은 순으로 전체 질문 출력")
	@GetMapping("/answerCount")
	public ResponseEntity<Response> findByAllInquiryOrderByAnswer() {
		List<InquiryDto> inquiryDtos = inquiryService.findByAllInquiryOrderByAnswer();
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_INQUIRY_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_INQUIRY_LIST_SUCCESS);
		response.setData(inquiryDtos);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@Operation(summary = "조회수 많은 순으로 전체 질문 출력")
	@GetMapping("/viewCount")
	public ResponseEntity<Response> findByAllInquiryOrderByView(
			@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size) {
		Page<InquiryDto> inquiryDtos = inquiryService.findByAllInquiryOrderByView(page,size);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_INQUIRY_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_INQUIRY_LIST_SUCCESS);
		response.setData(inquiryDtos);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@Operation(summary = "질문검색기능")
	@GetMapping("/search/{search}")
	public ResponseEntity<Response> findInquiryBySearch(@PathVariable(name = "search")String search) {
		List<InquiryDto> inquiryDtos = inquiryService.findInquiryBySearch(search);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_INQUIRY_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_INQUIRY_LIST_SUCCESS);
		response.setData(inquiryDtos);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}