package com.itwill.jpa.entity.chatting_review;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.itwill.jpa.dto.chatting_review.ReviewDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name="review")
public class Review {

    @Id
    @SequenceGenerator(name = "review_no_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_no_SEQ")
    @Column(name="review_no")
    private Long reviewNo;

    @Column(name="review_title", nullable=false)
    private String reviewTitle;
    
    @Column(name="review_content", nullable=false)
    private String reviewContent;

    @Min(1)
    @Max(5)
    @Column(name="review_score", nullable=false)
    private Integer reviewScore;

    @Column(name="review_date", updatable = false)
    private LocalDateTime reviewDate = LocalDateTime.now();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="chat_room_no")
    @JsonBackReference
    private ChatRoom chatRoom;

    @PrePersist
    public void setDefaultValues() {
        if (this.reviewDate == null) this.reviewDate = LocalDateTime.now();
        
        
        
    }

    public static Review toEntity(ReviewDto reviewDto) {
        return Review.builder()
                .reviewNo(reviewDto.getReviewNo())
                .reviewTitle(reviewDto.getReviewTitle())
                .reviewContent(reviewDto.getReviewContent())
                .reviewScore(reviewDto.getReviewScore())
                .reviewDate(reviewDto.getReviewDate())
                .chatRoom(ChatRoom.builder().chatRoomNo(reviewDto.getChatRoomNo()).build()) //  엔티티 포함
                .build();
    }

    
}
