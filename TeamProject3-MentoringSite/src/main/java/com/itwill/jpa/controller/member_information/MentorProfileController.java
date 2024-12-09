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
    @Operation(summary = "멘토 평균 점수 조회")
    @GetMapping("/{memberNo}/average-rating")
    public ResponseEntity<Response> getMentorAverageRating(@PathVariable Long memberNo) {
        Double averageRating = mentorProfileService.getAverageMentorRating(memberNo);
        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MEMBER_SUCCESS);
        response.setMessage("멘토 평균 점수 조회 성공");
        response.setData(averageRating);
        return ResponseEntity.ok(response);
    }

    /**
     * 멘토의 평점을 업데이트합니다.
     */
    @Operation(summary = "멘토 평점 업데이트")
    @GetMapping("/mentor-profile/update-rating/{memberNo}")
    public ResponseEntity<?> updateRating(@PathVariable("memberNo") Long memberNo) {
        mentorProfileService.updateMentorRating(memberNo);
        return ResponseEntity.ok("Mentor rating updated successfully for memberNo: " + memberNo);
    }

    /**
     * 멘토 프로필을 생성합니다.
     */
    @Operation(summary = "멘토 프로필 생성")
    @PostMapping("/{memberNo}/create-profile")
    public ResponseEntity<Response> createMentorProfile(
            @PathVariable("memberNo") Long memberNo,  
            @RequestBody MentorProfileDto mentorProfileDto) {

        // 멘토 프로필을 생성하는 서비스 호출
        mentorProfileService.createMentorProfile(memberNo, mentorProfileDto);
        
        Response response = new Response();
        response.setStatus(ResponseStatusCode.CREATED_MEMBER_SUCCESS);
        response.setMessage(ResponseMessage.CREATED_MEMBER_SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

