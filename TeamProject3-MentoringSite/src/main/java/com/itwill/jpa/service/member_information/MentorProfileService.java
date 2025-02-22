package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.dto.member_information.CareerDto;
import com.itwill.jpa.dto.member_information.MentorProfileDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.MentorProfile;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface MentorProfileService {
	
    /**
     * 멘토 프로필 생성
     */
    MentorProfile saveMentorProfile(Long memberNo, MentorProfileDto mentorProfileDto);
    
    /**
     * 멘토 더미 프로필 생성
     */
    MentorProfile saveMentorDummyProfile(Long memberNo);
    
	// 멘토링 전체활동 수 업데이트
	Integer updateMentoringCount(Long memberNo);
	
	// 멘토링 완료 활동 수 업데이트
	Integer updateAcitityCount(Long memberNo);
	
    /**
     * 멘토의 상태를 변경
     * status 변경할 상태 (1: 멘티, 2: 심사 중, 3: 멘토, 4: 탈퇴)
     */
	void updateMentorStatus(Long memberNo, int status);

    /**
     * 특정 멘토의 평균 평점을 반환
     */
    Double getAverageMentorRating(Long memberNo);

    /**
     * 특정 멘토의 평점을 업데이트
     *  삭제 금지 메서드
     */
    void updateMentorRating(Long memberNo);
    /* 멘토 전체 조회 */
    Page<MentorProfileDto> getMentorAll(int page, int size);
    
    /**
     * 특정 상태의 멘토 프로필을 페이지로 반환
     * status 조회할 멘토의 상태 (1: 멘티, 2: 심사 중, 3: 멘토, 4: 탈퇴)
     */
    Page<MentorProfileDto> getMentorsByStatus(int status, int page, int size);

    /**
     * 키워드를 기준으로 멘토 프로필을 검색
     */
    Page<MentorProfileDto> getMentorProfiles(String search, int page, int size);

    /**
     * 특정 카테고리에 속한 멘토 프로필을 페이지로 반환
     */
    Page<MentorProfileDto> getMentorProfilesByCategoryNo(Long categoryNo, int page, int size);

    /**
     * 멘토 프로필 이미지 업로드
     */
    String uploadMentorProfileImage(Long mentorProfileNo, MultipartFile file) throws Exception;

    /**
     * 멘토 프로필의 이미지 URL을 가져오기
     */
    String getMentorProfileImageUrl(Long mentorProfileNo);

    /**
     * 멘토 프로필 정보 수정
     */
    MentorProfile updateMentorProfile(Long mentorProfileNo, MentorProfileDto mentorProfileDto);

    /**
     * 멘토 프로필의 멘토링 횟수 조회
     */
    Integer getMentorMentoringCount(Long mentorProfileNo);

    /**
     * 멘토 프로필의 팔로우 수 조회
     */
    Integer getMentorFollowCount(Long mentorProfileNo);

    /**
     * 멘토 프로필의 활동 수 조회
     */
    Integer getMentorActivityCount(Long mentorProfileNo);
    
    //상세보기 1명
    MentorProfileDto getMentorProfileDetail(Long mentorProfileNo);
    
    
    //팔로우수 멘토링수 활동수 조회 리스트
    Page<MentorProfileDto> getMentorsByFollowCount(int page, int size);
    Page<MentorProfileDto> getMentorsByMentoringCount(int page, int size);
    Page<MentorProfileDto> getMentorsByActivityCount(int page, int size);

    //멤버 넘버로 멘토프로필 찾기
    MentorProfileDto getMentorByMemberNo(Long memberNo);
    

    //12월 24일 멘토 프로필 카테고리
 // 팔로우 순으로 소분류 카테고리별 멘토 리스트 조회
    Page<MentorProfileDto> getByParentCategoryOrderByFollowCount(Long parentCategoryNo, int page, int size);
    Page<MentorProfileDto> getByParentCategoryOrderByMentoringCount(Long parentCategoryNo, int page, int size);
    Page<MentorProfileDto> getByParentCategoryOrderByActivityCount(Long parentCategoryNo, int page, int size);

    Page<MentorProfileDto> getByCategoryNoOrderByFollowCount(Long categoryNo, int page, int size);
    Page<MentorProfileDto> getByCategoryNoOrderByMentoringCount(Long categoryNo, int page, int size);
    Page<MentorProfileDto> getByCategoryNoOrderByActivityCount(Long categoryNo, int page, int size);


    //별점 순으로 멘토 찾기
    List<MentorProfileDto> getMentorByRating();
    
    Long getMemberNoByMentorNo(Long mentorProfileNo);
    
}

