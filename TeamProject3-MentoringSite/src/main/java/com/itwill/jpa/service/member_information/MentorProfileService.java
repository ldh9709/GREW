package com.itwill.jpa.service.member_information;

import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.MentorProfile;

import java.util.List;

public interface MentorProfileService {

    // 멘토 생성 상태로 변경 (mentorStatus = 1)
    void setMentorStatusToCreated(Long memberNo);

    // 멘토 심사중 상태로 변경 (mentorStatus = 2)
    void setMentorStatusToUnderReview(Long memberNo);

    // 멘토 심사완료 상태로 변경 (mentorStatus = 3)
    void setMentorStatusToApproved(Long memberNo);

    // 멘토 탈퇴 상태로 변경 (mentorStatus = 4)
    void setMentorStatusToRetired(Long memberNo);

    // 특정 상태의 모든 멘토 프로필 조회
    List<MentorProfile> getMentorsByStatus(int status);

    // 멘토 검색 (이름, 소개글, 경력)
    List<MentorProfile> searchMentorProfiles(String keyword);

    // 특정 카테고리와 관련된 멘토 프로필 조회
    List<MentorProfile> getMentorProfilesByCategory(Category category);
    /**
     * 멘토 프로필의 평균 점수를 업데이트
     */
    void updateMentorRating(Long mentorNo);
}
