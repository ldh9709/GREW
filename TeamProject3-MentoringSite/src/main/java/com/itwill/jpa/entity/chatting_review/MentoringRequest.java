package com.itwill.jpa.entity.chatting_review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.itwill.jpa.dto.chatting_review.MentoringRequestDto;
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

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "mentoring_request")
public class MentoringRequest {
	@Id
	@SequenceGenerator(name = "request_no_SEQ", sequenceName = "request_no_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_no_SEQ")
	@Column(name = "request_no", nullable = false)
	private Long requestNo;
	
	@Column(name = "request_status", nullable = false)
	private Integer requestStatus;
	
	@Column(name = "request_start_date", updatable = false)
	private LocalDateTime requestStartDate;
	
	@Column(name = "request_end_date", updatable = false)
	private LocalDateTime requestEndDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_no", nullable = false)
    private Member mentee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_no", nullable = false)
    private Member mentor;
	
    @OneToMany(mappedBy = "mentoringRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<Review>();
    
    @OneToMany(mappedBy = "mentoringRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ChatRoom> chatRooms = new ArrayList<ChatRoom>();
	
    /* 초기값 설정 */
    @PrePersist
    public void setDefaultValues() {
    	if (this.requestStatus == 0 || this.requestStatus == null) this.requestStatus = 7000;
    	if (this.requestStartDate == null) this.requestStartDate = LocalDateTime.now();
    	if (this.requestEndDate == null) this.requestEndDate = null;
    }
    
    public static MentoringRequest toEntity(MentoringRequestDto mentoringRequestDto) {
        return MentoringRequest.builder()
                .requestNo(mentoringRequestDto.getRequestNo())
                .requestStatus(mentoringRequestDto.getRequestStatus())
                .requestStartDate(mentoringRequestDto.getRequestStratDate())
                .requestEndDate(mentoringRequestDto.getRequestEndDate())
                .mentee(Member.builder().memberNo(mentoringRequestDto.getMenteeNo()).build())
                .mentor(Member.builder().memberNo(mentoringRequestDto.getMentorNo()).build())
                .build();
    }
}
