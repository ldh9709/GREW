package com.itwill.jpa.entity.bullentin_board;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.entity.member_information.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_no_SEQ")
    @SequenceGenerator(name = "answer_no_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "answer_no")
    private Long answerNo;  // PK, 시퀀스로 자동 생성

    @Column(name = "answer_content", nullable = false, length = 500)
    private String answerContent;  // 답변 내용
    
    @CreationTimestamp
    @Column(name = "answer_date", nullable = false)
    private LocalDateTime answerDate;  // 답변 작성 시간 (LocalDate)

    @Column(name = "answer_accept", nullable = false)
    private Integer answerAccept;  // 채택 시 2 기본 1

    @Column(name = "answer_status", nullable = false)
    private Integer answerStatus;  // 답글 삭제 여부 (1 또는2)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;  // 사용자 (User 엔티티와 관계)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_no")
    private Inquiry inquiry;  // 문의 (Inquiry 엔티티와 관계)

    
    /* 초기값 설정 */
    @PrePersist
    public void setDefaultValues() {
    	if(this.answerContent==null) this.answerContent = "";
    	if(this.answerDate==null) this.answerDate = LocalDateTime.now();
    	if(this.answerAccept==null||this.answerAccept==0) this.answerAccept = 1;
    	if(this.answerStatus==null||this.answerStatus==0) this.answerStatus = 1;
    }
    
    /*
     * DTO -> Entity 변환 메소드
     */
    public static Answer toEntity(AnswerDto answerDto) {
        return Answer.builder()
                .answerNo(answerDto.getAnswerNo())
                .answerContent(answerDto.getAnswerContent())
                .answerDate(answerDto.getAnswerDate())
                .answerAccept(answerDto.getAnswerAccept())
                .answerStatus(answerDto.getAnswerStatus())
                .member(Member.builder().memberNo(answerDto.getMemberNo()).build())
                .inquiry(Inquiry.builder().inquiryNo(answerDto.getInquiryNo()).build())
                .build();
    }
    
    /* 한 개의 답변당 여러개의 좋아요/싫어요 보유가능 */
    @OneToMany(mappedBy = "answer", fetch = FetchType.LAZY)
    private List<Vote> votes; 
    
}
