package com.itwill.jpa.entity.bullentin_board;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.itwill.jpa.dto.bulletin_board.VoteDto;
import com.itwill.jpa.entity.member_information.Member;

import jakarta.persistence.CascadeType;
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
@Table(name = "vote")  // 테이블 이름을 "vote"로 설정
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_no_SEQ")
    @SequenceGenerator(name = "vote_no_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "vote_no")
    private Long voteNo;  // PK, 시퀀스로 자동 생성

    @Column(name = "vote_type", nullable = false)
    private Integer voteType;  // 투표 타입 (예: up=1, down=2)
    @CreationTimestamp
    @Column(name = "vote_date", nullable = false)
    private LocalDate voteDate;  // 투표 일자 (LocalDate)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no", nullable = false)  // "user_no"는 User 엔티티와 관계
    private Member member;  // 사용자 (User 엔티티와 관계)

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_no", nullable = false)  // "answer_no"는 Answer 엔티티와 관계
    private Answer answer;  // 답변 (Answer 엔티티와 관계)

    /*
     * DTO -> Entity 변환 메소드
     */
    public static Vote toEntity(VoteDto voteDto) {
        return Vote.builder()
                .voteNo(voteDto.getVoteNo())
                .voteType(voteDto.getVoteType())
                .voteDate(voteDto.getVoteDate())
                .member(Member.toEntity(voteDto.getMember()))
                .answer(Answer.toEntity(voteDto.getAnswer()))
                .build();
    }
}