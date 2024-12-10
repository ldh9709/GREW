package com.itwill.jpa.controller.bullentin_board;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.entity.bullentin_board.Answer;
import com.itwill.jpa.entity.bullentin_board.Inquiry;
import com.itwill.jpa.repository.bullentin_board.InquiryRepository;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.alarm.AlarmService;
import com.itwill.jpa.service.bullentin_board.AnswerService;
import com.itwill.jpa.service.bullentin_board.InquiryService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/answer")
public class AnswerRestController {
	
	@Autowired
	private AnswerService answerService;
	@Autowired
	private AlarmService alarmService;
	/* 답변 등록 */
	@Operation(summary = "답변 등록")
	@PostMapping
	//@Transactional
	public ResponseEntity<Response> createAnswer(@RequestBody AnswerDto answerDto){
		// 1. 서비스 호출 : 답변 데이터 저장
		AnswerDto createAnswerDto = answerService.saveAnswer(answerDto);
		AlarmDto alarmDto = alarmService.saveAlarmByAnswerToInquiry(createAnswerDto);
		// 2. 응답 데이터(Response 객체) 생성
		// - 응답객체에 코드, 메시지, 객체 설정
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CREATED_ANSWER_SUCCESS);
		response.setMessage(ResponseMessage.CREATED_ANSWER_SUCCESS);
		response.setData(createAnswerDto);
		response.setAddData(alarmDto);
		// 3. 응답 헤더 설정(인코딩 타입)
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		// 4. 응답 Entity 생성(ResponseEntity)
		// - HTTP 상태 코드와 헤더, 응답 데이터를 포함한 ResponseEntity를 반환합니다.
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.CREATED);
		
		// 5. Entity 반환
		return responseEntity;
		
	}
	
	
	/* 답변 수정 */
	@Operation(summary = "답변 수정")
	@PutMapping("update/{answerNo}")
	public ResponseEntity<Response> updateAnswer(@PathVariable(name = "answerNo") Long answerNo, @RequestBody AnswerDto answerDto) throws Exception {
		
		// 1. 서비스 호출 : 답변 업데이트 메소드 실행
		answerDto.setAnswerNo(answerNo);
		AnswerDto saveAnswerDto = answerService.updateAnswer(answerDto);
		
		// 2. 응답 데이터(Response 객체) 생성
		// - 응답객체에 코드, 메시지, 객체 설정
		Response response = new Response();
		response.setStatus(ResponseStatusCode.UPDATE_ANSWER_SUCCESS);
		response.setMessage(ResponseMessage.UPDATE_ANSWER_SUCCESS);
		response.setData(saveAnswerDto);
		
		// 3. 응답 헤더 설정(인코딩 타입)
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
	
		// 4. 응답 Entity 생성(ResponseEntity)
		// - HTTP 상태 코드와 헤더, 응답 데이터를 포함한 ResponseEntity를 반환합니다.
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	/* 답변채택 */
	@Operation(summary = "답변 채택")
	@PutMapping("/accept")
	public ResponseEntity<Response> acceptAnswer(@RequestBody AnswerDto answerDto) throws Exception {
		try {
			AnswerDto acceptedAnswerDto = answerService.acceptAnswer(answerDto);
			
			Response response = new Response();
			response.setStatus(ResponseStatusCode.ACCEPT_ANSWER_SUCCESS);
			response.setMessage(ResponseMessage.ACCEPT_ANSWER_SUCCESS);
			response.setData(acceptedAnswerDto);
			
			HttpHeaders httpHeaders = new HttpHeaders();
		    httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		        
	        // 5. 응답 Entity 생성(ResponseEntity)
	        ResponseEntity<Response> responseEntity = 
	                new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
			
			return responseEntity;
		} catch (ResponseStatusException ex) {
	        // 예외 발생 시 적절한 상태 코드와 메시지를 Response 객체에 담아 반환
	        Response response = new Response();
	        response.setStatus(ResponseStatusCode.ACCEPT_ANSWER_FAIL);
	        response.setMessage(ResponseMessage.ACCEPT_ANSWER_FAIL);
	        response.setData(null);
	        
	        HttpHeaders httpHeaders = new HttpHeaders();
		    httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		        
	        // 5. 응답 Entity 생성(ResponseEntity)
	        ResponseEntity<Response> responseEntity = 
	                new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
	        
	        return responseEntity;
		}
	}
	
	
	/* 답변 삭제(상태 업데이트) */
	@Operation(summary = "답변 삭제(상태 수정)")
	@PutMapping("/delete/{answerNo}")
	public ResponseEntity<Response> updateAnswerStatus(@PathVariable(name = "answerNo")Long answerNo, @RequestBody AnswerDto answerDto) throws Exception {
		answerDto.setAnswerNo(answerNo);
		AnswerDto deleteAnswerDto = answerService.deleteAnswer(answerDto);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.DELETE_ANSWER_SUCCESS);
		response.setMessage(ResponseMessage.DELETE_ANSWER_SUCCESS);
		response.setData(deleteAnswerDto);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.OK);
		
		
		return responseEntity;
	}
	
	/* 답변 상세보기 */
	@Operation(summary = "답변 상세보기")
	@GetMapping("/{answerNo}/answerDetail")
	public ResponseEntity<Response> findAnswerByAnswerNo(@PathVariable(name = "answerNo") Long answerNo) {
		AnswerDto answerDto = answerService.getAnswer(answerNo);
		
		Response response = new Response();
	    response.setStatus(ResponseStatusCode.READ_ANSWER_SUCCESS);
	    response.setMessage(ResponseMessage.READ_ANSWER_SUCCESS);
	    response.setData(answerDto);
	    
	    HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
	    ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
		
	}
	
	/* 질문 하나에 달린 답변 */
	/* 추천순 */
	@Operation(summary = "질문에 작성된답변조회(추천순)")
	@GetMapping("/answerList/{inquiryNo}/inquiryVote")
	public ResponseEntity<Response> findByAnswerOrderByVoteDate(@PathVariable(name = "inquiryNo") Long inquiryNo,
			@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size) {
		
		Page<AnswerDto> answerDtos = answerService.findByInquiryAnswerOrderByVotes(inquiryNo,page,size);
		
		Response response = new Response();
	    response.setStatus(ResponseStatusCode.READ_ANSWER_LIST_SUCCESS);
	    response.setMessage(ResponseMessage.READ_ANSWER_LIST_SUCCESS);
	    response.setData(answerDtos);
	    
	    HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
	    ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
	    return responseEntity;
		
		
	}
	
	
	/* 질문 하나에 달린 답변 */
	/* 최신순 */
	@Operation(summary = "질문에 작성된답변조회(최신순)")
	@GetMapping("/answerList/{inquiryNo}/inquiryDate")
	public ResponseEntity<Response> findByInquiryAnswerOrderByDate(@PathVariable(name = "inquiryNo") Long inquiryNo,
			@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size) {
		
		Page<AnswerDto> answerDtos = answerService.findByInquiryAnswerOrderByDate(inquiryNo,page,size);
		
		Response response = new Response();
	    response.setStatus(ResponseStatusCode.READ_ANSWER_LIST_SUCCESS);
	    response.setMessage(ResponseMessage.READ_ANSWER_LIST_SUCCESS);
	    response.setData(answerDtos);
	    
	    HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
	    ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
	    return responseEntity;
	}
	
	/* 카테고리별 답변 리스트 */
	/* 추천순 */
	@Operation(summary = "카테고리별 답변조회(추천순)")
	@GetMapping("/answerList/{categoryNo}categoryVote")
	public ResponseEntity<Response> findByCategoryAnswerOrderByVotes(@PathVariable(name = "categoryNo") Long categoryNo,
			@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size){
		
		Page<AnswerDto> answerDtos = answerService.findByCategoryAnswerOrderByDate(categoryNo,page,size);
		
		Response response = new Response();
	    response.setStatus(ResponseStatusCode.READ_ANSWER_LIST_SUCCESS);
	    response.setMessage(ResponseMessage.READ_ANSWER_LIST_SUCCESS);
	    response.setData(answerDtos);
	    
	    HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
	    ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		
		return responseEntity;
	}
	
	/* 카테고리별 답변 리스트 */
	/* 최신순 */
	@Operation(summary = "카테고리별 답변조회(최신순)")
	@GetMapping("/answerList/{categoryNo}/categoryDate")
	public ResponseEntity<Response> findByCategoryAnswerOrderByDate(@PathVariable(name = "categoryNo") Long categoryNo,
			@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size){
		
		Page<AnswerDto> answerDtos = answerService.findByCategoryAnswerOrderByDate(categoryNo,page,size);
		
		Response response = new Response();
	    response.setStatus(ResponseStatusCode.READ_ANSWER_LIST_SUCCESS);
	    response.setMessage(ResponseMessage.READ_ANSWER_LIST_SUCCESS);
	    response.setData(answerDtos);
	    
	    HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
	    ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	
	/* 최근 3일동안 추천 많이 받은 답변 리스트 */
	@Operation(summary = "최근 3일간 추천 많이 받은 답변 리스트")
	@GetMapping("/answerList/recently-vote")
	public ResponseEntity<Response> findByAnswerOrderByVoteDate(
			@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size) {
		
		Page<AnswerDto> answerDtos = answerService.findByAnswerOrderByVoteDate(page,size);
		
		Response response = new Response();
	    response.setStatus(ResponseStatusCode.READ_ANSWER_LIST_SUCCESS);
	    response.setMessage(ResponseMessage.READ_ANSWER_LIST_SUCCESS);
	    response.setData(answerDtos);
	    
	    HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
	    ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	
}