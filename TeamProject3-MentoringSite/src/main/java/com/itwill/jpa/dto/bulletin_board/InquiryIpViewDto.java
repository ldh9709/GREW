package com.itwill.jpa.dto.bulletin_board;

import java.time.LocalDateTime;

import com.itwill.jpa.entity.bullentin_board.InquiryIpView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InquiryIpViewDto {
	private Long inquiryIpViewNo;
	private String ipAddress;
	private Long inquiryNo;
	private LocalDateTime lastViewd;
	
	
	//Dto변환
	public static InquiryIpViewDto toDto(InquiryIpView inquiryIpViewEntity) {
		return InquiryIpViewDto.builder()
				.inquiryIpViewNo(inquiryIpViewEntity.getInquiryIpViewNo())
				.ipAddress(inquiryIpViewEntity.getIpAddress())
				.inquiryNo(inquiryIpViewEntity.getInquiry().getInquiryNo())
				.lastViewd(inquiryIpViewEntity.getLastViewed())
				.build();
	}
}
