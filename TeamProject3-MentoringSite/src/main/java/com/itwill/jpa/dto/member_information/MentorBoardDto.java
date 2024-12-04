package com.itwill.jpa.dto.member_information;

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
    private Integer mentorBoardStatus;
    //private MemberDto member;
    private Long memberNo;

    /*
     * Entity -> DTO
     */
    public static MentorBoardDto toDto(MentorBoard mentorBoardEntity) {
        return MentorBoardDto.builder()
        		.mentorBoardNo(mentorBoardEntity.getMentorBoardNo())
                .mentorBoardTitle(mentorBoardEntity.getMentorBoardTitle())
                .mentorBoardContent(mentorBoardEntity.getMentorBoardContent())
                .mentorBoardImage(mentorBoardEntity.getMentorBoardImage())
                .mentorBoardStatus(mentorBoardEntity.getMentorBoardStatus())
                //.member(MemberDto.toDto(mentorBoardEntity.getMember()))
                .memberNo(mentorBoardEntity.getMember().getMemberNo())
                .build();
    }

  
}
   
   

