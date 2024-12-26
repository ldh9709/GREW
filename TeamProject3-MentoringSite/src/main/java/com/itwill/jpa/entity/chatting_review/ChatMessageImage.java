package com.itwill.jpa.entity.chatting_review;

import com.itwill.jpa.dto.chatting_review.ChatMessageImageDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="chat_message_image")
public class ChatMessageImage {

    @Id
    @SequenceGenerator(name = "image_no_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_no_SEQ")
    @Column(name="image_no")
    private Long imageNo;
    
    @Column(name="image_name", nullable=false)
    private String imageName;
    
    @Column(name = "BASE64IMAGE", columnDefinition = "CLOB")
    private String base64Image;  // Base64로 인코딩된 이미지 데이터

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="chat_message_no", nullable=false)
    private ChatMessage chatMessage; 
    
    

    // 엔티티 -> DTO 변환
    public static ChatMessageImage toEntity(ChatMessageImageDto chatMessageImageDto) {
        return ChatMessageImage.builder()
                .imageNo(chatMessageImageDto.getImageNo())
                .imageName(chatMessageImageDto.getImageName())
                .chatMessage(ChatMessage.builder().chatMessageNo(chatMessageImageDto.getChatMessageNo()).build()) // chatMessageNo만 담기
                .build();
    }

    
}
