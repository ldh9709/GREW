package com.itwill.jpa.controller.chatting_review;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.auth.PrincipalDetails;
import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.dto.member_information.MentorProfileDto;
import com.itwill.jpa.entity.chatting_review.Review;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.alarm.AlarmService;
import com.itwill.jpa.service.chatting_review.ReviewService;
import com.itwill.jpa.service.member_information.MentorProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/review")
public class ReviewRestController {
	
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private AlarmService alarmService;
	@Autowired
	private MentorProfileService mentorProfileService;
	
	
	
	@Operation(summary = "리뷰 등록")
	@SecurityRequirement(name = "BearerAuth") // API 엔드포인트가 인증을 요구한다는 것을 문서화(Swagger에서 JWT인증을 명시
	@PostMapping("/create")
	public ResponseEntity<Response> insertReview(@RequestBody ReviewDto reviewDto){
		

		ReviewDto saveReview = ReviewDto.toDto(reviewService.createReview(reviewDto));

		
		mentorProfileService.updateMentorRating(saveReview.getMentorMemberNo());

		AlarmDto alarmDto = alarmService.createAlarmByReview(saveReview.getReviewNo());


		
		Response response = new Response();
		if (reviewDto.getChatRoomNo() == null) {
			response.setStatus(ResponseStatusCode.CREATED_REVIEW_FAIL);
			response.setMessage(ResponseMessage.CREATED_REVIEW_FAIL);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);	
		}
		response.setStatus(ResponseStatusCode.CREATED_REVIEW_SUCCESS);
		response.setMessage(ResponseMessage.CREATED_REVIEW_SUCCESS);
		response.setData(reviewDto);
		response.setData(saveReview);
		response.setAddData(alarmDto);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@Operation(summary = "리뷰 수정")
	@PutMapping("/update/{reviewNo}")
	public ResponseEntity<Response> updateReview(@RequestBody ReviewDto reviewDto){
		
		Response response = new Response();
		ReviewDto review = ReviewDto.toDto(reviewService.updateReview(reviewDto));
		response.setStatus(ResponseStatusCode.UPDATE_REVIEW_SUCCESS);
		response.setMessage(ResponseMessage.UPDATE_REVIEW_SUCCESS);
		response.setData(review);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);
		
		return responseEntity;
	}
	@Operation(summary = "리뷰 삭제")
	@PutMapping("/delete/{reviewNo}")
	public ResponseEntity<Response> deleteReview(@PathVariable(name = "reviewNo")Long reviewNo){
		
		Response response = new Response();

		Review review = reviewService.deleteReview(reviewNo);

		response.setStatus(ResponseStatusCode.DELETE_REVIEW_SUCCESS);
		response.setMessage(ResponseMessage.DELETE_REVIEW_SUCCESS);
		response.setData(review);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.OK);
		
		return responseEntity;
	}
	@Operation(summary = "리뷰 상세보기")
	@GetMapping("/detail/{reviewNo}")
	public ResponseEntity<Response> selectReviewByReviewNo(@PathVariable(name="reviewNo") Long reviewNo){
		
		Response response = new Response();
		ReviewDto review = reviewService.getReviewByReviewNo(reviewNo);
		// 리뷰가 없는 경우
	    if (review == null) {
	        response.setStatus(ResponseStatusCode.VIEW_REVIEW_FAIL);  // 예를 들어, REVIEW_NOT_FOUND 상태 코드
	        response.setMessage(ResponseMessage.VIEW_REVIEW_FAIL);  // REVIEW_NOT_FOUND 메시지
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
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
	@GetMapping("/ChatRoom/{chatRoomNo}")
	public ResponseEntity<Response> selectReviewByChatRoomNo(@PathVariable(name="chatRoomNo") Long chatRoomNo,
			@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size){
		
		Response response = new Response();
		Page<ReviewDto> reviews = reviewService.getReviewByChatRoomNo(chatRoomNo,page,size);
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
	@GetMapping("/reviewList/{mentorProfileNo}")
	public ResponseEntity<Response> selectReviewByMemberNo(
			@PathVariable(name="mentorProfileNo") Long mentorProfileNo,
			@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size){
		
		// mentorProfileNo로 멘토의 memberNo를 찾아야 합니다.
	    Long memberNo = mentorProfileService.getMemberNoByMentorNo(mentorProfileNo); // 해당 멘토의 memberNo를 찾는 서비스 메서드

	    Response response = new Response();
	    Page<ReviewDto> reviewsPage = reviewService.getReviewByMemberNo(memberNo, page, size);
//	    List<ReviewDto> reviews = reviewsPage.getContent();
		response.setStatus(ResponseStatusCode.READ_REVIEW_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_REVIEW_LIST_SUCCESS);
		response.setData(reviewsPage);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.OK);
		
		return responseEntity;
	}
	
	@Operation(summary = "전체 리뷰 목록 출력")
	@GetMapping("/reviewList")
	public ResponseEntity<Response> getReviewListAll(@RequestParam(name = "page",defaultValue = "0") int page,  // 기본값은 0 페이지
            @RequestParam(name = "size",defaultValue = "10") int size){
	
		Response response = new Response();
		Page<ReviewDto> reviews = reviewService.getReviewAll(page,size);
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
