package com.itwill.jpa.dto.chatting_review;

import java.time.LocalDateTime;

import com.itwill.jpa.entity.chatting_review.ChatRoom;

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

    private String memberId; // memberId만 필요한 경우 사용할 수 있음
}
