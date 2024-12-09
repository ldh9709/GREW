package com.itwill.jpa.entity.bullentin_board;

import java.time.LocalDateTime;

import com.itwill.jpa.dto.bulletin_board.InquiryIpViewDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="inquiryIpView")
	public class InquiryIpView {
	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "inquiryIpView_no_SEQ")
	    @SequenceGenerator(name="inquiryIpView_no_SEQ",allocationSize = 1,initialValue = 1)
	    private Long InquiryIpViewNo;

	    private String ipAddress;   // IP 주소

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "inquiry_no")
	    private Inquiry inquiry;    // 조회된 게시글 (외래 키)

	    private LocalDateTime lastViewed;  // 마지막 조회 시간
	    
	    /*초기값*/
	    @PrePersist
	    public void setDefaultValues() {
	    	if(this.lastViewed==null) this.lastViewed=LocalDateTime.now();
	    }
	    //entity변환
	    public static InquiryIpView toEntity(InquiryIpViewDto inquiryIpViewDto) {
	    	return InquiryIpView.builder()
	    			.InquiryIpViewNo(inquiryIpViewDto.getInquiryIpViewNo())
	    			.ipAddress(inquiryIpViewDto.getIpAddress())
	    			.inquiry(Inquiry.builder().inquiryNo(inquiryIpViewDto.getInquiryNo()).build())
	    			.lastViewed(inquiryIpViewDto.getLastViewd())
	    			.build();
	    }
	}

