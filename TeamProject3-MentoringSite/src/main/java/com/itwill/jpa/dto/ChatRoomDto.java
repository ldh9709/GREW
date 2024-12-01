package com.itwill.jpa.dto;

import java.time.LocalDateTime;

import com.itwill.jpa.entity.ChatRoom;

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
	
	public static ChatRoomDto toDto(ChatRoom chatRoomEntity) {
        return ChatRoomDto.builder()
                .chatRoomNo(chatRoomEntity.getChatRoomNo())
                .chatRoomDate(chatRoomEntity.getChatRoomDate())
                .build();
    }
}
