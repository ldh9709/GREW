package com.itwill.jpa.dto.bulletin_board;

import java.time.LocalDate;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.bullentin_board.Answer;

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
    private LocalDate answerDate;  // answer_time
    private Integer answerAccept;   // answer_accept (채택여부)
    private Integer answerStatus;   // answer_status (답글삭제여부, '1' or '2')
    private MemberDto member;           // user_no (FK)
    private InquiryDto inquiry;        // inquiry_no (FK)

    /*
     * Entity -> DTO 변환 메소드
     */
    public static AnswerDto toDto(Answer answerEntity) {

        return AnswerDto.builder()
                .answerNo(answerEntity.getAnswerNo())
                .answerContent(answerEntity.getAnswerContent())
                .answerDate(answerEntity.getAnswerDate())
                .answerAccept(answerEntity.getAnswerAccept())
                .answerStatus(answerEntity.getAnswerStatus())
                .member(MemberDto.toDto(answerEntity.getMember()))  
                .inquiry(InquiryDto.toDto(answerEntity.getInquiry()))  
                .build();
    }
}
