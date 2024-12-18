package com.itwill.jpa.dto.chatting_review;

import java.time.LocalDateTime;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.member_information.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChatRoomDto {
	private Long chatRoomNo;
	private int chatRoomStatus;
	private LocalDateTime chatRoomStartDate;
	private LocalDateTime chatRoomEndDate;
	
	private Long menteeNo;
	private Long mentorNo;
	private String chatRoomName;
	private int chatRoomLeaveStatus;
	
	public static ChatRoomDto toDto(ChatRoom mentoringRequestEntity) {
		return ChatRoomDto.builder()
				.chatRoomNo(mentoringRequestEntity.getChatRoomNo())
				.chatRoomStatus(mentoringRequestEntity.getChatRoomStatus())
				.chatRoomStartDate(mentoringRequestEntity.getChatRoomEndDate())
				.chatRoomEndDate(mentoringRequestEntity.getChatRoomEndDate())
				.menteeNo(mentoringRequestEntity.getMentee().getMemberNo())
                .mentorNo(mentoringRequestEntity.getMentor().getMemberNo())
				.build();
	}
}	
