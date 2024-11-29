package com.itwill.jpa.dto;

import java.time.LocalDate;

import com.itwill.jpa.entity.Answer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {

    private Long answerNo;         // answer_no (PK)
    private String answerContent;  // answer_content
    private LocalDate answerTime;  // answer_time
    private String answerAccept;   // answer_accept (채택여부)
    private String answerStatus;   // answer_status (답글삭제여부, 'active' or 'inactive')
    private MemberDto member;           // user_no (FK)
    private InquiryDto inquiry;        // inquiry_no (FK)

    /*
     * Entity -> DTO 변환 메소드
     */
    public static AnswerDto toDto(Answer answerEntity) {

        return AnswerDto.builder()
                .answerNo(answerEntity.getAnswerNo())
                .answerContent(answerEntity.getAnswerContent())
                .answerTime(answerEntity.getAnswerTime())
                .answerAccept(answerEntity.getAnswerAccept())
                .answerStatus(answerEntity.getAnswerStatus())
                .member(MemberDto.toDto(answerEntity.getMember()))  
                .inquiry(InquiryDto.toDto(answerEntity.getInquiry()))  
                .build();
    }
}
