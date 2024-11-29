package com.itwill.jpa.dto;

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
    private Long mentorBoardNo;
    private String mentorBoardTitle;
    private String mentorBoardContent;
    private String mentorBoardImage;
    private UserDto user;

    /*
     * Entity -> DTO
     */
    public static MentorBoardDto toDto(MentorBoard mentorBoardEntity) {
        return MentorBoardDto.builder()
                .mentorBoardNo(mentorBoardEntity.getMentorBoardNo())
                .mentorBoardTitle(mentorBoardEntity.getMentorBoardTitle())
                .mentorBoardContent(mentorBoardEntity.getMentorBoardContent())
                .mentorBoardImage(mentorBoardEntity.getMentorBoardImage())
                .user(UserDto.toDto(mentorBoardEntity.getUser()))
                .build();
    }

   
}
