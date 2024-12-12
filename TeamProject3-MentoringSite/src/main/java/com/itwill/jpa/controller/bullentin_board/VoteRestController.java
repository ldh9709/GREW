package com.itwill.jpa.controller.bullentin_board;


import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.dto.bulletin_board.VoteDto;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.alarm.AlarmService;
import com.itwill.jpa.service.bullentin_board.VoteService;

import io.swagger.v3.oas.annotations.Operation;
@RestController
@RequestMapping("/vote")
public class VoteRestController {
	@Autowired
	private VoteService voteService;
	@Autowired
	private AlarmService alarmService;
	@Operation(summary = "추천")
	@PostMapping("/upvote")
	public ResponseEntity<Response> upVote(@RequestBody VoteDto voteDto) {
		VoteDto savedvote=voteService.upVote(voteDto);
	    AlarmDto alarmDto=alarmService.createAlarmByVoteByMentor(voteDto.getAnswerNo());
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CREATED_ANSWER_SUCCESS);
		response.setMessage("추천 등록 성공~!");
		response.setData(savedvote);
		response.setAddData(alarmDto);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.CREATED);
		
		return responseEntity;

	}
	@Operation(summary = "비추천")
	@PostMapping("/downvote")
	public ResponseEntity<Response> downVote(@RequestBody VoteDto voteDto) {
		VoteDto savedvote=voteService.downVote(voteDto);
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CREATED_ANSWER_SUCCESS);
		response.setMessage("비추천 등록 성공~!");
		response.setData(savedvote);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.CREATED);
		
		return responseEntity;
	}
	@Operation(summary = "추천-비추천 값")
	@GetMapping("/{answerNo}/votes")
	public ResponseEntity<Response> getNetVotes(@PathVariable("answerNo") Long answerNo) {
	    Integer votes = voteService.calcVotes(answerNo);
	    Response response = new Response();
		response.setStatus(ResponseStatusCode.CREATED_ANSWER_SUCCESS);
		response.setMessage("추천값 출력 성공");
		response.setData(votes);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.CREATED);
		return responseEntity;
	}
}
