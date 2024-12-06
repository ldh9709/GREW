package com.itwill.jpa.service.member_information;

import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.MentorProfile;
import com.itwill.jpa.repository.chatting_review.ReviewRepository;
import com.itwill.jpa.repository.member_information.MentorProfileRepository;
import com.itwill.jpa.service.chatting_review.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MentorProfileServiceImpl implements MentorProfileService {

    @Autowired
    private MentorProfileRepository mentorProfileRepository;
    @Autowired
    private ReviewService reviewService;
    /**
     * 멘토 생성 상태로 설정 (mentorStatus = 1)
     */
    @Override
    public void setMentorStatusToCreated(Long memberNo) {
        mentorProfileRepository.updateMentorStatus(memberNo, 1);
    }

    /**
     * 멘토 심사중 상태로 설정 (mentorStatus = 2)
     */
    @Override
    public void setMentorStatusToUnderReview(Long memberNo) {
        mentorProfileRepository.updateMentorStatus(memberNo, 2);
    }

    /**
     * 멘토 심사완료 상태로 설정 (mentorStatus = 3)
     */
    @Override
    public void setMentorStatusToApproved(Long memberNo) {
        mentorProfileRepository.updateMentorStatus(memberNo, 3);
    }

    /**
     * 멘토 탈퇴 상태로 설정 (mentorStatus = 4)
     */
    @Override
    public void setMentorStatusToRetired(Long memberNo) {
        mentorProfileRepository.updateMentorStatus(memberNo, 4);
    }

    /**
     * 특정 상태의 모든 멘토 프로필 조회
     */
    @Override
    public List<MentorProfile> getMentorsByStatus(int status) {
        return mentorProfileRepository.findAllByMentorStatus(status);
    }

    /**
     * 멘토 검색 (이름, 소개글, 경력)
     */
    @Override
    public List<MentorProfile> searchMentorProfiles(String keyword) {
        return mentorProfileRepository.searchMentorProfiles(keyword);
    }

    /**
     * 특정 카테고리와 관련된 멘토 프로필 조회
     */
    @Override
    public List<MentorProfile> getMentorProfilesByCategory(Category category) {
        return mentorProfileRepository.findByCategory(category);
    }
    
    @Override
    public void updateMentorRating(Long mentorNo) {
        // ReviewService를 통해 평균 점수 가져오기
        Double averageScore = reviewService.getAverageReviewScoreByMentor(mentorNo);

        if (averageScore != null) {
            // 멘토 프로필 가져오기
            MentorProfile mentorProfile = mentorProfileRepository.findByMember_MemberNo(mentorNo);
            if (mentorProfile != null) {
                // 평균 점수 업데이트
                mentorProfile.setMentorRating(averageScore);
                mentorProfileRepository.save(mentorProfile);
            }
        }
    }
  
 
}
