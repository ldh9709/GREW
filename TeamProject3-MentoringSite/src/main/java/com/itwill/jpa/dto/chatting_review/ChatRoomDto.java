package com.itwill.jpa.dto.chatting_review;

import java.time.LocalDateTime;

import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.chatting_review.MentoringRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChatRoomDto {
	private String chatRoomNo;
	private LocalDateTime chatRoomDate;
	
	private MentoringRequestDto mentoringRequest;
	
	public static ChatRoomDto toDto(ChatRoom chatRoomEntity) {
        return ChatRoomDto.builder()
                .chatRoomNo(chatRoomEntity.getChatRoomNo())
                .chatRoomDate(chatRoomEntity.getChatRoomDate())
                .mentoringRequest(MentoringRequestDto.toDto(chatRoomEntity.getMentoringRequest()))
                .build();
    }
}
