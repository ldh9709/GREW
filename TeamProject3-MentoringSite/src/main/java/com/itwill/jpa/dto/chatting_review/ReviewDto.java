package com.itwill.jpa.dto.chatting_review;

import java.time.LocalDateTime;

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
    private Integer reviewScore;
    private LocalDateTime reviewDate; // 엔티티에서 자동 생성된 reviewDate

    //private String memberId; // memberId만 필요한 경우 사용할 수 있음
}
