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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

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

    @Operation(summary = "특정 상태의 멘토 목록 조회")
    @GetMapping("/status/{status}")
    public ResponseEntity<Response> getMentorsByStatus(@PathVariable(name = "status") int status) {
        try {
            List<MentorProfile> mentorProfiles = mentorProfileService.getMentorsByStatus(status);
            
            if (mentorProfiles.isEmpty()) {
                Response response = new Response();
                response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_FAIL_CODE);
                response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_FAIL + " - 해당 상태의 멘토가 존재하지 않습니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            Response response = new Response();
            response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_SUCCESS_CODE);
            response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_SUCCESS);
            response.setData(mentorProfiles);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Response response = new Response();
            response.setStatus(ResponseStatusCode.READ_MENTOR_PROFILE_LIST_FAIL_CODE);
            response.setMessage(ResponseMessage.READ_MENTOR_PROFILE_LIST_FAIL + " - " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    /**
     * 특정 키워드로 멘토를 검색합니다.
     */
    @Operation(summary = "검색 기능")
    @GetMapping("/search/{keyword}")
    public ResponseEntity<Response> searchMentorProfiles(@PathVariable(name = "keyword") String keyword) {
        List<MentorProfile> mentorProfiles = mentorProfileService.searchMentorProfiles(keyword);
        Response response = new Response();
        response.setStatus(200);
        response.setMessage("멘토 검색 성공");
        response.setData(mentorProfiles);
        return ResponseEntity.ok(response);
    }
/**
 *  카테고리 번호로 멘토 프로필 목록 조회
 * @param categoryNo
 * @return
 */
    @Operation(summary = "카테고리 멘토리스트")
    @GetMapping("/category/{categoryNo}")
    public ResponseEntity<List<MentorProfile>> getMentorProfilesByCategoryNo(@PathVariable(name="categoryNo") Long categoryNo) {
        List<MentorProfile> mentorProfiles = mentorProfileService.getMentorProfilesByCategoryNo(categoryNo);
        return ResponseEntity.ok(mentorProfiles);
    }


  
}
