package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.dto.member_information.MentorProfileDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.member_information.MentorProfile;
import com.itwill.jpa.exception.CustomException;
import com.itwill.jpa.repository.chatting_review.ReviewRepository;
import com.itwill.jpa.repository.member_information.CategoryRepository;
import com.itwill.jpa.repository.member_information.MemberRepository;
import com.itwill.jpa.repository.member_information.MentorProfileRepository;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.QueryTimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class MentorProfileServiceImpl implements MentorProfileService {
	
	private static final String IMAGE_PATH = "C:/mentor-profile-images/";
	
	
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
     * 멘토 상태를 "심사완료"로 변경합니다. (mentorStatus = 3)멘토 가입 완료한 사람 멘티에서-->멘토로 변경
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
     * 멘토 프로필 생성 메서드
     */
    @Override
    public void createMentorProfile(Long memberNo, MentorProfileDto mentorProfileDto) {
        // 1️⃣ 회원 정보 조회
        Member member = memberRepository.findById(memberNo)
                .orElseThrow(() -> new CustomException(
                        ResponseStatusCode.MEMBER_MENTOR_NOT_FOUND,
                        ResponseMessage.MEMBER_MENTOR_NOT_FOUND
                ));

        // 2️⃣ 카테고리 정보 조회
        Category category = categoryRepository.findById(mentorProfileDto.getCategoryNo())
                .orElseThrow(() -> new CustomException(
                        ResponseStatusCode.CATEGORY_NOT_FOUND,
                        ResponseMessage.CATEGORY_NOT_FOUND
                ));

        // 3️⃣ 멘토 프로필 중복 확인
        if (mentorProfileRepository.findByMember(member) != null) {
            throw new CustomException(
                    ResponseStatusCode.ALREADY_HAS_MENTOR_PROFILE,
                    ResponseMessage.ALREADY_HAS_MENTOR_PROFILE
            );
        }

        try {
            MentorProfile mentorProfile = MentorProfile.toEntity(mentorProfileDto, member, category);
            mentorProfile.setMentorStatus(2); // 심사중 상태로 설정
            mentorProfileRepository.save(mentorProfile);
        } catch (Exception e) {
            throw new CustomException(
                    ResponseStatusCode.CREATED_MENTOR_PROFILE_FAIL,
                    ResponseMessage.CREATED_MENTOR_PROFILE_FAIL
            );
        }
    }


    /**
     * 멘토의 평균 점수를 반환하는 메서드
     */
    @Override
    public Double getAverageMentorRating(Long memberNo) {
        MentorProfile mentorProfile = mentorProfileRepository.findByMemberNo(memberNo);
        if (mentorProfile == null) {
            throw new CustomException(
                    ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE,
                    ResponseMessage.MENTOR_PROFILE_NOT_FOUND
            );
        }
        return mentorProfile.getMentorRating();
    }

    /**
     * 멘토의 mentor_rating 업데이트
     */
    @Transactional
    public void updateMentorRating(Long memberNo) {
        try {
            mentorProfileRepository.updateMentorRatingByMemberNo(memberNo);
        } catch (Exception e) {
            throw new CustomException(
                ResponseStatusCode.UPDATE_MENTOR_PROFILE_FAIL_CODE,
                ResponseMessage.UPDATE_MENTOR_PROFILE_FAIL_CODE
            );
        }
    }

 
    @Override
    public Page<MentorProfileDto> getMentorsByStatus(int status, int page, int size) {
    	Pageable pageable = PageRequest.of(page, size);
    	Page<MentorProfile> mentorProfiles = mentorProfileRepository.findByMentorStatus(status, pageable);
    	return mentorProfiles.map(MentorProfileDto::toDto);
    }
    
    @Override
    public Page<MentorProfileDto> searchMentorProfiles(String keyword, int page, int size) {
    	Pageable pageable = PageRequest.of(page, size);
    	Page<MentorProfile> mentorProfiles = mentorProfileRepository.searchMentorProfiles(keyword, pageable);
    	return mentorProfiles.map(MentorProfileDto::toDto);
    }
    
    @Override
    public Page<MentorProfileDto> getMentorProfilesByCategoryNo(Long categoryNo, int page, int size) {
    	Pageable pageable = PageRequest.of(page, size);
    	Page<MentorProfile> mentorProfiles = mentorProfileRepository.findByCategoryNo(categoryNo, pageable);
    	return mentorProfiles.map(MentorProfileDto::toDto);
    }
    
    
    /**
     * 프로필 이미지 업로드 메서드
     */
    @Override
    public void uploadMentorProfileImage(Long mentorProfileNo, MultipartFile file) {
        try {
            // 멘토 프로필 정보 조회
            MentorProfile mentorProfile = mentorProfileRepository.findById(mentorProfileNo)
                    .orElseThrow(() -> new CustomException(
                            ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE , 
                            ResponseMessage.MENTOR_PROFILE_NOT_FOUND
                    ));

            // 이미지 저장 경로 설정
            File directory = new File(IMAGE_PATH);
            if (!directory.exists()) {
                directory.mkdirs(); // 디렉터리가 없으면 생성
            }

            // 파일명 생성 (UUID + 원본 파일명)
            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + originalFilename;

            // 파일 경로 설정
            File saveFile = new File(IMAGE_PATH + fileName);
            file.transferTo(saveFile); // 파일 저장

            // MentorProfile 엔티티의 이미지 URL을 업데이트
            mentorProfile.setMentorImage("/mentor-profile-images/" + fileName);
            mentorProfileRepository.save(mentorProfile);
        } catch (IOException e) {
            throw new CustomException(ResponseStatusCode.IMAGE_UPLOAD_FAIL, ResponseMessage.IMAGE_UPLOAD_FAIL);
        }
    }

    /**
     * 프로필 이미지 URL 조회 메서드
     */
    @Override
    public String getMentorProfileImageUrl(Long mentorProfileNo) {
        MentorProfile mentorProfile = mentorProfileRepository.findById(mentorProfileNo)
                .orElseThrow(() -> new CustomException(
                        ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE , 
                        ResponseMessage.MENTOR_PROFILE_NOT_FOUND
                ));

        return mentorProfile.getMentorImage();
    }
}
    
    
    


