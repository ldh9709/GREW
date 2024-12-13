package com.itwill.jpa.controller.member_information;

import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.dto.member_information.MentorProfileDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.MentorProfile;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.member_information.MentorProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mentor-profile")
public class MentorProfileController {

    private final MentorProfileService mentorProfileService;

    @Autowired
    public MentorProfileController(MentorProfileService mentorProfileService) {
        this.mentorProfileService = mentorProfileService;
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
    @PostMapping("/{memberNo}/create-profile")
    public ResponseEntity<Response> createMentorProfile(
            @PathVariable(name = "memberNo") Long memberNo,  
            @RequestBody MentorProfileDto mentorProfileDto) {

        mentorProfileService.createMentorProfile(memberNo, mentorProfileDto);
        
        Response response = new Response();
        response.setStatus(ResponseStatusCode.CREATED_MENTOR_PROFILE_SUCCESS_CODE);
        response.setMessage(ResponseMessage.CREATED_MENTOR_PROFILE_SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 멘토 상태를 생성 상태로 변경합니다.
     */
    @Operation(summary = "멘토 -->멘티로 변경")
    @PutMapping("/status/created/{memberNo}")
    public ResponseEntity<Response> setMentorStatusToCreated(@PathVariable(name = "memberNo") Long memberNo) {
        mentorProfileService.setMentorStatusToCreated(memberNo);
        Response response = new Response();
        response.setStatus(ResponseStatusCode.UPDATE_MENTOR_PROFILE_SUCCESS_CODE);
        response.setMessage(ResponseMessage.UPDATE_MENTOR_PROFILE_SUCCESS);
        return ResponseEntity.ok(response);
    }

    /**
     * 멘토 상태를 심사중 상태로 변경합니다.
     */
    @Operation(summary = "멘토 상태 변경 - 심사중")
    @PutMapping("/status/under-review/{memberNo}")
    public ResponseEntity<Response> setMentorStatusToUnderReview(@PathVariable(name = "memberNo") Long memberNo) {
        mentorProfileService.setMentorStatusToUnderReview(memberNo);
        Response response = new Response();
        response.setStatus(ResponseStatusCode.UPDATE_MENTOR_PROFILE_SUCCESS_CODE);
        response.setMessage(ResponseMessage.UPDATE_MENTOR_PROFILE_SUCCESS);
        return ResponseEntity.ok(response);
    }

    /**
     * 멘토 상태를 심사 완료 상태로 변경합니다.
     */
    @Operation(summary = "멘토 상태 변경 - 심사완료")
    @PutMapping("/status/approved/{memberNo}")
    public ResponseEntity<Response> setMentorStatusToApproved(@PathVariable(name = "memberNo") Long memberNo) {
        mentorProfileService.setMentorStatusToApproved(memberNo);
        Response response = new Response();
        response.setStatus(ResponseStatusCode.UPDATE_MENTOR_PROFILE_SUCCESS_CODE);
        response.setMessage(ResponseMessage.UPDATE_MENTOR_PROFILE_SUCCESS);
        return ResponseEntity.ok(response);
    }

    /**
     * 멘토 상태를 탈퇴 상태로 변경합니다.
     */
    @Operation(summary = "멘토 상태 변경 - 탈퇴")
    @PutMapping("/status/retired/{memberNo}")
    public ResponseEntity<Response> setMentorStatusToRetired(@PathVariable(name = "memberNo") Long memberNo) {
        mentorProfileService.setMentorStatusToRetired(memberNo);
        Response response = new Response();
        response.setStatus(ResponseStatusCode.UPDATE_MENTOR_PROFILE_SUCCESS_CODE);
        response.setMessage(ResponseMessage.UPDATE_MENTOR_PROFILE_SUCCESS);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "특정 상태의 멘토 목록 조회 페이징")
    @GetMapping("/status/{status}")
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

    @Operation(summary = "검색 기능 페이징")
    @GetMapping("/search")
    public ResponseEntity<Response> searchMentorProfiles(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<MentorProfileDto> mentors = mentorProfileService.searchMentorProfiles(keyword, page, size);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
        response.setMessage("멘토 검색 성공");
        response.setData(mentors);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Operation(summary = "카테고리 멘토리스트 페이징")
    @GetMapping("/category/{categoryNo}")
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
    
    
    
    
    
    
    /**
     * 멘토 프로필 이미지 업로드 메서드
     */
    @PostMapping("/{mentorProfileNo}/upload-image")
    public ResponseEntity<String> uploadMentorProfileImage(
            @PathVariable("mentorProfileNo") Long mentorProfileNo, 
            @RequestParam("file") MultipartFile file
    ) {
        try {
            mentorProfileService.uploadMentorProfileImage(mentorProfileNo, file);
            return ResponseEntity.ok("프로필 이미지 업로드 성공");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("프로필 이미지 업로드 실패: " + e.getMessage());
        }
    }

    /**
     * 멘토 프로필 이미지 URL 조회 메서드
     */
    @GetMapping("/{mentorProfileNo}/image-url")
    public ResponseEntity<Map<String, String>> getMentorProfileImageUrl(
            @PathVariable("mentorProfileNo") Long mentorProfileNo
    ) {
        try {
            String imageUrl = mentorProfileService.getMentorProfileImageUrl(mentorProfileNo);
            Map<String, String> response = new HashMap<>();
            response.put("imageUrl", imageUrl);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of("error", "프로필 이미지 조회 실패: " + e.getMessage())
            );
        }
    }
    
    
    
    
    
    }
    
    
    
    
    
    
    
    
    

  

