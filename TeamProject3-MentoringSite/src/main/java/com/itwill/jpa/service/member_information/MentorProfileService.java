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
	void saveMentorProfile(Long memberNo, MentorProfileDto mentorProfileDto);
 //유저 상태 변경 1 멘티 2처음 만들어지고 심사 3멘토 4탈퇴
	void updateMentorStatus(Long memberNo, int status);

    // 특정 멘토의 평균 점수를 반환
    Double getAverageMentorRating(Long memberNo);
    
    // 특정 멘토의 평점을 업데이트 //지우지말것
    void updateMentorRating(Long memberNo);
    
    Double updateMentorRatingg(Long memberNo);
    
    
    Page<MentorProfileDto> getMentorsByStatus(int status, int page, int size);

    Page<MentorProfileDto> getMentorProfiles(String keyword, int page, int size);

    Page<MentorProfileDto> getMentorProfilesByCategoryNo(Long categoryNo, int page, int size);
    
    void uploadMentorProfileImage(Long mentorProfileNo, MultipartFile file) throws Exception;
    
    String getMentorProfileImageUrl(Long mentorProfileNo);
}

