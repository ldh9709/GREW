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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class MentorProfileServiceImpl implements MentorProfileService {
	
	private static final String IMAGE_PATH = "src/main/resources/static/images/mentor-profile/";
	
	
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

        // 4️ MentorProfileDto → MentorProfile 엔티티로 변환 (MentorProfile 엔티티의 toEntity 메서드 사용)
        MentorProfile mentorProfile = MentorProfile.toEntity(mentorProfileDto, member, category);
        mentorProfile.setMentorStatus(2); // 멘토의 초기 상태를 "생성 대기"로 설정

        // 5️ 멘토 프로필 저장
        mentorProfileRepository.save(mentorProfile);
        
        
    }


    /**
     * 특정 멘토의 평균 점수를 반환합니다.
     */
    @Override
    public Double getAverageMentorRating(Long memberNo) {
    	MentorProfile mentorProfile = mentorProfileRepository.findByMemberNo(memberNo);
    	if (mentorProfile == null) {
    	    throw new IllegalStateException("해당 멘토 프로필을 찾을 수 없습니다.");
    	}
        return mentorProfile.getMentorRating();
    }

    // 멘토의 mentor_rating 업데이트
    @Transactional
    public void updateMentorRating(Long memberNo) {
        mentorProfileRepository.updateMentorRatingByMemberNo(memberNo);
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

    @Override
    public void updateMentorProfileImage(Long mentorProfileNo, MultipartFile file) throws Exception {
        // 1️⃣ 멘토 프로필 정보 조회
        MentorProfile mentorProfile = mentorProfileRepository.findById(mentorProfileNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 멘토 프로필을 찾을 수 없습니다. mentorProfileNo: " + mentorProfileNo));

        // 2️⃣ 절대 경로 가져오기
        String absolutePath = new File("").getAbsolutePath();
        String IMAGE_PATH = absolutePath + "/src/main/resources/static/images/mentor-profile/"; // 절대 경로 생성

        // 3️⃣ 디렉터리 확인 및 생성
        File saveDir = new File(IMAGE_PATH);
        if (!saveDir.exists()) {
            saveDir.mkdirs(); // 디렉터리가 없으면 생성
        }

        // 4️⃣ 파일명 생성 (고유한 이름으로 생성)
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + fileExtension;

        // 5️⃣ 파일 저장 경로 생성
        File saveFile = new File(IMAGE_PATH + fileName);

        // 6️⃣ 파일 저장
        file.transferTo(saveFile);

        // 7️⃣ mentorProfile 엔티티에 이미지 경로 저장
        mentorProfile.setMentorImage("/images/mentor-profile/" + fileName);
        mentorProfileRepository.save(mentorProfile);
    }
    
    
}

//    /**
//     * 특정 상태의 모든 멘토 프로필을 조회합니다.
//     */
//    @Override
//    public List<MentorProfile> getMentorsByStatus(int status) {
//        return mentorProfileRepository.findByMentorStatus(status);
//    }
//
//
//    /**
//     * 특정 키워드(이름, 소개글, 경력)으로 멘토를 검색합니다.
//     */
//    @Override
//    public List<MentorProfile> searchMentorProfiles(String keyword) {
//        return mentorProfileRepository.searchMentorProfiles(keyword);
//    }
//    /**
//     * 카테고리 번호로 멘토 프로필 목록 조회
//    
//   */
//    @Override
//    public List<MentorProfile> getMentorProfilesByCategoryNo(Long categoryNo) {
//        return mentorProfileRepository.findByCategoryNo(categoryNo);
//    }
//    