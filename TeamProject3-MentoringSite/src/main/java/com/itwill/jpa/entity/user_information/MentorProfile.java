package com.itwill.jpa.entity.user_information;


import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import com.itwill.jpa.dto.user_information.MentorProfileDto;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentorprofile_SEQ")
    
    @JoinColumn(name = "mentor_profile_No")
    private Long mentorProfileNo;

    @JoinColumn(name = "mentor_introduce")
    private String mentorIntroduce; 
    
    @JoinColumn(name = "mentor_rating")
    private Integer mentorRating;
    
    @JoinColumn(name = "mentor_mentoring_count")
    private Integer mentorMentoringCount;
    
    @JoinColumn(name = "mentor_image")
    private String mentorImage;
   
    @JoinColumn(name = "mentor_activity_count")
    private Integer mentorActivityCount;
    
    @JoinColumn(name = "mentor_follow_count")
    private Integer mentorFollowCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no")
    private Category category;
    
    
    @PrePersist
    public void setValues() {
    	if (this.mentorRating == null) this.mentorRating =0;
        if (this.mentorMentoringCount == null) this.mentorMentoringCount = 0;
        if (this.mentorActivityCount == null) this.mentorActivityCount = 0;
        if (this.mentorFollowCount == null) this.mentorFollowCount = 0;
    }
    
    public static MentorProfile toEntity(MentorProfileDto mentorProfileDto) {
        return MentorProfile.builder()
        		.mentorProfileNo(mentorProfileDto.getMentorProfileNo())
        		.mentorIntroduce(mentorProfileDto.getMentorIntroduce())
        		.mentorMentoringCount(mentorProfileDto.getMentorMentoringCount())
        		.mentorImage(mentorProfileDto.getMentorImage())
                .mentorActivityCount(mentorProfileDto.getMentorActivityCount())
                .mentorFollowCount(mentorProfileDto.getMentorFollowCount())
                .member(Member.toEntity(mentorProfileDto.getMember()))
                .category(Category.toEntity(mentorProfileDto.getCategory()))
                .build();
    }
}
