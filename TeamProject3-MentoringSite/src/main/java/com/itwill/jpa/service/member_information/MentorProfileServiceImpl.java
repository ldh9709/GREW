package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.chatting_review.ChatRoomDto;
import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.dto.member_information.CareerDto;
import com.itwill.jpa.dto.member_information.MentorProfileDto;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.member_information.Career;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.member_information.MentorProfile;
import com.itwill.jpa.exception.CustomException;
import com.itwill.jpa.repository.member_information.CareerRepository;
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
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class MentorProfileServiceImpl implements MentorProfileService {

    private static final String IMAGE_PATH = "C:/upload/mentor-profile/";
    
    @Autowired
    private MentorProfileRepository mentorProfileRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CareerRepository careerRepository;
    @Autowired
    private CareerService careerService;
  
    
    
    //상세보기 12-19일
    @Override
    public MentorProfileDto getMentorProfileDetail(Long mentorProfileNo) {
        // 🔥 엔티티 조회
        MentorProfile mentorProfile = mentorProfileRepository.findDetailedProfileByNo(mentorProfileNo);
        
        // 🔥 엔티티가 존재하지 않으면 예외 발생
        if (mentorProfile == null) {
            throw new CustomException(
                ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE, 
                ResponseMessage.MENTOR_PROFILE_NOT_FOUND, 
                null
            );
        }

        // 🔥 엔티티 → DTO 변환
        return MentorProfileDto.toResponseDto(mentorProfile);
    }

    
    
    
    
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
    public MentorProfile saveMentorProfile(Long memberNo, MentorProfileDto mentorProfileDto) {
    	
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
            mentorProfile.setMentorStatus(2); // 초기값 2로 등록
            mentorProfileRepository.save(mentorProfile);
            
            return mentorProfile; 
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
	public MentorProfile saveMentorDummyProfile(Long memberNo) {
    	//번호로 멤버 찾기
    	Member member = memberRepository.findByMemberNo(memberNo);
    	
    	//찾은 번호로 프로필 더미 데이터 작성
    	MentorProfileDto mentorProfileDto =	
    			MentorProfileDto.builder()
				                .memberNo(memberNo) // 멤버 정보 설정
				                .categoryNo(26L) // 카테고리 정보 설정
//				                .mentorCareer("경력을 입력해주세요.")
				                .mentorIntroduce("소개글을 입력해주세요.")
				                .mentorImage(null)
				                .mentorStatus(2) // 초기 상태가 없으면 2로 설정
				                .mentorRating(0.0) // 초기 평점이 없으면 0.0으로 설정
				                .mentorMentoringCount(0)
				                .mentorFollowCount(0)
				                .mentorActivityCount(0)
				                .mentorHeadline("한 줄 소개글을 입력해주세요.")
				                .build();
    	//더미 데이터에 넣은 카테고리 번호로 객체 찾기
    	Category findCategory = categoryRepository.findByCategoryNo(26L);
    	
    	//Entity로 변환
    	MentorProfile mentorProfile = MentorProfile.toEntity(mentorProfileDto, member, findCategory);
    	
    	//저장
    	MentorProfile saveMentor = mentorProfileRepository.save(mentorProfile);
    	
    	return saveMentor;
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
    @Transactional
    @Override
    public Page<MentorProfileDto> getMentorAll(int page, int size) {
    	try {
    		Pageable pageable = PageRequest.of(page, size);
    		Page<MentorProfile> mentorProfiles = mentorProfileRepository.findAll(pageable);
    		List<MentorProfileDto> mentorProfileDtos = new ArrayList<>();
    		
    		for (MentorProfile mentorProfile : mentorProfiles) {
    			mentorProfileDtos.add(MentorProfileDto.toResponseDto(mentorProfile));
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
            return mentorProfiles.map(MentorProfileDto::toResponseDto);
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE, ResponseMessage.MENTOR_PROFILE_NOT_FOUND, e);
        }
    }

    /**
     * 멘토 프로필 검색
     */
    @Override
    public Page<MentorProfileDto> getMentorProfiles(String search, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<MentorProfile> mentorProfiles = mentorProfileRepository.searchMentorProfiles(search, pageable);
            return mentorProfiles.map(MentorProfileDto::toResponseDto);
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
            return mentorProfiles.map(MentorProfileDto::toResponseDto);
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE, ResponseMessage.MENTOR_PROFILE_NOT_FOUND, e);
        }
    }

    
    
    /**
     * 프로필 이미지 업로드 메서드
     * @return 
     */
    @Override
    public String uploadMentorProfileImage(Long mentorProfileNo, MultipartFile file) {
        try {
        	MentorProfile mentorProfile = mentorProfileRepository.findById(mentorProfileNo).orElse(null);
            if (mentorProfile == null) {
                throw new CustomException(ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE, ResponseMessage.MENTOR_PROFILE_NOT_FOUND, null);
            }
            String fileName = file.getOriginalFilename();
            String filePath = IMAGE_PATH + mentorProfileNo + "/" + fileName;

            // 4️⃣ 디렉토리 생성 (존재하지 않으면)
            File directory = new File(IMAGE_PATH + mentorProfileNo + "/");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 5️⃣ 파일 저장
            file.transferTo(new File(filePath));

            // 6️⃣ 저장된 이미지 URL 생성
            String imageUrl = "/upload/mentor-profile/" + mentorProfileNo + "/" + fileName;
            // 7️⃣ 멘토 프로필에 이미지 URL 저장
            mentorProfile.setMentorImage(imageUrl);
            mentorProfileRepository.save(mentorProfile);
            
            // 8️⃣ 업로드된 이미지 URL 반환
            return imageUrl;
        } catch (IOException e) {
            throw new CustomException(ResponseStatusCode.IMAGE_UPLOAD_FAIL, ResponseMessage.IMAGE_UPLOAD_FAIL, e);
        }
    }

    /**
     * 멘토 프로필 수정 메소드
     */
    @Override
    public MentorProfile updateMentorProfile(Long mentorProfileNo, MentorProfileDto mentorProfileDto) {
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
//            mentorProfile.setMentorCareer(mentorProfileDto.getMentorCareer());
            mentorProfile.setMentorIntroduce(mentorProfileDto.getMentorIntroduce());
            mentorProfile.setMentorImage(mentorProfileDto.getMentorImage());
            mentorProfile.setMentorStatus(2); // 2로 설정
            System.out.println(">>>>> updateMentorProfile : " + mentorProfileDto.getMentorImage());
            mentorProfile.setCategory(category); // 카테고리 설정
            careerService.updateCareer(mentorProfileDto.getCareerDtos());
            
            // 🔥 저장
            return mentorProfileRepository.save(mentorProfile);
            
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



    
    @Override
    public Page<MentorProfileDto> getMentorsByFollowCount(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MentorProfile> mentorProfiles = mentorProfileRepository.findByOrderByMentorFollowCountDesc(pageable);
        return mentorProfiles.map(MentorProfileDto::toResponseDto);
    }

    @Override
    public Page<MentorProfileDto> getMentorsByMentoringCount(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MentorProfile> mentorProfiles = mentorProfileRepository.findByOrderByMentorMentoringCountDesc(pageable);
        return mentorProfiles.map(MentorProfileDto::toResponseDto);
    }

    @Override
    public Page<MentorProfileDto> getMentorsByActivityCount(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MentorProfile> mentorProfiles = mentorProfileRepository.findByOrderByMentorActivityCountDesc(pageable);
        return mentorProfiles.map(MentorProfileDto::toResponseDto);
    }




    //멤버 넘버로 멘토 프로필 찾기
	@Override
	public MentorProfileDto getMentorByMemberNo(Long memberNo) {
		MentorProfile mentor = mentorProfileRepository.findByMember_MemberNo(memberNo);
		return MentorProfileDto.toResponseDto(mentor);
	}




	//별점 순으로 멘토 찾기
	@Override
	public List<MentorProfileDto> getMentorByRating() {
		List<MentorProfile> profiles= mentorProfileRepository.findByOrderByMentorRatingDesc();
		List<MentorProfileDto> profileDtos = new ArrayList<>();
		for(MentorProfile profile : profiles) {
			profileDtos.add(MentorProfileDto.toResponseDto(profile));
		}
		return profileDtos;
	}
    
    @Override
    public Long getMemberNoByMentorNo(Long mentorProfileNo) {
    	MentorProfile mentor = mentorProfileRepository.findMemberNoByMentorProfileNo(mentorProfileNo);
    	Long mentorNo = mentor.getMember().getMemberNo();
    	return mentorNo;
    }
    
	
	//12월 24일 멘토 프로필 카테고리
	@Override
	public Page<MentorProfileDto> getByParentCategoryOrderByFollowCount(Long parentCategoryNo, int page, int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    Page<MentorProfile> mentorProfiles = mentorProfileRepository.findByParentCategoryOrderByFollowCount(parentCategoryNo, pageable);
	    return mentorProfiles.map(MentorProfileDto::toResponseDto);
	}

	@Override
	public Page<MentorProfileDto> getByParentCategoryOrderByMentoringCount(Long parentCategoryNo, int page, int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    Page<MentorProfile> mentorProfiles = mentorProfileRepository.findByParentCategoryOrderByMentoringCount(parentCategoryNo, pageable);
	    return mentorProfiles.map(MentorProfileDto::toResponseDto);
	}

	@Override
	public Page<MentorProfileDto> getByParentCategoryOrderByActivityCount(Long parentCategoryNo, int page, int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    Page<MentorProfile> mentorProfiles = mentorProfileRepository.findByParentCategoryOrderByActivityCount(parentCategoryNo, pageable);
	    return mentorProfiles.map(MentorProfileDto::toResponseDto);
	}


    @Override
    public Page<MentorProfileDto> getByCategoryNoOrderByFollowCount(Long categoryNo, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MentorProfile> mentors = mentorProfileRepository.findByCategoryNoOrderByFollowCount(categoryNo, pageable);
        return mentors.map(MentorProfileDto::toResponseDto);
    }

    @Override
    public Page<MentorProfileDto> getByCategoryNoOrderByMentoringCount(Long categoryNo, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MentorProfile> mentors = mentorProfileRepository.findByCategoryNoOrderByMentoringCount(categoryNo, pageable);
        return mentors.map(MentorProfileDto::toResponseDto);
    }

    @Override
    public Page<MentorProfileDto> getByCategoryNoOrderByActivityCount(Long categoryNo, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MentorProfile> mentors = mentorProfileRepository.findByCategoryNoOrderByActivityCount(categoryNo, pageable);
        return mentors.map(MentorProfileDto::toResponseDto);
    }
}