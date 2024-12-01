package com.itwill.jpa.entity.bullentin_board;

import java.time.LocalDate;

import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.entity.user_information.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_seq")
    @SequenceGenerator(name = "answer_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "answer_no")
    private Long answerNo;  // PK, 시퀀스로 자동 생성

    @Column(name = "answer_content", nullable = false)
    private String answerContent;  // 답변 내용

    @Column(name = "answer_time", nullable = false)
    private LocalDate answerTime;  // 답변 작성 시간 (LocalDate)

    @Column(name = "answer_accept", nullable = false)
    private String answerAccept;  // 채택 여부 (예: "Y", "N")

    @Column(name = "answer_status", nullable = false)
    private String answerStatus;  // 답글 삭제 여부 (active 또는 inactive)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;  // 사용자 (User 엔티티와 관계)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_no")
    private Inquiry inquiry;  // 문의 (Inquiry 엔티티와 관계)

    /*
     * DTO -> Entity 변환 메소드
     */
    public static Answer toEntity(AnswerDto answerDto) {
        return Answer.builder()
                .answerNo(answerDto.getAnswerNo())
                .answerContent(answerDto.getAnswerContent())
                .answerTime(answerDto.getAnswerTime())
                .answerAccept(answerDto.getAnswerAccept())
                .answerStatus(answerDto.getAnswerStatus())
                .member(Member.toEntity(answerDto.getMember()))
                .inquiry(Inquiry.toEntity(answerDto.getInquiry()))
                .build();
    }
}
