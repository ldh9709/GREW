package com.itwill.jpa.entity.member_information;


import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import com.itwill.jpa.dto.member_information.MentorProfileDto;

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
    @SequenceGenerator(name = "mentorprofile_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentorprofile_SEQ")
    
    @Column(name = "mentor_profile_No" )
    private Long mentorProfileNo;

    @Column(name = "mentor_introduce" ,nullable = false)
    private String mentorIntroduce; 
    
    @Column(name = "mentor_rating" ,nullable = false)
    private Integer mentorRating;
    
    @Column(name = "mentor_mentoring_count" ,nullable = false)
    private Integer mentorMentoringCount;
    
    @Column(name = "mentor_image" ,nullable = false)
    private String mentorImage;
   
    @Column(name = "mentor_activity_count" ,nullable = false)
    private Integer mentorActivityCount;
    
    @Column(name = "mentor_follow_count" ,nullable = false)
    private Integer mentorFollowCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no" )
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
