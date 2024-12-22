package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.chatting_review.ChatRoomDto;
import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.dto.member_information.MentorProfileDto;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.member_information.MentorProfile;
import com.itwill.jpa.exception.CustomException;
import com.itwill.jpa.repository.member_information.CategoryRepository;
import com.itwill.jpa.repository.member_information.MemberRepository;
import com.itwill.jpa.repository.member_information.MentorProfileRepository;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.chatting_review.ChatRoomService;
import com.itwill.jpa.service.chatting_review.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class MentorProfileServiceImpl implements MentorProfileService {

    private static final String IMAGE_PATH = "C:/mentor-profile-images/";
    
    @Autowired
    private MentorProfileRepository mentorProfileRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 멘토 상태를 변경하는 메서드
     */
    @Override
    public void updateMentorStatus(Long memberNo, int status) {
        try {
            mentorProfileRepository.updateMentorStatus(memberNo, status);
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.UPDATE_MENTOR_PROFILE_FAIL_CODE, ResponseMessage.UPDATE_MENTOR_PROFILE_FAIL_CODE, e);
        }
    }

    /**
     * 멘토 프로필 생성 메서드
     */
    @Override
    public void saveMentorProfile(Long memberNo, MentorProfileDto mentorProfileDto) {
        try {
            // 1️⃣ 회원 정보 조회
            Member member = memberRepository.findById(memberNo).get();
            if (member == null) {
                throw new CustomException(ResponseStatusCode.MEMBER_MENTOR_NOT_FOUND, ResponseMessage.MEMBER_MENTOR_NOT_FOUND, null);
            }

            // 2️⃣ 카테고리 정보 조회
            Category category = categoryRepository.findById(mentorProfileDto.getCategoryNo()).get();
            if (category == null) {
                throw new CustomException(ResponseStatusCode.CATEGORY_NOT_FOUND, ResponseMessage.CATEGORY_NOT_FOUND, null);
            }

            // 3️⃣ 멘토 프로필 중복 확인
            if (mentorProfileRepository.findByMember(member) != null) {
                throw new CustomException(ResponseStatusCode.ALREADY_HAS_MENTOR_PROFILE, ResponseMessage.ALREADY_HAS_MENTOR_PROFILE, null);
            }

            // 4️⃣ 멘토 프로필 생성 및 저장
            MentorProfile mentorProfile = MentorProfile.toEntity(mentorProfileDto, member, category);
            mentorProfile.setMentorStatus(1); // 초기값 1로 등록
            mentorProfileRepository.save(mentorProfile);
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.CREATED_MENTOR_PROFILE_FAIL, ResponseMessage.CREATED_MENTOR_PROFILE_FAIL, e);
        }
    }
    /* 멘토링 전체활동 수 업데이트 */
    public Integer updateMentoringCount(Long memberNo) {
    	MentorProfile mentorProfile = mentorProfileRepository.findByMember_MemberNo(memberNo);
    	Integer mentoringCount = mentorProfile.getMentorMentoringCount();
    	
    	mentorProfile.setMentorMentoringCount(mentoringCount+1);
    	mentorProfileRepository.save(mentorProfile);
    	
    	return mentoringCount+1;
    }
    
    /* 멘토링 완료 활동 수 업데이트 */
    public Integer updateAcitityCount(Long memberNo) {
    	MentorProfile mentorProfile = mentorProfileRepository.findByMember_MemberNo(memberNo);
    	Integer mentorActivityCount = mentorProfile.getMentorActivityCount();
    	
    	mentorProfile.setMentorActivityCount(mentorActivityCount+1);
    	mentorProfileRepository.save(mentorProfile);
    	
    	return mentorActivityCount+1;
    }
    
    /*** 멘토 더미 프로필 생성 ***/
    @Override
	public void saveMentorDummyProfile(Long memberNo) {
    	Member member = memberRepository.findByMemberNo(memberNo);
		
    	MentorProfile mentorProfile = MentorProfile.builder()
                .member(member) // 멤버 정보 설정
                .category(null) // 카테고리 정보 설정
                .mentorCareer(null)
                .mentorIntroduce(null)
                .mentorImage(null)
                .mentorStatus(0) // 초기 상태가 없으면 1로 설정
                .mentorRating(0.0) // 초기 평점이 없으면 0.0으로 설정
                .mentorMentoringCount(0)
                .mentorFollowCount(0)
                .mentorActivityCount(0)
                .build();
    	
    	
	}
    
    /**
     * 멘토의 평균 점수를 반환하는 메서드
     */
    @Override
    public Double getAverageMentorRating(Long memberNo) {
        try {
            MentorProfile mentorProfile = mentorProfileRepository.findByMemberNo(memberNo);
            if (mentorProfile == null) {
                throw new CustomException(ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE, ResponseMessage.MENTOR_PROFILE_NOT_FOUND, null);
            }
            return mentorProfile.getMentorRating();
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE, ResponseMessage.MENTOR_PROFILE_NOT_FOUND, e);
        }
    }
    
    
    /**
     * 멘토의 mentor_rating 업데이트
     */
    @Transactional
    public void updateMentorRating(Long memberNo) {
        try {
            mentorProfileRepository.updateMentorRatingByMemberNo(memberNo);
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.UPDATE_MENTOR_PROFILE_FAIL_CODE, ResponseMessage.UPDATE_MENTOR_PROFILE_FAIL_CODE, e);
        }
    }

    /**
     * 멘토 프로필 전체 조회
     */
    @Override
    public Page<MentorProfileDto> getMentorAll(int page, int size) {
    	try {
    		Pageable pageable = PageRequest.of(page, size);
    		Page<MentorProfile> mentorProfiles = mentorProfileRepository.findAll(pageable);
    		List<MentorProfileDto> mentorProfileDtos = new ArrayList<>();
    		
    		for (MentorProfile mentorProfile : mentorProfiles) {
    			mentorProfileDtos.add(MentorProfileDto.toDto(mentorProfile));
			}
    		
    		return new PageImpl<>(mentorProfileDtos, pageable, mentorProfiles.getTotalElements());
    	} catch (Exception e) {
    		throw new CustomException(ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE, ResponseMessage.MENTOR_PROFILE_NOT_FOUND, e);
    	}
    }
    
    /**
     * 멘토 프로필 상태별 조회
     */
    @Override
    public Page<MentorProfileDto> getMentorsByStatus(int status, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<MentorProfile> mentorProfiles = mentorProfileRepository.findByMentorStatus(status, pageable);
            return mentorProfiles.map(MentorProfileDto::toDto);
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE, ResponseMessage.MENTOR_PROFILE_NOT_FOUND, e);
        }
    }

    /**
     * 멘토 프로필 검색
     */
    @Override
    public Page<MentorProfileDto> getMentorProfiles(String keyword, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<MentorProfile> mentorProfiles = mentorProfileRepository.searchMentorProfiles(keyword, pageable);
            return mentorProfiles.map(MentorProfileDto::toDto);
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE, ResponseMessage.MENTOR_PROFILE_NOT_FOUND, e);
        }
    }

    /**
     * 카테고리 번호로 멘토 프로필 조회
     */
    @Override
    public Page<MentorProfileDto> getMentorProfilesByCategoryNo(Long categoryNo, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<MentorProfile> mentorProfiles = mentorProfileRepository.findByCategoryNo(categoryNo, pageable);
            return mentorProfiles.map(MentorProfileDto::toDto);
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE, ResponseMessage.MENTOR_PROFILE_NOT_FOUND, e);
        }
    }

    
    
    /**
     * 프로필 이미지 업로드 메서드
     */
    @Override
    public void uploadMentorProfileImage(Long mentorProfileNo, MultipartFile file) {
        try {
            MentorProfile mentorProfile = mentorProfileRepository.findById(mentorProfileNo).orElse(null);
            if (mentorProfile == null) {
                throw new CustomException(ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE, ResponseMessage.MENTOR_PROFILE_NOT_FOUND, null);
            }

            File directory = new File(IMAGE_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + originalFilename;
            File saveFile = new File(IMAGE_PATH + fileName);
            file.transferTo(saveFile);

            mentorProfile.setMentorImage("/mentor-profile-images/" + fileName);
            mentorProfileRepository.save(mentorProfile);
        } catch (IOException e) {
            throw new CustomException(ResponseStatusCode.IMAGE_UPLOAD_FAIL, ResponseMessage.IMAGE_UPLOAD_FAIL, e);
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.IMAGE_UPLOAD_FAIL, ResponseMessage.IMAGE_UPLOAD_FAIL, e);
        }
    }

    /**
     * 프로필 이미지 URL 조회 메서드
     */
    @Override
    public void updateMentorProfile(Long mentorProfileNo, MentorProfileDto mentorProfileDto) {
        try {
            // 🔥 멘토 프로필 조회
            MentorProfile mentorProfile = mentorProfileRepository.findById(mentorProfileNo)
                    .orElseThrow(() -> new CustomException(
                            ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE,
                            ResponseMessage.MENTOR_PROFILE_NOT_FOUND, null
                    ));
            
            // 🔥 카테고리 조회
            Category category = categoryRepository.findById(mentorProfileDto.getCategoryNo())
                    .orElseThrow(() -> new CustomException(
                            ResponseStatusCode.CATEGORY_NOT_FOUND,
                            ResponseMessage.CATEGORY_NOT_FOUND, null
                    ));
            
            // 🔥 프로필 정보 업데이트
            mentorProfile.setMentorCareer(mentorProfileDto.getMentorCareer());
            mentorProfile.setMentorIntroduce(mentorProfileDto.getMentorIntroduce());
            mentorProfile.setMentorImage(mentorProfileDto.getMentorImage());
            mentorProfile.setCategory(category); // 카테고리 설정
            
            // 🔥 저장
            mentorProfileRepository.save(mentorProfile);
            
        } catch (CustomException e) {
            throw e; // 그대로 예외 던지기
        } catch (Exception e) {
            throw new CustomException(
                ResponseStatusCode.UPDATE_MENTOR_PROFILE_FAIL_CODE, 
                ResponseMessage.UPDATE_MENTOR_PROFILE_FAIL_CODE, 
                e
            );
        }
    }
    
    
    @Override
    public String getMentorProfileImageUrl(Long mentorProfileNo) {
        try {
            // 🔥 멘토 프로필 조회
            MentorProfile mentorProfile = mentorProfileRepository.findById(mentorProfileNo)
                    .orElseThrow(() -> new CustomException(
                            ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE, 
                            ResponseMessage.MENTOR_PROFILE_NOT_FOUND, null
                    ));

            // 🔥 멘토 이미지 URL 반환
            return mentorProfile.getMentorImage();
        } catch (CustomException e) {
            // ⚠️ CustomException이 발생한 경우 그대로 예외를 던짐
            throw e;
        } catch (Exception e) {
            // ⚠️ 예기치 않은 예외가 발생한 경우 서버 내부 오류로 CustomException을 던짐
            throw new CustomException(
                ResponseStatusCode.INTERNAL_SERVER_ERROR, 
                ResponseMessage.INTERNAL_SERVER_ERROR, 
                e
            );
        }
    }

    
    
    
    
    
    
    
    @Override
    public Integer getMentorMentoringCount(Long mentorProfileNo) {
        try {
            Integer count = mentorProfileRepository.findMentorMentoringCountByProfileNo(mentorProfileNo);
            if (count == null) {
                throw new CustomException(ResponseStatusCode.READ_MENTOR_PROFILE_FAIL, ResponseMessage.READ_MENTOR_PROFILE_FAIL, null);
            }
            return count;
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.READ_MENTOR_PROFILE_FAIL, ResponseMessage.READ_MENTOR_PROFILE_FAIL, e);
        }
    }

    @Override
    public Integer getMentorFollowCount(Long mentorProfileNo) {
        try {
            Integer count = mentorProfileRepository.findMentorFollowCountByProfileNo(mentorProfileNo);
            if (count == null) {
                throw new CustomException(ResponseStatusCode.READ_MENTOR_PROFILE_FAIL, ResponseMessage.READ_MENTOR_PROFILE_FAIL, null);
            }
            return count;
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.READ_MENTOR_PROFILE_FAIL, ResponseMessage.READ_MENTOR_PROFILE_FAIL, e);
        }
    }

    @Override
    public Integer getMentorActivityCount(Long mentorProfileNo) {
        try {
            Integer count = mentorProfileRepository.findMentorActivityCountByProfileNo(mentorProfileNo);
            if (count == null) {
                throw new CustomException(ResponseStatusCode.READ_MENTOR_PROFILE_FAIL, ResponseMessage.READ_MENTOR_PROFILE_FAIL, null);
            }
            return count;
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.READ_MENTOR_PROFILE_FAIL, ResponseMessage.READ_MENTOR_PROFILE_FAIL, e);
        }
    }

	
}
    

