package com.itwill.jpa.dto.chatting_review;

import com.itwill.jpa.entity.chatting_review.ChatMessage;
import com.itwill.jpa.entity.chatting_review.ChatMessageImage;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageImageDto {

    private Long imageNo;
    private String imageName;
    private Long chatMessageNo; // chatMessage의 ID만 필요한 경우
    private String chatMessageContent;
    private Long chatRoomNo;
    private Long memberNo;
    
    private String base64Image;  // Base64 이미지 문자열 필드


    // DTO -> 엔티티 변환
    public static ChatMessageImageDto toDto(ChatMessageImage chatMessageImagedto) {
        return ChatMessageImageDto.builder()
                .imageNo(chatMessageImagedto.getImageNo())
                .imageName(chatMessageImagedto.getImageName())
                .chatMessageNo(chatMessageImagedto.getChatMessage().getChatMessageNo()) // ChatMessage 객체를 직접 전달
                .chatMessageContent(chatMessageImagedto.getChatMessage().getChatMessageContent())
                .chatRoomNo(chatMessageImagedto.getChatMessage().getChatRoom().getChatRoomNo())
                .memberNo(chatMessageImagedto.getChatMessage().getChatRoom().getMentee().getMemberNo())
                .build();
    }
}
