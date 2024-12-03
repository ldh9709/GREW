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

import com.itwill.jpa.dto.bulletin_board.VoteDto;
import com.itwill.jpa.service.bullentin_board.VoteService;

import io.swagger.v3.oas.annotations.Operation;
@RestController
@RequestMapping("/vote")
public class VoteRestController {
	@Autowired
	private VoteService voteService;
	@Operation(summary = "추천")
	@PostMapping("/upvote")
	public ResponseEntity<VoteDto> upVote(@RequestBody VoteDto voteDto) {
	    try {
	        VoteDto savedVote = voteService.UpVote(voteDto);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedVote);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    }
	}
	@Operation(summary = "비추천")
	@PostMapping("/downvote")
	public ResponseEntity<VoteDto> downVote(@RequestBody VoteDto voteDto) {
	    try {
	        VoteDto savedVote = voteService.DownVote(voteDto);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedVote);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    }
	}
	@Operation(summary = "추천-비추천 값")
	@GetMapping("/{answerNo}/votes")
	public ResponseEntity<Integer> getNetVotes(@PathVariable("answerNo") Long answerNo) {
	    Integer votes = voteService.calcVotes(answerNo);
	    return ResponseEntity.ok(votes);
	}
}
