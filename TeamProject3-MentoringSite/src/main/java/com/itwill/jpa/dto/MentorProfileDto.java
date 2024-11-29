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
    private String mentorIntroduce;
    private String mentorImage;
    private MemberDto member;
    private CategoryDto category;

    /*
     * Entity -> DTO
     */
    public static MentorProfileDto toDto(MentorProfile mentorProfileEntity) {
        return MentorProfileDto.builder()
                .mentorIntroduce(mentorProfileEntity.getMentorIntroduce())
                .mentorImage(mentorProfileEntity.getMentorImage())
                .member(MemberDto.toDto(mentorProfileEntity.getMember()))
                .category(CategoryDto.toDto(mentorProfileEntity.getCategory()))
                .build();
    }

    
}
