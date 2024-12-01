package com.itwill.jpa.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.itwill.jpa.dto.ChatRoomDto;

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
    private LocalDateTime chatRoomDate = LocalDateTime.now();

    @Column(name = "chat_room_name")
    private String chatRoomName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_no", nullable = false)
    private MentoringRequest mentoringRequest;
    
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ChatMessage> chatMessages;
    
    public static ChatRoom toEntity(ChatRoomDto dto) {
        return ChatRoom.builder()
                .chatRoomNo(dto.getChatRoomNo())
                .chatRoomName(dto.getChatRoomName())
                .chatRoomDate(dto.getChatRoomDate())
                .build();
    }
}
