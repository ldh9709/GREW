package com.itwill.jpa.dto;

import java.time.LocalDate;

import com.itwill.jpa.entity.Vote;

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
    private String voteType;     // vote_type
    private LocalDate voteDate;  // vote_date
    private MemberDto member;    // user_no (FK)
    private AnswerDto answer;    // answer_no (FK)

    /*
     * Entity -> DTO 변환 메소드
     */
    public static VoteDto toDto(Vote voteEntity) {

        return VoteDto.builder()
                .voteNo(voteEntity.getVoteNo())
                .voteType(voteEntity.getVoteType())
                .voteDate(voteEntity.getVoteDate())
                .member(MemberDto.toDto(voteEntity.getMember()))  
                .answer(AnswerDto.toDto(voteEntity.getAnswer()))  
                .build();
    }
}
