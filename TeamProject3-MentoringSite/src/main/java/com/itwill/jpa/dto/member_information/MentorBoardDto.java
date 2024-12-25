package com.itwill.jpa.dto.member_information;

import java.time.LocalDateTime;
import java.util.List;

import com.itwill.jpa.entity.member_information.Follow;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.member_information.MentorBoard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorBoardDto {
	private Long mentorBoardNo;
	
    private String mentorBoardTitle;
    private String mentorBoardContent;
    private String mentorBoardImage;
    private LocalDateTime mentorBoardDate;
    private Integer mentorBoardViews;
    private Integer mentorBoardStatus;
    //private MemberDto member;
    private Long memberNo;
    private Long categoryNo;
    /*
     * Entity -> DTO
     */
    public static MentorBoardDto toDto(MentorBoard mentorBoardEntity) {
        return MentorBoardDto.builder()
        		.mentorBoardNo(mentorBoardEntity.getMentorBoardNo())
                .mentorBoardTitle(mentorBoardEntity.getMentorBoardTitle())
                .mentorBoardContent(mentorBoardEntity.getMentorBoardContent())
                .mentorBoardImage(mentorBoardEntity.getMentorBoardImage())
                .mentorBoardDate(mentorBoardEntity.getMentorBoardDate())
                .mentorBoardViews(mentorBoardEntity.getMentorBoardViews())
                .mentorBoardStatus(mentorBoardEntity.getMentorBoardStatus())
                //.member(MemberDto.toDto(mentorBoardEntity.getMember()))
                .memberNo(mentorBoardEntity.getMember().getMemberNo())
                .categoryNo(mentorBoardEntity.getCategory().getCategoryNo())
                .build();
    }

  
}
   
   

