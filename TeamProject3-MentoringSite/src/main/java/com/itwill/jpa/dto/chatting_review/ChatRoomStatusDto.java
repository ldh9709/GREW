package com.itwill.jpa.dto.chatting_review;

import com.itwill.jpa.entity.chatting_review.ChatRoomStatus;

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
	
	private Long memberNo;
	
	public static ChatRoomStatusDto toDto(ChatRoomStatus chatRoomStatusEntity) {
        return ChatRoomStatusDto.builder()
                .chatRoomStatusNo(chatRoomStatusEntity.getChatRoomStatusNo())
                .chatRoomName(chatRoomStatusEntity.getChatRoomName())
                .chatRoomStatus(chatRoomStatusEntity.getChatRoomStatus())
                .memberNo(chatRoomStatusEntity.getMember().getMemberNo())
                .build();
    }
}
