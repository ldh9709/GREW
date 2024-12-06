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
    
    private Integer mentorRating;
    
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
    public static MentorProfileDto toDto(MentorProfile mentorProfileEntity) {
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
                .memberNo(mentorProfileEntity.getMember().getMemberNo())
                .categoryNo(mentorProfileEntity.getCategory().getCategoryNo())
                .build();
    }

    
}
