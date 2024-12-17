package com.itwill.jpa.dto.bulletin_board;

import java.time.LocalDateTime;

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
    private LocalDateTime answerDate;  // answer_time
    private Integer answerAccept;   // answer_accept (채택여부)
    private Integer answerStatus;   // answer_status (답글삭제여부, '1' or '2')
    private Long memberNo;           // user_no (FK)
    private Long inquiryNo;        // inquiry_no (FK)
    private String memberName;		//작성자이름
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
                .inquiryNo(answerEntity.getInquiry().getInquiryNo())
                .memberNo(answerEntity.getMember().getMemberNo())  
                .memberName(answerEntity.getMember().getMemberName())
                .build();
    }
}
