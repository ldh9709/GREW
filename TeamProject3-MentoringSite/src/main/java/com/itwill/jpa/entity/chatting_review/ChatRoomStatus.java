package com.itwill.jpa.entity.chatting_review;

import com.itwill.jpa.dto.chatting_review.ChatRoomStatusDto;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.role.Role;

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
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "chat_room_status")
public class ChatRoomStatus {
	@Id
	@SequenceGenerator(name = "chat_room_status_no_SEQ", sequenceName = "chat_room_status_no_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_room_status_no_SEQ")
    @Column(name = "chat_room_status_no")
    private Long chatRoomStatusNo;

    @Column(name = "chat_room_name")
    private String chatRoomName;

    @Column(name = "chat_room_status")
    private Integer chatRoomStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_no")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;
    
    public static ChatRoomStatus toEntity(ChatRoomStatusDto chatRoomStatusDto) {
        return ChatRoomStatus.builder()
                .chatRoomStatusNo(chatRoomStatusDto.getChatRoomStatusNo())
                .chatRoomName(chatRoomStatusDto.getChatRoomName())
                .chatRoomStatus(chatRoomStatusDto.getChatRoomStatus())
                .member(Member.builder().memberNo(chatRoomStatusDto.getMemberNo()).build())
                .build();
    }
}
