package com.itwill.jpa.entity.member_information;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itwill.jpa.dto.member_information.MentorBoardDto;
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
@Table(name = "mentor_profile")
public class MentorProfile {
    
	@Id
	@SequenceGenerator(name = "mentor_profile_no_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentor_profile_no_SEQ")
    @Column(name="mentor_profile_no")
    private Long mentorProfileNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "member_no")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "category_no")
    private Category category;

    @OneToMany(mappedBy = "mentorProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Career> careers = new ArrayList<>();

    @Column(name="mentor_introduce")
    private String mentorIntroduce;

    @Column(name="mentor_image")
    private String mentorImage;

    @Column(name="mentor_status")
    private Integer mentorStatus;

    @Column(name="mentor_rating", precision = 2)
    private Double mentorRating;

    @Column(name="mentor_mentoring_count")
    private Integer mentorMentoringCount;

    @Column(name="mentor_follow_count")
    private Integer mentorFollowCount;

    @Column(name="mentor_activity_count")
    private Integer mentorActivityCount;
    
    //멘토 한줄 소개
    @Column(name="mentor_headline")
    private String mentorHeadline;

    /**
     * DTO를 엔티티로 변환하는 메서드
     * 
     * @param dto MentorProfileDto
     * @param member Member 엔티티
     * @param category Category 엔티티
     * @return MentorProfile 엔티티
     */
    public static MentorProfile toEntity(MentorProfileDto mentorProfileDto, Member member, Category category) {
        return MentorProfile.builder()
                .mentorProfileNo(mentorProfileDto.getMentorProfileNo())
                .member(member) // 멤버 정보 설정
                .category(category) // 카테고리 정보 설정
                .mentorIntroduce(mentorProfileDto.getMentorIntroduce())
                .mentorImage(mentorProfileDto.getMentorImage())
                .mentorStatus(mentorProfileDto.getMentorStatus() != null ? mentorProfileDto.getMentorStatus() : 1) // 초기 상태가 없으면 1로 설정
                .mentorRating(mentorProfileDto.getMentorRating() != null ? mentorProfileDto.getMentorRating() : 0.0) // 초기 평점이 없으면 0.0으로 설정
                .mentorMentoringCount(mentorProfileDto.getMentorMentoringCount() != null ? mentorProfileDto.getMentorMentoringCount() : 0)
                .mentorFollowCount(mentorProfileDto.getMentorFollowCount() != null ? mentorProfileDto.getMentorFollowCount() : 0)
                .mentorActivityCount(mentorProfileDto.getMentorActivityCount() != null ? mentorProfileDto.getMentorActivityCount() : 0)
                .mentorHeadline(mentorProfileDto.getMentorHeadline())
                .build();
    }
    
    //경력 추가
    public void addCareers(Career career) {
    	careers.add(career);
    	career.setMentorProfile(this);
    	
    }
}
