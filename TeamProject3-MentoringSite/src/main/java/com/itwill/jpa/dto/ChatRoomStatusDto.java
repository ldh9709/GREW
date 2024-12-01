package com.itwill.jpa.dto;

import com.itwill.jpa.entity.ChatRoomStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChatRoomStatusDto {
	private Long chatRoomStatusNo;
	private String chatRoomName;
	private Integer chatRoomStatus;
	
	public static ChatRoomStatusDto toEntity(ChatRoomStatus chatRoomStatusEntity) {
        return ChatRoomStatusDto.builder()
                .chatRoomStatusNo(chatRoomStatusEntity.getChatRoomStatusNo())
                .chatRoomName(chatRoomStatusEntity.getChatRoomName())
                .chatRoomStatus(chatRoomStatusEntity.getChatRoomStatus())
                .build();
    }
}
