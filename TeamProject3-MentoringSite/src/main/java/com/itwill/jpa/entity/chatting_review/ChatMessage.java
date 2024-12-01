package com.itwill.jpa.entity.chatting_review;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.itwill.jpa.dto.chatting_review.ChatMessageDto;
import com.itwill.jpa.entity.user_information.Member;

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
public class ChatMessage {

    @Id
    @SequenceGenerator(name = "chat_message_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_message_seq")
    private Long chatMessageNo;

    private String chatContent;

    @CreationTimestamp
    private LocalDateTime chatMessageDate;

    private Integer chatMessageCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    // 엔티티 -> DTO 변환
    public ChatMessageDto toDto() {
        return ChatMessageDto.builder()
                             .chatMessageNo(this.chatMessageNo)
                             .chatContent(this.chatContent)
                             .chatMessageDate(this.chatMessageDate)
                             .chatMessageCheck(this.chatMessageCheck)
                             .memberId(this.member != null ? this.member.getMemberId() : null) // memberId만 담기
                             .build();
    }

    // DTO -> 엔티티 변환
    public static ChatMessage fromDto(ChatMessageDto dto, Member member) {
        return new ChatMessage(
            dto.getChatMessageNo(),
            dto.getChatContent(),
            dto.getChatMessageDate(),
            dto.getChatMessageCheck(),
            member
        );
    }
}
