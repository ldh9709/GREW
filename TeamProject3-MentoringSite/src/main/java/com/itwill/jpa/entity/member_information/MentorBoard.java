package com.itwill.jpa.entity.member_information;


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
@Table(name = "mentorboard")
public class MentorBoard {

    @Id
    @SequenceGenerator(name = "mentorboard_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentorboard_SEQ")
    @Column(name = "mentor_board_no")
    private Long mentorBoardNo;

    @Column(name = "mentor_board_title" ,nullable = false)
    private String mentorBoardTitle;
    
    @Column(name = "mentor_board_content" ,nullable = false)
    private String mentorBoardContent;
    
    @Column(name = "mentor_board_image" ,nullable = false)
    private String mentorBoardImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no" ,nullable = false)
    private Member member;
    
    
    public static MentorBoard toEntity(MentorBoardDto mentorBoardDto) {
        return MentorBoard.builder()
        		.mentorBoardNo(mentorBoardDto.getMentorBoardNo())
                .mentorBoardTitle(mentorBoardDto.getMentorBoardTitle())
                .mentorBoardContent(mentorBoardDto.getMentorBoardContent())
                .mentorBoardImage(mentorBoardDto.getMentorBoardImage())
                .member(Member.toEntity(mentorBoardDto.getMember()))
                .build();
    }
}
