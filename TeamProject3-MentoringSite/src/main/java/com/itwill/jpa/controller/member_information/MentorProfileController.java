package com.itwill.jpa.controller.member_information;

import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.MentorProfile;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.member_information.MentorProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/mentor-profile")
public class MentorProfileController {

    @Autowired
    private MentorProfileService mentorProfileService;

    /**
     * 멘토 생성 상태로 설정 (mentorStatus = 1)
     */
    @Operation(summary = "멘토 생성 상태로 설정")
    @PutMapping("/{memberNo}/status/created")
    public ResponseEntity<Response> setMentorStatusToCreated(@PathVariable Long memberNo) {
        mentorProfileService.setMentorStatusToCreated(memberNo);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.UPDATE_MEMBER_SUCCESS);
        response.setMessage("멘토가 생성 상태로 설정되었습니다.");

        return ResponseEntity.ok(response);
    }

    /**
     * 멘토 심사중 상태로 설정 (mentorStatus = 2)
     */
    @Operation(summary = "멘토 심사중 상태로 설정")
    @PutMapping("/{memberNo}/status/under-review")
    public ResponseEntity<Response> setMentorStatusToUnderReview(@PathVariable Long memberNo) {
        mentorProfileService.setMentorStatusToUnderReview(memberNo);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.UPDATE_MEMBER_SUCCESS);
        response.setMessage("멘토가 심사중 상태로 설정되었습니다.");

        return ResponseEntity.ok(response);
    }

    /**
     * 멘토 심사완료 상태로 설정 (mentorStatus = 3)
     */
    @Operation(summary = "멘토 심사완료 상태로 설정")
    @PutMapping("/{memberNo}/status/approved")
    public ResponseEntity<Response> setMentorStatusToApproved(@PathVariable Long memberNo) {
        mentorProfileService.setMentorStatusToApproved(memberNo);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.UPDATE_MEMBER_SUCCESS);
        response.setMessage("멘토가 심사완료 상태로 설정되었습니다.");

        return ResponseEntity.ok(response);
    }

    /**
     * 멘토 탈퇴 상태로 설정 (mentorStatus = 4)
     */
    @Operation(summary = "멘토 탈퇴 상태로 설정")
    @PutMapping("/{memberNo}/status/retired")
    public ResponseEntity<Response> setMentorStatusToRetired(@PathVariable Long memberNo) {
        mentorProfileService.setMentorStatusToRetired(memberNo);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.UPDATE_MEMBER_SUCCESS);
        response.setMessage("멘토가 탈퇴 상태로 설정되었습니다.");

        return ResponseEntity.ok(response);
    }

    /**
     * 특정 상태의 모든 멘토 프로필 조회
     */
    @Operation(summary = "특정 상태의 모든 멘토 프로필 조회")
    @GetMapping("/status/{status}")
    public ResponseEntity<Response> getMentorsByStatus(@PathVariable int status) {
        List<MentorProfile> mentors = mentorProfileService.getMentorsByStatus(status);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MEMBER_LIST_SUCCESS);
        response.setMessage("멘토 프로필 리스트 조회 성공");
        response.setData(mentors);

        return ResponseEntity.ok(response);
    }

    /**
     * 멘토 검색 (이름, 소개글, 경력)
     */
    @Operation(summary = "멘토 검색")
    @GetMapping("/search")
    public ResponseEntity<Response> searchMentorProfiles(@RequestParam String keyword) {
        List<MentorProfile> mentors = mentorProfileService.searchMentorProfiles(keyword);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MEMBER_LIST_SUCCESS);
        response.setMessage("멘토 프로필 검색 성공");
        response.setData(mentors);

        return ResponseEntity.ok(response);
    }

    /**
     * 특정 카테고리와 관련된 멘토 프로필 조회
     */
    @Operation(summary = "특정 카테고리와 관련된 멘토 프로필 조회")
    @GetMapping("/category/{categoryNo}")
    public ResponseEntity<Response> getMentorProfilesByCategory(@PathVariable Long categoryNo) {
        // 카테고리 객체를 생성 (간단한 예제에서 직접 생성)
        Category category = Category.builder().categoryNo(categoryNo).build();
        List<MentorProfile> mentors = mentorProfileService.getMentorProfilesByCategory(category);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MEMBER_LIST_SUCCESS);
        response.setMessage("특정 카테고리의 멘토 프로필 조회 성공");
        response.setData(mentors);

        return ResponseEntity.ok(response);
    }

    /**
     * 특정 멘토의 평균 점수 조회
     */
    @Operation(summary = "특정 멘토의 평균 점수 조회")
    @GetMapping("/{memberNo}/average-rating")
    public ResponseEntity<Response> getMentorAverageRating(@PathVariable Long memberNo) {
        Double averageRating = mentorProfileService.getAverageMentorRating(memberNo);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MEMBER_SUCCESS);
        response.setMessage("멘토 평균 점수 조회 성공");
        response.setData(averageRating);

        return ResponseEntity.ok(response);
    }
    
    @Operation(summary = "멤버 번호와 연결된 멘토 프로필 생성")
    @PostMapping("/{memberNo}/create-profile")
    public ResponseEntity<Response> createMentorProfile(
            @PathVariable Long memberNo,
            @RequestBody MentorProfile mentorProfile) {

        mentorProfileService.createMentorProfile(memberNo, mentorProfile);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.CREATED_MEMBER_SUCCESS);
        response.setMessage("멘토 프로필이 성공적으로 생성되었습니다.");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
