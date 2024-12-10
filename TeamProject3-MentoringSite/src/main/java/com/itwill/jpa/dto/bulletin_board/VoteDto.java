package com.itwill.jpa.dto.bulletin_board;

import java.time.LocalDate;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.bullentin_board.Vote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDto {

    private Long voteNo;         // vote_no (PK)
    private Integer voteType;     // vote_type
    private LocalDate voteDate;  // vote_date
    private Long memberNo;    // member_no (FK)
    private Long answerNo;    // answer_no (FK)

    /*
     * Entity -> DTO 변환 메소드
     */
    public static VoteDto toDto(Vote voteEntity) {

        return VoteDto.builder()
                .voteNo(voteEntity.getVoteNo())
                .voteType(voteEntity.getVoteType())
                .voteDate(voteEntity.getVoteDate())
                .memberNo(MemberDto.toDto(voteEntity.getMember()).getMemberNo())  
                .answerNo(AnswerDto.toDto(voteEntity.getAnswer()).getAnswerNo())  
                .build();
    }
}