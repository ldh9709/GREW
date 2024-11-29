package com.itwill.jpa.dto;

import com.itwill.jpa.entity.Member;
import com.itwill.jpa.entity.MentorBoard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorBoardDto {
    private String mentorBoardTitle;
    private String mentorBoardContent;
    private String mentorBoardImage;
    private MemberDto member;

    /*
     * Entity -> DTO
     */
    public static MentorBoardDto toDto(MentorBoard mentorBoardEntity) {
        return MentorBoardDto.builder()
                .mentorBoardTitle(mentorBoardEntity.getMentorBoardTitle())
                .mentorBoardContent(mentorBoardEntity.getMentorBoardContent())
                .mentorBoardImage(mentorBoardEntity.getMentorBoardImage())
                .member(MemberDto.toDto(mentorBoardEntity.getMember()))
                .build();
    }

  
}
   
   

