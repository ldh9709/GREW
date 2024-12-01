package com.itwill.jpa.dto;

import java.time.LocalDateTime;

import com.itwill.jpa.entity.MentoringRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MentoringRequestDto {
	private Long requestNo;
	private String requestStatus;
	private LocalDateTime requestDate;
	
	public static MentoringRequestDto toDto(MentoringRequest mentoringRequestEntity) {
		return MentoringRequestDto.builder()
				.requestNo(mentoringRequestEntity.getRequestNo())
				.requestStatus(mentoringRequestEntity.getRequestStatus())
				.requestDate(mentoringRequestEntity.getRequestDate())
				.build();
	}
}	
