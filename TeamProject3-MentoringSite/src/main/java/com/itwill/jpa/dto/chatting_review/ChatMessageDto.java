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
    private String chatMessageContent;
    private LocalDateTime chatMessageDate; 
    private Integer chatMessageCheck;

    private long memberNo;
    private String memberName;
    
    private long chatRoomNo;
    
    private String base64Image;
    
 // DTO -> 엔티티 변환
    public static ChatMessageDto toDto(ChatMessage chatMessageEntity) {
        return ChatMessageDto.builder()
            .chatMessageNo(chatMessageEntity.getChatMessageNo())
            .chatMessageContent(chatMessageEntity.getChatMessageContent())
            .chatMessageDate(chatMessageEntity.getChatMessageDate())
            .chatMessageCheck(chatMessageEntity.getChatMessageCheck())
            .memberNo(chatMessageEntity.getMember().getMemberNo())  
            .memberName(chatMessageEntity.getMember().getMemberName())
            .chatRoomNo(chatMessageEntity.getChatRoom().getChatRoomNo())
            .build();
       
    }
    
}
