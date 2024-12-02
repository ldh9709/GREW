package com.itwill.jpa.dto.chatting_review;

import java.time.LocalDateTime;

import com.itwill.jpa.dto.user_information.MemberDto;
import com.itwill.jpa.entity.chatting_review.ChatMessage;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.user_information.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {

    private Long chatMessageNo;
    private String chatContent;
    private LocalDateTime chatMessageDate; 
    private Integer chatMessageCheck;

    private MemberDto member; // memberId만 필요한 경우 사용할 수 있음
    private ChatRoomDto chatRoom;
    
 // DTO -> 엔티티 변환
    public static ChatMessageDto toDto(ChatMessage chatMessageEntity) {
        return ChatMessageDto.builder()
            .chatMessageNo(chatMessageEntity.getChatMessageNo())
            .chatContent(chatMessageEntity.getChatContent())
            .chatMessageDate(chatMessageEntity.getChatMessageDate())
            .chatMessageCheck(chatMessageEntity.getChatMessageCheck())
            .member(MemberDto.toDto(chatMessageEntity.getMember()))
            .chatRoom(ChatRoomDto.toDto(chatMessageEntity.getChatRoom()))
            .build();
       
    }
    
}
