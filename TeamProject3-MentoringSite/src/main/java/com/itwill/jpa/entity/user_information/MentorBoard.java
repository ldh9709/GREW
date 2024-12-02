package com.itwill.jpa.entity.user_information;


import com.itwill.jpa.dto.user_information.MentorBoardDto;

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
@Table(name = "MentorBoard")
public class MentorBoard {

    @Id
    @SequenceGenerator(name = "mentorboard_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentorboard_SEQ")
    @JoinColumn(name = "mentor_board_no")
    private Long mentorBoardNo;

    @JoinColumn(name = "mentor_board_title")
    private String mentorBoardTitle;
    
    @JoinColumn(name = "mentor_board_content")
    private String mentorBoardContent;
    
    @JoinColumn(name = "mentor_board_image")
    private String mentorBoardImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
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
