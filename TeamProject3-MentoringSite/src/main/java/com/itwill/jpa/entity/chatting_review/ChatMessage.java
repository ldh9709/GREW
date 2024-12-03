package com.itwill.jpa.entity.chatting_review;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.itwill.jpa.dto.chatting_review.ChatMessageDto;
import com.itwill.jpa.entity.member_information.Member;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="chat_message")
public class ChatMessage {

    @Id
    @SequenceGenerator(name = "chat_message_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_message_SEQ")
    @Column(name="chat_message_no")
    private Long chatMessageNo;

    @Column(name="chat_message_content",nullable = false)
    private String chatContent;

    @Column(name="chat_message_date",nullable = false)
    private LocalDateTime chatMessageDate;

    @Column(name="chat_message_check",nullable = false)
    private Integer chatMessageCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no", nullable=false)
    private Member member;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="chat_room_no",nullable=false)
    private ChatRoom chatRoom;
    
    @PrePersist
    public void setDefaultValues() {
    	if (this.chatMessageCheck == null) this.chatMessageCheck = 1;
        if (this.chatMessageDate == null) this.chatMessageDate = LocalDateTime.now();
    }

    // 엔티티 -> DTO 변환
    public static ChatMessage toEntity(ChatMessageDto chatMessageDto) {
        return ChatMessage.builder()
                             .chatMessageNo(chatMessageDto.getChatMessageNo())
                             .chatContent(chatMessageDto.getChatContent())
                             .chatMessageDate(chatMessageDto.getChatMessageDate())
                             .chatMessageCheck(chatMessageDto.getChatMessageCheck())
                             .member(Member.toEntity(chatMessageDto.getMember()))
                             .chatRoom(ChatRoom.toEntity(chatMessageDto.getChatRoom()))
                             .build();
    }

    
}
