package com.itwill.jpa.dto.chatting_review;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.chatting_review.ChatRoomStatus;
import com.itwill.jpa.entity.member_information.Member;

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
	
	private MemberDto member;
	
	public static ChatRoomStatusDto toDto(ChatRoomStatus chatRoomStatusEntity) {
        return ChatRoomStatusDto.builder()
                .chatRoomStatusNo(chatRoomStatusEntity.getChatRoomStatusNo())
                .chatRoomName(chatRoomStatusEntity.getChatRoomName())
                .chatRoomStatus(chatRoomStatusEntity.getChatRoomStatus())
                .member(MemberDto.toDto(chatRoomStatusEntity.getMember()))
                .build();
    }
}
