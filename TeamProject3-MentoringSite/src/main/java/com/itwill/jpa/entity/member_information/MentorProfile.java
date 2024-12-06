package com.itwill.jpa.entity.member_information;


import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;


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
@Table(name = "mentor_profile")
public class MentorProfile {

    @Id
    @SequenceGenerator(name = "mentor_profile_no_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentor_profile_no_SEQ")
    
    @Column(name = "mentor_profile_no" )
    private Long mentorProfileNo;

    @Column(name = "mentor_introduce" ,nullable = false, length = 2000)
    private String mentorIntroduce; 
    
    @Column(name = "mentor_career" ,nullable = false, length = 1000)
    private String mentorCareer; 
    
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
    
    @Column(name = "mentor_status" ,nullable = false)
    private Integer mentorStatus;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no" )
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_no")
    private Category category;
    
    
    @PrePersist
    public void setValues() {
    	if (this.mentorRating == null) this.mentorRating =0;
        if (this.mentorMentoringCount == null) this.mentorMentoringCount = 0;
        if (this.mentorActivityCount == null) this.mentorActivityCount = 0;
        if (this.mentorFollowCount == null) this.mentorFollowCount = 0;
    }
    
}
