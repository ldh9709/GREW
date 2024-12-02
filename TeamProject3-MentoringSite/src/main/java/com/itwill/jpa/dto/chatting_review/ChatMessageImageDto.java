package com.itwill.jpa.dto.chatting_review;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageImageDto {

    private Long imageNo;
    private String imageName;
    private Long chatMessageNo; // chatMessage의 ID만 필요한 경우

    // 엔티티 -> DTO 변환은 엔티티에서 처리
    // DTO -> 엔티티 변환은 엔티티에서 처리
}
