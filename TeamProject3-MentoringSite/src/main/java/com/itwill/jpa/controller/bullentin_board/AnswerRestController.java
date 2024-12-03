package com.itwill.jpa.controller.bullentin_board;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.bullentin_board.AnswerService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/answer")
public class AnswerRestController {
	
	@Autowired
	private AnswerService answerService;
	
	/* 답변 등록 */
	@Operation(summary = "답변 등록")
	@PostMapping
	public ResponseEntity<Response> insertAnswer(@RequestBody AnswerDto answerDto){
		
		// 1. 서비스 호출 : 답변 데이터 저장
		answerService.saveAnswer(answerDto);
		
		// 2. 응답 데이터(Response 객체) 생성
		// - 응답객체에 코드, 메시지, 객체 설정
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CREATED_ANSWER_SUCESS);
		response.setMessage(ResponseMessage.CREATED_ANSWER_SUCESS);
		response.setData(answerDto);
		
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
	
}
