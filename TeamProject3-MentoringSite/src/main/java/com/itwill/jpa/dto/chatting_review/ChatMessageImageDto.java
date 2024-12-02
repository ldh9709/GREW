package com.itwill.jpa.dto.chatting_review;

import com.itwill.jpa.entity.chatting_review.ChatMessage;
import com.itwill.jpa.entity.chatting_review.ChatMessageImage;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageImageDto {

    private Long imageNo;
    private String imageName;
    private ChatMessageDto chatMessage; // chatMessage의 ID만 필요한 경우

    // DTO -> 엔티티 변환
    public static ChatMessageImageDto toDto(ChatMessageImage chatMessageImagedto) {
        return ChatMessageImageDto.builder()
                .imageNo(chatMessageImagedto.getImageNo())
                .imageName(chatMessageImagedto.getImageName())
                .chatMessage(ChatMessageDto.toDto(chatMessageImagedto.getChatMessage())) // ChatMessage 객체를 직접 전달
                .build();
    }
}
