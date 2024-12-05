package com.itwill.jpa.controller.bullentin_board;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.alarm.AlarmService;
import com.itwill.jpa.service.bullentin_board.AnswerService;

import io.swagger.v3.oas.annotations.Operation;
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
	public ResponseEntity<Response> insertAnswer(@RequestBody AnswerDto answerDto){
		
		// 1. 서비스 호출 : 답변 데이터 저장
		AnswerDto insertAnswerDto = answerService.saveAnswer(answerDto);
		/******알림 등록*****/
		AlarmDto alarmDto = new AlarmDto();
		alarmDto.setAlarmType("질문");
		alarmDto.setReferenceNo(answerDto.getAnswerNo());
		alarmDto.setAlarmContent("회원님의 질문에 답변이 달렸습니다");
		alarmDto.setReferenceType("답변");
		alarmDto.setMemberNo(answerDto.getInquiryMemberNo());
		alarmService.saveAlarm(alarmDto);
		// 2. 응답 데이터(Response 객체) 생성
		// - 응답객체에 코드, 메시지, 객체 설정
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CREATED_ANSWER_SUCCESS);
		response.setMessage(ResponseMessage.CREATED_ANSWER_SUCCESS);
		response.setData(insertAnswerDto);
		
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
	@PutMapping("/{answerNo}")
	public ResponseEntity<Response> updateAnswer(@RequestBody AnswerDto answerDto) throws Exception {
		
		// 1. 서비스 호출 : 답변 업데이트 메소드 실행
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
	/* 잠시보류 */
	
	
	/* 답변 삭제(상태 업데이트) */
	@Operation(summary = "답변 삭제(상태 수정)")
	@PutMapping("/{answerNo}/status")
	public ResponseEntity<Response> updateAnswerStatus(@RequestBody AnswerDto answerDto) throws Exception {
		
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
	
	
	/* 답변리스트 조회 */
	/* 최신순 */
	@Operation(summary = "한 질문의 답변리스트 조회(최신순)")
	@GetMapping("/viewAnswerDate/{inquiryNo}")
	public ResponseEntity<Response> findByInquiryAnswerOrderByDate(@PathVariable(name = "inquiryNo") Long inquiryNo) {
		
		List<AnswerDto> answerDtos = answerService.findByInquiryAnswerOrderByDate(inquiryNo);
		
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
	/* 추천순 */
	@Operation(summary = "한 질문의 답변리스트 조회(추천순)")
	@GetMapping("/viewAnswerVotes/{inquiryNo}")
	public ResponseEntity<Response> findByInquiryAnswerOrderByVotes(@PathVariable(name = "inquiryNo") Long inquiryNo) {
		
		List<AnswerDto> answerDtos = answerService.findByInquiryAnswerOrderByVotes(inquiryNo);
		
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
