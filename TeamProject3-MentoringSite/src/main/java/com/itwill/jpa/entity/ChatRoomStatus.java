package com.itwill.jpa.entity;

import com.itwill.jpa.dto.ChatRoomStatusDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "ChatRoomStatus")
public class ChatRoomStatus {
	@Id
	@SequenceGenerator(name = "ChatRoomStatus_chat_room_status_no_SEQ", sequenceName = "ChatRoomStatus_chat_room_status_no_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ChatRoomStatus_chat_room_status_no_SEQ")
    @Column(name = "chat_room_status_no")
    private Long chatRoomStatusNo;

    @Column(name = "chat_room_name")
    private String chatRoomName;

    @Column(name = "chat_room_status")
    private Integer chatRoomStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_no", nullable = false)
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;
    
    public static ChatRoomStatus toEntity(ChatRoomStatusDto dto) {
        return ChatRoomStatus.builder()
                .chatRoomStatusNo(dto.getChatRoomStatusNo())
                .chatRoomName(dto.getChatRoomName())
                .chatRoomStatus(dto.getChatRoomStatus())
                .build();
    }
}
