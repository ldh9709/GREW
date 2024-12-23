package com.itwill.jpa.controller.admin;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.dto.member_information.MentorProfileDto;
import com.itwill.jpa.exception.CustomException;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.member_information.MemberService;
import com.itwill.jpa.service.member_information.MentorProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/admin")
public class AdminMemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MentorProfileService mentorProfileService;
	
	/* 회원 전체 정보 */
	@SecurityRequirement(name = "BearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "회원 전체 출력")
	@GetMapping("/member")
	public ResponseEntity<Response> getMemberAll(
			@Parameter(name = "role", description = "필터링할 역할 (ROLE_MENTEE, ROLE_MENTOR)", required = true, example = "ROLE_MENTEE") 
			@RequestParam(name ="role") String role, 
			@Parameter(name = "order", description = "정렬 종류 (1: 가입 순, 2: 이름 순)", required = true, example = "1") 
			@RequestParam(name ="order") Integer order
			){
		
		List<MemberDto> memberList = memberService.getMemberAll(role, order);
		
		Response response = new Response();
		
		response.setStatus(ResponseStatusCode.READ_MEMBER_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_MEMBER_LIST_SUCCESS);
		response.setData(memberList);
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	/* 멘토 전체 정보 */ 
	@SecurityRequirement(name = "BearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "멘토 프로필 전체 리스트 조회")
    @GetMapping("/mentor")
    public ResponseEntity<Response> getMentorsByStatus(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<MentorProfileDto> mentors = mentorProfileService.getMentorAll(page, size);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
        response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_SUCCESS);
        response.setData(mentors);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
    
	@SecurityRequirement(name = "BearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "멘토 프로필 리스트 출력(상태별)")
    @GetMapping("/mentor/status/{status}")
    public ResponseEntity<Response> getMentorsByStatus(
            @PathVariable(name = "status") int status,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<MentorProfileDto> mentors = mentorProfileService.getMentorsByStatus(status, page, size);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
        response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_SUCCESS);
        response.setData(mentors);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
	
	@SecurityRequirement(name = "BearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "멘토 프로필 리스트 출력(카테고리)")
    @GetMapping("/mentor/category/{categoryNo}")
    public ResponseEntity<Response> getMentorProfilesByCategoryNo(
            @PathVariable(name = "categoryNo") Long categoryNo,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<MentorProfileDto> mentors = mentorProfileService.getMentorProfilesByCategoryNo(categoryNo, page, size);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
        response.setMessage("카테고리 멘토 조회 성공");
        response.setData(mentors);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
    
    
    /* 멘토 상태 변경 */
	@SecurityRequirement(name = "BearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "멘토 프로필 상태변경")
    @PutMapping("/mentor/update-state/{memberNo}")
    public ResponseEntity<Response> setMentorStatus(
        @PathVariable("memberNo") Long memberNo, 
        @RequestParam("status") int status
    ) {
        Response response = new Response();
        // 🔥 멘토 상태 변경 서비스 호출
        mentorProfileService.updateMentorStatus(memberNo, status);

        // 🔥 성공 응답 생성
        response.setStatus(ResponseStatusCode.UPDATE_MENTOR_PROFILE_SUCCESS_CODE);
        response.setMessage(ResponseMessage.UPDATE_MENTOR_PROFILE_SUCCESS);
        response.setData(null);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
