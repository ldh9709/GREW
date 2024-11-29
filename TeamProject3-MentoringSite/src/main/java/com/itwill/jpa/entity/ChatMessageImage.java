package com.itwill.jpa.entity;

import com.itwill.jpa.dto.ChatMessageImageDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatMessageImage {

    @Id
    @SequenceGenerator(name = "chat_message_image_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_message_image_seq")
    private Long imageNo;

    private String imageName;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChatMessage chatMessage; // 하나의 chatMessage에 여러 개의 ChatMessageImage가 속함

    // 엔티티 -> DTO 변환
    public ChatMessageImageDto toDto() {
        return ChatMessageImageDto.builder()
                .imageNo(this.imageNo)
                .imageName(this.imageName)
                .chatMessageNo(this.chatMessage != null ? this.chatMessage.getChatMessageNo() : null) // chatMessageNo만 담기
                .build();
    }

    // DTO -> 엔티티 변환
    public static ChatMessageImage fromDto(ChatMessageImageDto dto, ChatMessage chatMessage) {
        return ChatMessageImage.builder()
                .imageNo(dto.getImageNo())
                .imageName(dto.getImageName())
                .chatMessage(chatMessage) // ChatMessage 객체를 직접 전달
                .build();
    }
}
