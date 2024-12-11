package com.itwill.jpa.entity.chatting_review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.itwill.jpa.dto.chatting_review.ChatRoomDto;
import com.itwill.jpa.entity.member_information.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "chat_room")
public class ChatRoom {
	@Id
	@SequenceGenerator(name = "chat_room_no_SEQ", sequenceName = "chat_room_no_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_room_no_SEQ")
	@Column(name = "chat_room_no", nullable = false)
	private Long chatRoomNo;
	
	@Column(name = "chat_room_status", nullable = false)
	private Integer chatRoomStatus;
	
	@Column(name = "chat_room_start_date", updatable = false)
	private LocalDateTime chatRoomStartDate;
	
	@Column(name = "chat_room_end_date")
	private LocalDateTime chatRoomEndDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_no", nullable = false)
    private Member mentee;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_no", nullable = false)
    private Member mentor;
	
	
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ChatRoomStatus> chatRoomStatusList = new ArrayList<ChatRoomStatus>();
    
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<Review>();
    
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ChatMessage> chatMessages= new ArrayList<ChatMessage>();
	
    /* 초기값 설정 */
    @PrePersist
    public void setDefaultValues() {
    	if (this.chatRoomStatus == 0 || this.chatRoomStatus == null) this.chatRoomStatus = 7000;
    	if (this.chatRoomStartDate == null) this.chatRoomStartDate = LocalDateTime.now();
    }
    
    public static ChatRoom toEntity(ChatRoomDto mentoringRequestDto) {
        return ChatRoom.builder()
                .chatRoomNo(mentoringRequestDto.getChatRoomNo())
                .chatRoomStatus(mentoringRequestDto.getChatRoomStatus())
                .chatRoomStartDate(mentoringRequestDto.getChatRoomStartDate())
                .chatRoomEndDate(mentoringRequestDto.getChatRoomEndDate())
                .mentee(Member.builder().memberNo(mentoringRequestDto.getMenteeNo()).build())
                .mentor(Member.builder().memberNo(mentoringRequestDto.getMentorNo()).build())
                .build();
    }
}
