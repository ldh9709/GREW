package com.itwill.jpa.dto.member_information;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itwill.jpa.entity.member_information.MentorProfile;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentorProfileDto {

    private Long mentorProfileNo; 
    private Long memberNo; 
    private Long categoryNo; 
    private String mentorCareer;
    private String mentorIntroduce; 
    private String mentorImage; 
    private Integer mentorStatus; 
    private Double mentorRating; 
    private Integer mentorMentoringCount; 
    private Integer mentorFollowCount; 
    private Integer mentorActivityCount; 

    /**
     * 엔티티를 DTO로 변환하는 메서드
     * 
     * @param entity MentorProfile 엔티티
     * @return MentorProfileDto
     */
    public static MentorProfileDto toDto(MentorProfile entity) {
        if (entity == null) {
            return null;
        }

        return MentorProfileDto.builder()
                .mentorProfileNo(entity.getMentorProfileNo())
                .memberNo(entity.getMember().getMemberNo())
                .categoryNo(entity.getCategory().getCategoryNo())
                .mentorCareer(entity.getMentorCareer())
                .mentorIntroduce(entity.getMentorIntroduce())
                .mentorImage(entity.getMentorImage())
                .mentorStatus(entity.getMentorStatus())
                .mentorRating(entity.getMentorRating())
                .mentorMentoringCount(entity.getMentorMentoringCount())
                .mentorFollowCount(entity.getMentorFollowCount())
                .mentorActivityCount(entity.getMentorActivityCount())
                .build();
    }
}