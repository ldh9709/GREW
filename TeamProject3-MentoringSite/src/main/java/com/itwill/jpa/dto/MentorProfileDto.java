package com.itwill.jpa.dto;

import com.itwill.jpa.entity.MentorProfile;

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
    private Integer mentorRating;
    private Integer mentorMentoringCount;
    private String mentorImage;
    private Integer mentorActivityCount;
    private Integer mentorFollowCount;
    private UserDto user;
    private CategoryDto category;

    /*
     * Entity -> DTO
     */
    public static MentorProfileDto toDto(MentorProfile mentorProfileEntity) {
        return MentorProfileDto.builder()
                .mentorProfileNo(mentorProfileEntity.getMentorProfileNo())
                .mentorIntroduce(mentorProfileEntity.getMentorIntroduce())
                .mentorRating(mentorProfileEntity.getMentorRating())
                .mentorMentoringCount(mentorProfileEntity.getMentorMentoringCount())
                .mentorImage(mentorProfileEntity.getMentorImage())
                .mentorActivityCount(mentorProfileEntity.getMentorActivityCount())
                .mentorFollowCount(mentorProfileEntity.getMentorFollowCount())
                .user(UserDto.toDto(mentorProfileEntity.getUser()))
                .category(CategoryDto.toDto(mentorProfileEntity.getCategory()))
                .build();
    }

    
}
