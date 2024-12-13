package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.dto.member_information.MentorProfileDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.MentorProfile;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface MentorProfileService {

	//멘토 생성 
	void createMentorProfile(Long memberNo, MentorProfileDto mentorProfileDto);

	// 멘토 생성 상태로 변경 (mentorStatus = 1)
    void setMentorStatusToCreated(Long memberNo);

    // 멘토 심사중 상태로 변경 (mentorStatus = 2)
    void setMentorStatusToUnderReview(Long memberNo);

    // 멘토 심사완료 상태로 변경 (mentorStatus = 3)
    void setMentorStatusToApproved(Long memberNo);

    // 멘토 탈퇴 상태로 변경 (mentorStatus = 4)
    void setMentorStatusToRetired(Long memberNo);

    // 특정 멘토의 평균 점수를 반환
    Double getAverageMentorRating(Long memberNo);
    
    // 특정 멘토의 평점을 업데이트 //지우지말것
    void updateMentorRating(Long memberNo);
    
    Page<MentorProfileDto> getMentorsByStatus(int status, int page, int size);

    Page<MentorProfileDto> searchMentorProfiles(String keyword, int page, int size);

    Page<MentorProfileDto> getMentorProfilesByCategoryNo(Long categoryNo, int page, int size);
    
    void uploadMentorProfileImage(Long mentorProfileNo, MultipartFile file) throws Exception;
    
    String getMentorProfileImageUrl(Long mentorProfileNo);
}

