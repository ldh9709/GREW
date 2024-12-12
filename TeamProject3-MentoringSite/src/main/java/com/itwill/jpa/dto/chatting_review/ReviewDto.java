package com.itwill.jpa.dto.chatting_review;

import java.time.LocalDateTime;

import com.itwill.jpa.entity.chatting_review.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long reviewNo;
    private String reviewTitle;
    private String reviewContent;
    private Integer reviewStatus;//기본값 1 삭제시 2
    private Integer reviewScore;
    private LocalDateTime reviewDate; // 엔티티에서 자동 생성된 reviewDate
    private Long chatRoomNo;
    private String menteeName;
    private Long memberNo;
    private Long mentorMemberNo;
    
    public ReviewDto(Long memberNo, Double reviewScore) {
        this.memberNo = memberNo;
        this.reviewScore = (int) Math.round(reviewScore); // 🔥 Double -> Integer 변환
    }
    public ReviewDto(Long memberNo, Integer reviewScore) {
        this.memberNo = memberNo;
        this.reviewScore = reviewScore;
    }

    
    public static ReviewDto toDto(Review reviewEntity) {
        return ReviewDto.builder()
                .reviewNo(reviewEntity.getReviewNo())
                .reviewTitle(reviewEntity.getReviewTitle())
                .reviewContent(reviewEntity.getReviewContent())
                .reviewStatus(reviewEntity.getReviewStatus())
                .reviewScore(reviewEntity.getReviewScore())
                .reviewDate(reviewEntity.getReviewDate())
                .chatRoomNo(reviewEntity.getChatRoom().getChatRoomNo())
                .menteeName(reviewEntity.getChatRoom().getMentee().getMemberName())
                .memberNo(reviewEntity.getChatRoom().getMentee().getMemberNo())
                .mentorMemberNo(reviewEntity.getChatRoom().getMentor().getMemberNo())
                .build();
    }
}