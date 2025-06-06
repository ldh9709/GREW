package com.itwill.jpa.dto.member_information;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itwill.jpa.entity.member_information.Career;
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
    private String memberName;
    private Long categoryNo; 
    private String mentorIntroduce; 
    private String mentorImage; 
    private Integer mentorStatus; 
    private Double mentorRating; 
    private Integer mentorMentoringCount; 
    private Integer mentorFollowCount; 
    private Integer mentorActivityCount; 
    private String mentorHeadline;
    private String categoryName;
    
    private List<CareerDto> careerDtos;
    
    /**
     * 엔티티를 DTO로 변환하는 메서드
     * 
     * @param entity MentorProfile 엔티티
     * @return MentorProfileDto
     */
    
    /*출력용 dto변환*/
    public static MentorProfileDto toResponseDto(MentorProfile entity) {
        if (entity == null) {
            return null;
        }
       
        List<CareerDto> careers = new ArrayList<>();
        for (Career career : entity.getCareers()) {
        	careers.add(CareerDto.toDto(career));
		}
        
        return MentorProfileDto.builder()
                .mentorProfileNo(entity.getMentorProfileNo())
                .memberNo(entity.getMember().getMemberNo())
                .memberName(entity.getMember().getMemberName())
                .categoryNo(entity.getCategory().getCategoryNo())
                .mentorIntroduce(entity.getMentorIntroduce())
                .mentorImage(entity.getMentorImage())
                .mentorStatus(entity.getMentorStatus())
                .mentorRating(entity.getMentorRating())
                .mentorMentoringCount(entity.getMentorMentoringCount())
                .mentorFollowCount(entity.getMentorFollowCount())
                .mentorActivityCount(entity.getMentorActivityCount())
                .mentorHeadline(entity.getMentorHeadline())
                .categoryName(entity.getCategory().getCategoryName())
                .careerDtos(careers)
                .build();
    }
    public static MentorProfileDto toDto(MentorProfile entity) {
        if (entity == null) {
            return null;
        }

        return MentorProfileDto.builder()
                .mentorProfileNo(entity.getMentorProfileNo())
                .memberNo(entity.getMember().getMemberNo())
                .categoryNo(entity.getCategory().getCategoryNo())
                .mentorIntroduce(entity.getMentorIntroduce())
                .mentorImage(entity.getMentorImage())
                .mentorStatus(entity.getMentorStatus())
                .mentorRating(entity.getMentorRating())
                .mentorMentoringCount(entity.getMentorMentoringCount())
                .mentorFollowCount(entity.getMentorFollowCount())
                .mentorHeadline(entity.getMentorHeadline())
                .mentorActivityCount(entity.getMentorActivityCount())
                .build();
    }
    
    

}