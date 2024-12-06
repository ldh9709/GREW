package com.itwill.jpa.dto.chatting_review;

import java.time.LocalDateTime;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.chatting_review.ChatMessage;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.member_information.Member;

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

    private long memberNo;
    
    private long chatRoomNo;
    
 // DTO -> 엔티티 변환
    public static ChatMessageDto toDto(ChatMessage chatMessageEntity) {
        return ChatMessageDto.builder()
            .chatMessageNo(chatMessageEntity.getChatMessageNo())
            .chatContent(chatMessageEntity.getChatContent())
            .chatMessageDate(chatMessageEntity.getChatMessageDate())
            .chatMessageCheck(chatMessageEntity.getChatMessageCheck())
            .memberNo(chatMessageEntity.getMember().getMemberNo())  // 멘토 번호
            .chatRoomNo(chatMessageEntity.getChatRoom().getChatRoomNo())
            .build();
       
    }
    
}
