package com.itwill.jpa.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.itwill.jpa.dto.MentoringRequestDto;

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
@Table(name = "MentoringRequest")
public class MentoringRequest {
	@Id
	@SequenceGenerator(name = "MentoringRequest_request_no_SEQ", sequenceName = "MentoringRequest_request_no_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MentoringRequest_request_no_SEQ")
	@Column(name = "request_no")
	private Long requestNo;
	
	@Column(name = "request_status")
	private String requestStatus;
	
	@Column(name = "request_date", updatable = false)
	private LocalDateTime requestDate = LocalDateTime.now();
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_no", nullable = false)
    private Member menteeNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_no", nullable = false)
    private Member mentorNo;
	
    @OneToMany(mappedBy = "mentoringRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Review> reviews;
	
    public static MentoringRequest toEntity(MentoringRequestDto dto) {
        return MentoringRequest.builder()
                .requestNo(dto.getRequestNo())
                .requestStatus(dto.getRequestStatus())
                .requestDate(dto.getRequestDate())
                .build();
    }
}
