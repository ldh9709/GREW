package com.itwill.jpa.dto.user_information;

import com.itwill.jpa.entity.user_information.Member;
import com.itwill.jpa.entity.user_information.MentorBoard;

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
    private MemberDto member;

    /*
     * Entity -> DTO
     */
    public static MentorBoardDto toDto(MentorBoard mentorBoardEntity) {
        return MentorBoardDto.builder()
        		.mentorBoardNo(mentorBoardEntity.getMentorBoardNo())
                .mentorBoardTitle(mentorBoardEntity.getMentorBoardTitle())
                .mentorBoardContent(mentorBoardEntity.getMentorBoardContent())
                .mentorBoardImage(mentorBoardEntity.getMentorBoardImage())
                .member(MemberDto.toDto(mentorBoardEntity.getMember()))
                .build();
    }

  
}
   
   

