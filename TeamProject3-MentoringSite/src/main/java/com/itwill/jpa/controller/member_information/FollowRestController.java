package com.itwill.jpa.controller.member_information;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.auth.PrincipalDetails;
import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.dto.member_information.FollowRequestDto;
import com.itwill.jpa.dto.member_information.FollowResponseDto;
import com.itwill.jpa.exception.GlobalExceptionHandler;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.alarm.AlarmService;
import com.itwill.jpa.service.member_information.FollowService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/follow")
public class FollowRestController {
	
	@Autowired
	private FollowService followService;
	@Autowired
	private AlarmService alarmService;
	
	
	/*팔로우 여부 체크*/
	@Operation(summary = "팔로우 등록여부 체크")
	@SecurityRequirement(name = "BearerAuth")//API 엔드포인트가 인증을 요구한다는 것을 문서화(Swagger에서 JWT인증을 명시
	@PreAuthorize("hasRole('MENTEE')")//ROLE이 MENTEE인 사람만 접근 가능
	@GetMapping("/is-exist")
	public ResponseEntity<Response> checkFollow(Authentication authentication, @RequestParam("mentorNo") String mentorNo){
		
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		Long menteeNo = principalDetails.getMemberNo();
		
		
		Boolean checkFollow = followService.isExistFollow(menteeNo,Long.parseLong(mentorNo));
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CHECK_FOLLOW_SUCCESS);
		response.setMessage(ResponseMessage.CHECK_FOLLOW_SUCCESS);
		response.setData(checkFollow);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity =
				 new ResponseEntity<Response>(response,httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	
	/*팔로우 등록*/
	@Operation(summary = "팔로우 신청")
	@SecurityRequirement(name = "BearerAuth")//API 엔드포인트가 인증을 요구한다는 것을 문서화(Swagger에서 JWT인증을 명시
	@PreAuthorize("hasRole('MENTEE')")//ROLE이 MENTEE인 사람만 접근 가능
	@PostMapping
	public ResponseEntity<Response> createFollow(@RequestBody FollowRequestDto followDto){
		followService.createFollow(followDto);
		AlarmDto alarmDto = alarmService.createAlarmByFollowByMentor(followDto.getMentorMemberNo());
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CREATE_FOLLOW_SUCCESS);
		response.setMessage(ResponseMessage.CREATE_FOLLOW_SUCCESS);
		response.setData(followDto);
		response.setAddData(alarmDto);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity =
				 new ResponseEntity<Response>(response,httpHeaders, HttpStatus.CREATED);
		
		return responseEntity;
	}
	/*팔로우 취소*/
	@Operation(summary = "팔로우 취소")
	@DeleteMapping("/{followNo}")
	public ResponseEntity<Response> deleteFollow(@PathVariable(name="followNo") Long FollowNo){
		followService.deleteFollow(FollowNo);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.DELETE_FOLLOW_SUCCESS);
		response.setMessage(ResponseMessage.DELETE_FOLLOW_SUCCESS);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	/*팔로잉 리스트 출력(멘토리스트)*/
	@Operation(summary = "멘티 팔로잉 리스트 출력")
	@SecurityRequirement(name = "BearerAuth")
	@PreAuthorize("hasRole('MENTEE')")
	@GetMapping("/followList")
	public ResponseEntity<Response> getFollowingMentorList(
			Authentication authentication,
			@RequestParam(name = "page", defaultValue ="0") int page,
			@RequestParam(name = "size", defaultValue ="6") int size){
		
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		Long memberNo = principalDetails.getMemberNo();
		
		Page<FollowResponseDto> followMentorList = followService.getMentorList(memberNo, page, size);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTORLIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_MENTORLIST_SUCCESS);
		response.setData(followMentorList);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity =
				new ResponseEntity<Response>(response,httpHeaders,HttpStatus.OK);
		
		return responseEntity;
	}
	/*팔로워 수(멘티 수)*/
	@Operation(summary = "멘토 팔로워 수 출력")
	@GetMapping("/mentor/{mentorNo}/follower-count")
	public ResponseEntity<Response> getFollowerCount(@PathVariable(name="mentorNo") Long mentorNo){
		Integer followerCount = followService.countFollower(mentorNo);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTEE_COUNT_SUCCESS);
		response.setMessage(ResponseMessage.READ_MENTEE_COUNT_SUCCESS);
		response.setData(followerCount);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity =
				new ResponseEntity<Response>(response,httpHeaders,HttpStatus.OK);
		
		return responseEntity;
	}
	
}
