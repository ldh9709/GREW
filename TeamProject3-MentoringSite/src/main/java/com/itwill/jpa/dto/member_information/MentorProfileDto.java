package com.itwill.jpa.dto.member_information;

import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.member_information.MentorProfile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data

@NoArgsConstructor
@AllArgsConstructor
public class MentorProfileDto {
	private Long mentorProfileNo;
	
    private String mentorIntroduce;
    
    private String mentorCareer; 
    
    private Double mentorRating;
    
    private Integer mentorMentoringCount;
    
    private String mentorImage;
    
    private Integer mentorActivityCount;
    
    private Integer mentorFollowCount;
    private Integer mentorStatus;
    
    private Long memberNo;
    
    private Long categoryNo;

    /*
     * Entity -> DTO
     */
    public static MentorProfile toEntity(MentorProfileDto mentorProfileDto, Member member, Category category) {
        // 🔥 DTO 정보를 바탕으로 MentorProfile 엔티티 생성
        return MentorProfile.builder()
                .mentorProfileNo(mentorProfileDto.getMentorProfileNo())
                .mentorIntroduce(mentorProfileDto.getMentorIntroduce())
                .mentorCareer(mentorProfileDto.getMentorCareer())
                .mentorRating(mentorProfileDto.getMentorRating())
                .mentorMentoringCount(mentorProfileDto.getMentorMentoringCount())
                .mentorImage(mentorProfileDto.getMentorImage())
                .mentorActivityCount(mentorProfileDto.getMentorActivityCount())
                .mentorFollowCount(mentorProfileDto.getMentorFollowCount())
                .mentorStatus(mentorProfileDto.getMentorStatus())
                .member(member) // 🔥 멤버 정보 매핑
                .category(category) // 🔥 카테고리 정보 매핑
                .build();
    }
    public static MentorProfileDto toDto(MentorProfile mentorProfileEntity) {
        if (mentorProfileEntity == null) {
            // 명확한 예외 메시지를 던지도록 개선
            throw new IllegalArgumentException("mentorProfileEntity가 null입니다. MentorProfileDto로 변환할 수 없습니다.");
        }

        return MentorProfileDto.builder()
                .mentorProfileNo(mentorProfileEntity.getMentorProfileNo())
                .mentorIntroduce(mentorProfileEntity.getMentorIntroduce())
                .mentorCareer(mentorProfileEntity.getMentorCareer())
                .mentorRating(mentorProfileEntity.getMentorRating())
                .mentorMentoringCount(mentorProfileEntity.getMentorMentoringCount())
                .mentorImage(mentorProfileEntity.getMentorImage())
                .mentorActivityCount(mentorProfileEntity.getMentorActivityCount())
                .mentorFollowCount(mentorProfileEntity.getMentorFollowCount())
                .mentorStatus(mentorProfileEntity.getMentorStatus())
                .memberNo(mentorProfileEntity.getMember() != null ? mentorProfileEntity.getMember().getMemberNo() : null)
                .categoryNo(mentorProfileEntity.getCategory() != null ? mentorProfileEntity.getCategory().getCategoryNo() : null)
                .build();
    }

}
