package com.itwill.jpa.entity.chatting_review;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.itwill.jpa.dto.chatting_review.ChatRoomDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "ChatRoom")
public class ChatRoom {
	@Id
	@SequenceGenerator(name = "ChatRoom_chat_room_no_SEQ", sequenceName = "ChatRoom_chat_room_no_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ChatRoom_chat_room_no_SEQ")
    @Column(name = "chat_room_no")
    private String chatRoomNo;

    @Column(name = "chat_room_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime chatRoomDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_no", nullable = false)
    private MentoringRequest mentoringRequest;
    
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ChatMessage> chatMessages;
    
    public static ChatRoom toEntity(ChatRoomDto chatRoomDto) {
        return ChatRoom.builder()
                .chatRoomNo(chatRoomDto.getChatRoomNo())
                .chatRoomDate(chatRoomDto.getChatRoomDate())
                .mentoringRequest(MentoringRequest.toEntity(chatRoomDto.getMentoringRequest()))
                .build();
    }
}
