package com.itwill.jpa.controller.member_information;

import com.itwill.jpa.auth.PrincipalDetails;
import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.dto.member_information.CareerDto;
import com.itwill.jpa.dto.member_information.MentorProfileDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.MentorProfile;
import com.itwill.jpa.exception.CustomException;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.member_information.CareerService;
import com.itwill.jpa.service.member_information.MemberService;
import com.itwill.jpa.service.member_information.MentorProfileService;
import com.itwill.jpa.util.HttpStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.JpaRepositoryNameSpaceHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mentor-profile")
public class MentorProfileController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MentorProfileService mentorProfileService;
	@Autowired
	private CareerService careerService;
	
	@Operation(summary = "멘토 프로필 상세보기")
	@GetMapping("/{mentorProfileNo}")
	public ResponseEntity<Response> getMentorProfileDetail(@PathVariable("mentorProfileNo") Long mentorProfileNo) {
		try {
			// 🔥 서비스 호출하여 DTO 반환
			MentorProfileDto mentorProfileDto = mentorProfileService.getMentorProfileDetail(mentorProfileNo);
			List<CareerDto> careerDtos = careerService.getCareerByMentorProfileNo(mentorProfileNo);
			mentorProfileDto.setCareerDtos(careerDtos);

			// 🔥 응답 생성
			Response response = new Response();
			response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_SUCCESS_CODE);
			response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_SUCCESS);
			response.setData(mentorProfileDto);

			return ResponseEntity.ok(response);
		} catch (CustomException e) {
			Response response = new Response();
			response.setStatus(e.getStatusCode());
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	/**
	 * 특정 멘토의 평균 점수를 조회합니다.
	 */
	/**
	 * 자신의 멘토 프로필의 mentor_rating 점수를 조회합니다.
	 */
	@Operation(summary = "자신의 멘토 프로필 mentor_rating 조회")
	@GetMapping("/my-profile/mentor-rating")
	public ResponseEntity<Response> getMyMentorRating(@RequestParam(name = "memberNo") Long memberNo) {
		try {
			// 1️⃣ 멘토 프로필을 조회하여 mentor_rating 값을 가져옵니다.
			Double mentorRating = mentorProfileService.getAverageMentorRating(memberNo);

			// 2️⃣ 응답 데이터를 명확히 설정합니다.
			Response response = new Response();
			response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_SUCCESS_CODE);
			response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_SUCCESS);
			response.setData(mentorRating); // 🔥 mentor_rating 값을 소수점까지 정확히 전달

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			Response response = new Response();
			response.setStatus(ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE);
			response.setMessage(ResponseMessage.MENTOR_PROFILE_NOT_FOUND + " - " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	/**
	 * 멘토 프로필을 생성합니다.
	 */
	@Operation(summary = "멘토 프로필 생성")
	@PreAuthorize("hasRole('MENTEE')")
	@PostMapping("/create-profile")
	public ResponseEntity<Response> saveMentorProfile(Authentication authentication,
			@RequestBody MentorProfileDto mentorProfileDto,
			HttpServletResponse res) {
		
		//PrincipalDetails에서 memberNo를 가져옴
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		Long memberNo = principalDetails.getMemberNo();
		System.out.println("멘토 프로필 생성 memberNo : " + memberNo);
		
		MentorProfile mentorProfile = mentorProfileService.saveMentorProfile(memberNo, mentorProfileDto);
		Integer mentorProfileNo = mentorProfile.getMentorProfileNo().intValue();
		
		//토큰 재생성
		Map<String, String> tokens = memberService.regenerateTokensByMentorProfileNo(authentication, mentorProfileNo);
		
		//토큰 생성 후 http응답 헤더에 포함
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.add("Authorization", "Bearer " + tokens.get("accessToken"));
		httpHeaders.add("Refresh-Token", tokens.get("refreshToken"));
		
		//쿠키 설정
		// 4. JSON 문자열 생성 후 Base64로 인코딩
        String jsonValue = String.format("{\"accessToken\": \"%s\", \"refreshToken\": \"%s\"}", tokens.get("accessToken"), tokens.get("refreshToken"));
        String encodedValue = Base64.getEncoder().encodeToString(jsonValue.getBytes());
        
        // 5. 쿠키 설정
        Cookie cookie = new Cookie("member", encodedValue); // Base64로 인코딩된 값 저장
        cookie.setHttpOnly(false); // JavaScript에서 접근 가능
        cookie.setSecure(false); // HTTPS에서만 전송 (개발 환경에서는 false)
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24); // 1일

        res.addCookie(cookie);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CREATED_MENTOR_PROFILE_SUCCESS_CODE);
		response.setMessage(ResponseMessage.CREATED_MENTOR_PROFILE_SUCCESS);
		response.setData(mentorProfile);
		response.setAddData(tokens);
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}

	/**
	 * 멘토 더미 프로필을 생성합니다.
	 */
	@Operation(summary = "멘토 더미 프로필 생성")
	@PostMapping("/{memberNo}/create-dumy-profile")
	public ResponseEntity<Response> saveMentorDummyProfile(@PathVariable(name = "memberNo") Long memberNo) {

		mentorProfileService.saveMentorDummyProfile(memberNo);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.CREATED_MENTOR_PROFILE_SUCCESS_CODE);
		response.setMessage(ResponseMessage.CREATED_MENTOR_PROFILE_SUCCESS);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * 멘토 상태를 변경하는 엔드포인트
	 */
	@Operation(summary = "멘토 프로필 상태변경")
	@PutMapping("/status/{memberNo}")
	public ResponseEntity<Response> setMentorStatus(@PathVariable("memberNo") Long memberNo,
			@RequestParam("status") int status) {
		Response response = new Response();
		try {
			// 🔥 멘토 상태 변경 서비스 호출
			mentorProfileService.updateMentorStatus(memberNo, status);

			// 🔥 성공 응답 생성
			response.setStatus(ResponseStatusCode.UPDATE_MENTOR_PROFILE_SUCCESS_CODE);
			response.setMessage(ResponseMessage.UPDATE_MENTOR_PROFILE_SUCCESS);

			return ResponseEntity.ok(response);
		} catch (CustomException e) {
			// ⚠️ CustomException이 발생한 경우 예외 정보를 클라이언트에 전달
			response.setStatus(e.getStatusCode());
			response.setMessage(e.getMessage());
			if (e.getCause() != null) {
				response.setData(e.getCause().getMessage());
			}
			return ResponseEntity.status(e.getStatusCode()).body(response);
		} catch (Exception e) {
			// ⚠️ 예기치 않은 예외가 발생한 경우 서버 내부 오류로 응답
			response.setStatus(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			response.setMessage(ResponseMessage.INTERNAL_SERVER_ERROR);
			response.setData(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	/**
	 * 멘토 전체 리스트
	 */

	@Operation(summary = "특정 상태의 멘토 목록 조회 페이징")
	@GetMapping("/status/{status}")
	public ResponseEntity<Response> getMentorsByStatus(@PathVariable(name = "status") int status,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<MentorProfileDto> mentors = mentorProfileService.getMentorsByStatus(status, page, size);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
		response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_SUCCESS);
		response.setData(mentors);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	@Operation(summary = "검색 기능 페이징")
	@GetMapping("/search/{search}")
	public ResponseEntity<Response> searchMentorProfiles(@PathVariable(name = "search") String search,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<MentorProfileDto> mentors = mentorProfileService.getMentorProfiles(search, page, size);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
		response.setMessage("멘토 검색 성공");
		response.setData(mentors);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	@Operation(summary = "멘토의 멤버번호 조회")
	@GetMapping("/{mentorProfileNo}/member_no")
	public ResponseEntity<Response> getMemberNoByMentorProfileNo(
			@PathVariable("mentorProfileNo") Long mentorProfileNo) {
		Long mentorNo = mentorProfileService.getMemberNoByMentorNo(mentorProfileNo);
		Response response = new Response();
		response.setData(mentorNo);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);

		return responseEntity;
	}

	@Operation(summary = "카테고리 멘토리스트 페이징")
	@GetMapping("/category/{categoryNo}")
	public ResponseEntity<Response> getMentorProfilesByCategoryNo(@PathVariable(name = "categoryNo") Long categoryNo,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<MentorProfileDto> mentors = mentorProfileService.getMentorProfilesByCategoryNo(categoryNo, page, size);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
		response.setMessage("카테고리 멘토 조회 성공");
		response.setData(mentors);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	/**
	 * 멘토 프로필 이미지 업로드 메서드
	 */
	@PostMapping("/{mentorProfileNo}/upload-image")
	public ResponseEntity<Response> uploadMentorProfileImage(@PathVariable("mentorProfileNo") Long mentorProfileNo,
			@RequestParam("file") MultipartFile file) {
		
		try {
			
			String imageUrl = mentorProfileService.uploadMentorProfileImage(mentorProfileNo, file);
			Response response = new Response();
            response.setStatus(ResponseStatusCode.IMAGE_UPLOAD_SUCCESS);
            response.setMessage(ResponseMessage.IMAGE_UPLOAD_SUCCESS);
            response.setData(imageUrl); // **업로드된 이미지 URL 반환**
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
            
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
            
		} catch (Exception e) {
			
			Response response = new Response();
            response.setStatus(ResponseStatusCode.IMAGE_UPLOAD_FAIL);
            response.setMessage("이미지 업로드 실패: " + e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 멘토 프로필 이미지 URL 조회 메서드
	 */
	@GetMapping("/{mentorProfileNo}/image-url")
	public ResponseEntity<Map<String, String>> getMentorProfileImageUrl(
			@PathVariable("mentorProfileNo") Long mentorProfileNo) {
		try {
			String imageUrl = mentorProfileService.getMentorProfileImageUrl(mentorProfileNo);
			Map<String, String> response = new HashMap<>();
			response.put("imageUrl", imageUrl);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Map.of("error", "프로필 이미지 조회 실패: " + e.getMessage()));
		}
	}

	@Operation(summary = "멘토 프로필 수정")
	@PutMapping("/modify/{mentorProfileNo}")
	public ResponseEntity<Response> updateMentorProfile(@PathVariable("mentorProfileNo") Long mentorProfileNo,
			@RequestBody MentorProfileDto mentorProfileDto) {
		Response response = new Response();
		try {
			// 🔥 멘토 프로필 수정 서비스 호출
			MentorProfile mentorProfile = mentorProfileService.updateMentorProfile(mentorProfileNo, mentorProfileDto);
			

			// 🔥 성공 응답 생성
			response.setStatus(ResponseStatusCode.UPDATE_MENTOR_PROFILE_SUCCESS_CODE);
			response.setMessage(ResponseMessage.UPDATE_MENTOR_PROFILE_SUCCESS);
			response.setData(mentorProfile);
			
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (CustomException e) {
			// ⚠️ CustomException이 발생한 경우 예외 정보를 클라이언트에 전달
			response.setStatus(HttpStatusMapper.getHttpStatus(e.getStatusCode()).value());
			response.setMessage(e.getMessage());
			if (e.getCause() != null) {
				response.setData(e.getCause().getMessage());
			}
			return ResponseEntity.status(HttpStatusMapper.getHttpStatus(e.getStatusCode())).body(response);
		} catch (Exception e) {
			// ⚠️ 예기치 않은 예외가 발생한 경우 서버 내부 오류로 응답
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage(ResponseMessage.INTERNAL_SERVER_ERROR);
			response.setData(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Operation(summary = "멘토의 멘토링 횟수 조회")
	@GetMapping("/{mentorProfileNo}/mentoring-count")
	public ResponseEntity<Response> getMentorMentoringCount(@PathVariable("mentorProfileNo") Long mentorProfileNo) {
		Response response = new Response();
		try {
			Integer count = mentorProfileService.getMentorMentoringCount(mentorProfileNo);
			response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_SUCCESS_CODE);
			response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_SUCCESS);
			response.setData(count);
			return ResponseEntity.ok(response);
		} catch (CustomException e) {
			response.setStatus(e.getStatusCode());
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@Operation(summary = "멘토의 팔로우 수 조회")
	@GetMapping("/{mentorProfileNo}/follow-count")
	public ResponseEntity<Response> getMentorFollowCount(@PathVariable("mentorProfileNo") Long mentorProfileNo) {
		Response response = new Response();
		try {
			Integer count = mentorProfileService.getMentorFollowCount(mentorProfileNo);
			response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_SUCCESS_CODE);
			response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_SUCCESS);
			response.setData(count);
			return ResponseEntity.ok(response);
		} catch (CustomException e) {
			response.setStatus(e.getStatusCode());
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@Operation(summary = "멘토의 활동 수 조회")
	@GetMapping("/{mentorProfileNo}/activity-count")
	public ResponseEntity<Response> getMentorActivityCount(@PathVariable("mentorProfileNo") Long mentorProfileNo) {
		Response response = new Response();
		try {
			Integer count = mentorProfileService.getMentorActivityCount(mentorProfileNo);
			response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_SUCCESS_CODE);
			response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_SUCCESS);
			response.setData(count);
			return ResponseEntity.ok(response);
		} catch (CustomException e) {
			response.setStatus(e.getStatusCode());
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@Operation(summary = "팔로우 수 순으로 멘토 목록 조회")
	@GetMapping("/follow-count")
	public ResponseEntity<Response> getMentorProfilesByFollowCount(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<MentorProfileDto> mentors = mentorProfileService.getMentorsByFollowCount(page, size);
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
		response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_SUCCESS);
		response.setData(mentors);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "멘토링 횟수 순으로 멘토 목록 조회")
	@GetMapping("/mentoring-count")
	public ResponseEntity<Response> getMentorProfilesByMentoringCount(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<MentorProfileDto> mentors = mentorProfileService.getMentorsByMentoringCount(page, size);
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
		response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_SUCCESS);
		response.setData(mentors);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "활동 수 순으로 멘토 목록 조회")
	@GetMapping("/activity-count")
	public ResponseEntity<Response> getMentorProfilesByActivityCount(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<MentorProfileDto> mentors = mentorProfileService.getMentorsByActivityCount(page, size);
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
		response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_SUCCESS);
		response.setData(mentors);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "멤버넘버로 멘토프로필 조회")
	@GetMapping("/mentor-profile/{memberNo}")
	public ResponseEntity<Response> getMentorProfileByMemberNo(@PathVariable(name = "memberNo") Long memberNo) {
		MentorProfileDto mentor = mentorProfileService.getMentorByMemberNo(memberNo);
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_SUCCESS_CODE);
		response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_SUCCESS);
		response.setData(mentor);
		return ResponseEntity.ok(response);

	}
	// 12월 24일 멘토 프로필 카테고리

	// MentorProfileController.java
	/**
	 * 팔로우 순으로 카테고리별 멘토 리스트 조회
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@Operation(summary = "팔로우 순으로 대분류 카테고리의 멘토 리스트 조회")
	@GetMapping("/{parentCategoryNo}/parent/follow")
	public ResponseEntity<Response> getByParentCategoryOrderByFollowCount(
			@PathVariable(name = "parentCategoryNo") Long parentCategoryNo,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<MentorProfileDto> mentorDtos = mentorProfileService.getByParentCategoryOrderByFollowCount(parentCategoryNo,
				page, size);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
		response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_SUCCESS);
		response.setData(mentorDtos);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
	}

	@Operation(summary = "멘토링 횟수 순으로 대분류 카테고리의 멘토 리스트 조회")
	@GetMapping("/{parentCategoryNo}/parent/mentoring")
	public ResponseEntity<Response> getByParentCategoryOrderByMentoringCount(
			@PathVariable(name = "parentCategoryNo") Long parentCategoryNo,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<MentorProfileDto> mentorDtos = mentorProfileService
				.getByParentCategoryOrderByMentoringCount(parentCategoryNo, page, size);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
		response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_SUCCESS);
		response.setData(mentorDtos);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
	}

	@Operation(summary = "활동 순으로 대분류 카테고리의 멘토 리스트 조회")
	@GetMapping("/{parentCategoryNo}/parent/activity")
	public ResponseEntity<Response> getByParentCategoryOrderByActivityCount(
			@PathVariable(name = "parentCategoryNo") Long parentCategoryNo,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<MentorProfileDto> mentorDtos = mentorProfileService
				.getByParentCategoryOrderByActivityCount(parentCategoryNo, page, size);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
		response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_SUCCESS);
		response.setData(mentorDtos);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
	}

	@Operation(summary = "팔로우 순으로 CATEGORY_NO별 멘토 리스트 조회")
	@GetMapping("/category/{categoryNo}/follow")
	public ResponseEntity<Response> getMentorsByCategoryNoFollow(@PathVariable(name = "categoryNo") Long categoryNo,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<MentorProfileDto> mentors = mentorProfileService.getByCategoryNoOrderByFollowCount(categoryNo, page, size);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
		response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_SUCCESS);
		response.setData(mentors);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
	}

	@Operation(summary = "멘토링 횟수 순으로 CATEGORY_NO별 멘토 리스트 조회")
	@GetMapping("/category/{categoryNo}/mentoring")
	public ResponseEntity<Response> getMentorsByCategoryNoMentoring(@PathVariable(name = "categoryNo") Long categoryNo,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<MentorProfileDto> mentors = mentorProfileService.getByCategoryNoOrderByMentoringCount(categoryNo, page,
				size);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
		response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_SUCCESS);
		response.setData(mentors);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
	}

	@Operation(summary = "활동 수 순으로 CATEGORY_NO별 멘토 리스트 조회")
	@GetMapping("/category/{categoryNo}/activity")
	public ResponseEntity<Response> getMentorsByCategoryNoActivity(@PathVariable(name = "categoryNo") Long categoryNo,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<MentorProfileDto> mentors = mentorProfileService.getByCategoryNoOrderByActivityCount(categoryNo, page,
				size);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
		response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_SUCCESS);
		response.setData(mentors);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
	}

	@Operation(summary = "별점 순 멘토 순위")
	@GetMapping("/rating")
	public ResponseEntity<Response> getMentorsByCategoryNoActivity() {
		List<MentorProfileDto> mentors = mentorProfileService.getMentorByRating();

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
		response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_SUCCESS);
		response.setData(mentors);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
	}
	
	@Operation(summary = "경력 데이터 가져오기")
	@GetMapping("/career/{mentorProfileNo}")
	public ResponseEntity<Response> getCareer(@PathVariable("mentorProfileNo") Long mentorProfileNo) {
		List<CareerDto> careerDtos = careerService.getCareerByMentorProfileNo(mentorProfileNo);

		Response response = new Response();
		response.setStatus(900);
		response.setMessage("경력 출력 성공");
		response.setData(careerDtos);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
	}
	

}
