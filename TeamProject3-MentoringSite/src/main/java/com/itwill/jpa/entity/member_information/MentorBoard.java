package com.itwill.jpa.entity.member_information;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.itwill.jpa.dto.member_information.MentorBoardDto;

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
@Table(name = "mentor_board")
public class MentorBoard {

    @Id
    @SequenceGenerator(name = "mentor_board_no_SEQ",initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentor_board_no_SEQ")
    @Column(name = "mentor_board_no")
    private Long mentorBoardNo; //PK 시퀀스로 자동생성 

    @Column(name = "mentor_board_title")
    private String mentorBoardTitle; // 글 제목
    
    @Column(name = "mentor_board_content", length = 4000)
    private String mentorBoardContent; // 글 본문
    
    @Column(name = "mentor_board_image")
    private String mentorBoardImage;   // **이미지 경로(URL) 저장**

    @Column(name = "mentor_board_date")
    private LocalDateTime mentorBoardDate;   // 날짜 
    
    @Column(name = "mentor_board_views")
    private Integer mentorBoardViews;   // 조회수 
    
    @Column(name = "mentor_board_status")
    private Integer mentorBoardStatus;   //답글 삭제 여부 (1또는 2)
    


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    @JsonBackReference
    private Member member;
    
    /* 초기값 설정 */
    @PrePersist
    public void setDefaultValues() {
    	if(this.mentorBoardImage==null) this.mentorBoardImage = "default.jpg";
    	if(this.mentorBoardDate==null) this.mentorBoardDate = LocalDateTime.now();
    	if(this.mentorBoardStatus==null||this.mentorBoardStatus==0) this.mentorBoardStatus = 1;
    	if(this.mentorBoardViews == null) this.mentorBoardViews = 0;
    }
    
    public static MentorBoard toEntity(MentorBoardDto mentorBoardDto) {
    	
    	MentorProfile mentorProfile = MentorProfile.builder()
    			.category(Category.builder()
    					.categoryNo(mentorBoardDto.getCategoryNo())
    					.categoryName(mentorBoardDto.getCategoryName())
    					.build())
    			.build();
    	
    	Member member = Member.builder()
				.memberNo(mentorBoardDto.getMemberNo())
				.mentorProfile(mentorProfile)
				.build();
 
    	
    	
        return MentorBoard.builder()
        		.mentorBoardNo(mentorBoardDto.getMentorBoardNo())
                .mentorBoardTitle(mentorBoardDto.getMentorBoardTitle())
                .mentorBoardContent(mentorBoardDto.getMentorBoardContent())
                .mentorBoardImage(mentorBoardDto.getMentorBoardImage())
                .mentorBoardStatus(mentorBoardDto.getMentorBoardStatus())
                .mentorBoardViews(mentorBoardDto.getMentorBoardViews())
                .mentorBoardStatus(mentorBoardDto.getMentorBoardStatus())
                .member(member)
                .build();
    }
}
