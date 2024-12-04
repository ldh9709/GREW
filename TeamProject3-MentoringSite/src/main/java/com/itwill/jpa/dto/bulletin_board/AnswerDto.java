package com.itwill.jpa.dto.bulletin_board;

import java.time.LocalDateTime;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.bullentin_board.Answer;
import com.itwill.jpa.entity.bullentin_board.Inquiry;
import com.itwill.jpa.entity.member_information.Member;

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
    private LocalDateTime answerDate;  // answer_time
    private Integer answerAccept;   // answer_accept (채택여부)
    private Integer answerStatus;   // answer_status (답글삭제여부, '1' or '2')
    private Long memberNo;           // user_no (FK)
    private Long inquiryNo;        // inquiry_no (FK)

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
                .memberNo(answerEntity.getMember().getMemberNo())  
                .inquiryNo(answerEntity.getInquiry().getInquiryNo())
                .build();
    }
}
