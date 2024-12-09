package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.dto.member_information.MentorProfileDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.member_information.MentorProfile;
import com.itwill.jpa.repository.chatting_review.ReviewRepository;
import com.itwill.jpa.repository.member_information.CategoryRepository;
import com.itwill.jpa.repository.member_information.MemberRepository;
import com.itwill.jpa.repository.member_information.MentorProfileRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.QueryTimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MentorProfileServiceImpl implements MentorProfileService {
	 @PersistenceContext
	  private EntityManager entityManager;
	 private final MentorProfileRepository mentorProfileRepository;

	    @Autowired
	    public MentorProfileServiceImpl(MentorProfileRepository mentorProfileRepository) {
	        this.mentorProfileRepository = mentorProfileRepository;
	    }

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 멘토 상태를 "생성 상태"로 변경합니다. (mentorStatus = 1)
     */
    @Override
    public void setMentorStatusToCreated(Long memberNo) {
        mentorProfileRepository.updateMentorStatus(memberNo, 1);
    }

    /**
     * 멘토 상태를 "심사중"으로 변경합니다. (mentorStatus = 2)
     */
    @Override
    public void setMentorStatusToUnderReview(Long memberNo) {
        mentorProfileRepository.updateMentorStatus(memberNo, 2);
    }

    /**
     * 멘토 상태를 "심사완료"로 변경합니다. (mentorStatus = 3)
     */
    @Override
    public void setMentorStatusToApproved(Long memberNo) {
        mentorProfileRepository.updateMentorStatus(memberNo, 3);
    }

    /**
     * 멘토 상태를 "탈퇴"로 변경합니다. (mentorStatus = 4)
     */
    @Override
    public void setMentorStatusToRetired(Long memberNo) {
        mentorProfileRepository.updateMentorStatus(memberNo, 4);
    }

    /**
     * 특정 상태의 모든 멘토 프로필을 조회합니다.
     */
    @Override
    public List<MentorProfile> getMentorsByStatus(int status) {
        return mentorProfileRepository.findAllByMentorStatus(status);
    }

    /**
     * 특정 키워드(이름, 소개글, 경력)으로 멘토를 검색합니다.
     */
    @Override
    public List<MentorProfile> searchMentorProfiles(String keyword) {
        return mentorProfileRepository.searchMentorProfiles(keyword);
    }

    /**
     * 특정 카테고리와 관련된 멘토 프로필을 조회합니다.
     */
    @Override
    public List<MentorProfile> getMentorProfilesByCategory(Category category) {
        return mentorProfileRepository.findByCategory(category);
    }

    
    @Override
    public void createMentorProfile(Long memberNo, MentorProfileDto mentorProfileDto) {
        // 1️ 회원(Member) 정보 조회
        Member member = memberRepository.findById(memberNo)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다. memberNo: " + memberNo));

        // 2️ 카테고리 정보 조회
        Category category = categoryRepository.findById(mentorProfileDto.getCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("카테고리 정보를 찾을 수 없습니다. categoryNo: " + mentorProfileDto.getCategoryNo()));

        // 3️ 이미 멘토 프로필이 존재하는지 확인
        if (mentorProfileRepository.findByMember(member) != null) {
            throw new IllegalStateException("해당 회원은 이미 멘토 프로필을 가지고 있습니다. memberNo: " + memberNo);
        }

        // 4️ MentorProfileDto → MentorProfile 엔티티로 변환
        MentorProfile mentorProfile = MentorProfileDto.toEntity(mentorProfileDto, member, category);
        mentorProfile.setMentorStatus(1); // 멘토의 초기 상태를 "생성 대기"로 설정

        // 5️ 멘토 프로필 저장
        mentorProfileRepository.save(mentorProfile);
    }

    /**
     * 특정 멘토의 평균 점수를 반환합니다.
     */
    @Override
    public Double getAverageMentorRating(Long memberNo) {
        List<Integer> reviewScores = reviewRepository.findReviewScoresByMentor(memberNo);
        if (reviewScores.isEmpty()) {
            return 0.0;
        }
        return reviewScores.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    // 멘토의 mentor_rating 업데이트
    @Transactional
    public void updateMentorRating(Long memberNo) {
        mentorProfileRepository.updateMentorRatingByMemberNo(memberNo);
    }

    /**
     * 리뷰 정보를 바탕으로 멘토 프로필을 저장하거나 업데이트합니다.
     */
    @Override
    public MentorProfileDto saveMentorProfileByReview(ReviewDto reviewDto) {
        Long memberNo = reviewDto.getMemberNo();

        // 1️⃣ 멘토 평점 업데이트
        updateMentorRating(memberNo); 

        // 2️⃣ 멘토 프로필 조회 (Optional을 사용해 null 체크)
        MentorProfile mentorProfile = mentorProfileRepository.findByMemberNo(memberNo);

        // 3️⃣ 멘토 프로필이 없을 때 예외 발생 또는 새로 생성
        if (mentorProfile == null) {
            // 3-1. 예외 발생 방식
            throw new IllegalStateException("해당 멤버의 멘토 프로필이 존재하지 않습니다. memberNo: " + memberNo);
            
            // 3-2. 새로운 멘토 프로필 생성 방식 (필요한 경우)
            // mentorProfile = new MentorProfile();
            // mentorProfile.setMember(Member.builder().memberNo(memberNo).build()); // 기본 Member 객체 설정
            // mentorProfile.setMentorRating(0.0); // 초기 평점 설정
            // mentorProfile.setMentorStatus(1); // 초기 멘토 상태
            // mentorProfileRepository.save(mentorProfile); // 저장
        }

        // 4️⃣ MentorProfile 엔티티를 MentorProfileDto로 변환 (null-safe)
        return MentorProfileDto.toDto(mentorProfile);
    }

}
