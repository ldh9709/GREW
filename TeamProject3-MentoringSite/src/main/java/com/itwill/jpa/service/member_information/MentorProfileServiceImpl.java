package com.itwill.jpa.service.member_information;

import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.MentorProfile;
import com.itwill.jpa.repository.member_information.MentorProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MentorProfileServiceImpl implements MentorProfileService {

    @Autowired
    private MentorProfileRepository mentorProfileRepository;

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
    
}
