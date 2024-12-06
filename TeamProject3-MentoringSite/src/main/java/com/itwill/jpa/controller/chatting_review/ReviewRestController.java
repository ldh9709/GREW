package com.itwill.jpa.controller.chatting_review;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.entity.chatting_review.Review;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.chatting_review.ReviewService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/review")
public class ReviewRestController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Operation(summary = "리뷰 등록")
	@PostMapping
	public ResponseEntity<Response> insertReview(ReviewDto reviewDto){
		
		Response response = new Response();
		reviewService.saveReview(reviewDto);
		response.setStatus(ResponseStatusCode.CREATED_REVIEW_SUCCESS);
		response.setMessage(ResponseMessage.CREATED_REVIEW_SUCCESS);
		response.setData(reviewDto);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);
		
		return responseEntity;
		
	}
	
	@Operation(summary = "리뷰 수정")
	@PutMapping("/update/{ReviewNo}")
	public ResponseEntity<Response> updateReview(@RequestBody ReviewDto reviewDto){
		
		Response response = new Response();
		reviewService.updateReview(reviewDto);
		response.setStatus(ResponseStatusCode.UPDATE_REVIEW_SUCCESS);
		response.setMessage(ResponseMessage.UPDATE_REVIEW_SUCCESS);
		response.setData(reviewDto);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);
		
		return responseEntity;
	}
	@Operation(summary = "리뷰 삭제")
	@PutMapping("/delete/{ReviewNo}")
	public ResponseEntity<Response> deleteReview(@RequestBody ReviewDto reviewDto){
		
		Response response = new Response();
		reviewService.deleteReview(reviewDto.getReviewNo());
		response.setStatus(ResponseStatusCode.DELETE_REVIEW_SUCCESS);
		response.setMessage(ResponseMessage.DELETE_REVIEW_SUCCESS);
		response.setData(reviewDto);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);
		
		return responseEntity;
	}
	@Operation(summary = "리뷰 상세보기")
	@GetMapping("/{ReviewNo}")
	public ResponseEntity<Response> selectReviewByReviewNo(@PathVariable(name="reviewNo") Long reviewNo){
		
		Response response = new Response();
		Review review = reviewService.selectReviewByReviewNo(reviewNo);
		response.setStatus(ResponseStatusCode.VIEW_REVIEW_SUCCESS);
		response.setMessage(ResponseMessage.VIEW_REVIEW_SUCCESS);
		response.setData(review);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.OK);
		
		return responseEntity;
	}
	@Operation(summary = "특정 요청 리뷰 목록 출력")
	@GetMapping("/MentoringRequest/{requestNo}")
	public ResponseEntity<Response> selectReviewByRequestNo(@PathVariable(name="requestNo") Long requestNo){
		
		Response response = new Response();
		List<ReviewDto> reviews = reviewService.selectReviewByRequestNo(requestNo);
		response.setStatus(ResponseStatusCode.READ_REVIEW_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_REVIEW_LIST_SUCCESS);
		response.setData(reviews);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.OK);
		
		return responseEntity;
	}
	@Operation(summary = "특정 멤버 리뷰 목록 출력")
	@GetMapping("/member/{member_no}")
	public ResponseEntity<Response> selectReviewByMemberNo(@PathVariable(name="member_no") Long memberNo){
		
		Response response = new Response();
		List<ReviewDto> reviews = reviewService.selectReviewByMemberNo(memberNo);
		response.setStatus(ResponseStatusCode.READ_REVIEW_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_REVIEW_LIST_SUCCESS);
		response.setData(reviews);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.OK);
		
		return responseEntity;
	}
	
	
	
	

}
