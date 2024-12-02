package com.itwill.jpa.entity.chatting_review;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.entity.user_information.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

    @Id
    @SequenceGenerator(name = "review_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    private Long reviewNo;

    private String reviewTitle;
    private String reviewContent;

    @Min(1)
    @Max(5)
    private Integer reviewScore;

    @CreationTimestamp
    private LocalDate reviewDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private MentoringRequest mentoringRequest;
    //@ManyToOne(fetch = FetchType.LAZY)
    //private Member member;

    // 엔티티 -> DTO 변환
    public ReviewDto toDto() {
        return ReviewDto.builder()
                .reviewNo(this.reviewNo)
                .reviewTitle(this.reviewTitle)
                .reviewContent(this.reviewContent)
                .reviewScore(this.reviewScore)
                .reviewDate(this.reviewDate != null ? this.reviewDate.atStartOfDay() : null) // LocalDateTime으로 변환
                //.memberId(this.member != null ? this.member.getMemberId() : null) // memberId만 담기
                .build();
    }

    // DTO -> 엔티티 변환
    public static Review fromDto(ReviewDto dto, /*Member member*/ MentoringRequest mentoringRequest) {
        return new Review(
            dto.getReviewNo(),
            dto.getReviewTitle(),
            dto.getReviewContent(),
            dto.getReviewScore(),
            dto.getReviewDate() != null ? dto.getReviewDate().toLocalDate() : null, // LocalDate로 변환
            //member
            mentoringRequest
        );
    }
}
