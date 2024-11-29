package com.itwill.jpa.entity;


import com.itwill.jpa.dto.MentorProfileDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MentorProfile")
public class MentorProfile {

    @Id
    @SequenceGenerator(name = "mentorprofile_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentorprofile_seq")
    private Long mentorProfileNo;

    private String mentorIntroduce;
    private Integer mentorRating;
    private Integer mentorMentoringCount;
    private String mentorImage;
    private Integer mentorActivityCount;
    private Integer mentorFollowCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no")
    private Category category;
    
    public static MentorProfile toEntity(MentorProfileDto mentorProfileDto) {
        return MentorProfile.builder()
                .mentorProfileNo(mentorProfileDto.getMentorProfileNo())
                .mentorIntroduce(mentorProfileDto.getMentorIntroduce())
                .mentorRating(mentorProfileDto.getMentorRating())
                .mentorMentoringCount(mentorProfileDto.getMentorMentoringCount())
                .mentorImage(mentorProfileDto.getMentorImage())
                .mentorActivityCount(mentorProfileDto.getMentorActivityCount())
                .mentorFollowCount(mentorProfileDto.getMentorFollowCount())
                .user(User.toEntity(mentorProfileDto.getUser()))
                .category(Category.toEntity(mentorProfileDto.getCategory()))
                .build();
    }
}
