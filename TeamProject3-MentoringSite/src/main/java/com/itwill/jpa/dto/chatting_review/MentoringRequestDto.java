package com.itwill.jpa.dto.chatting_review;

import java.time.LocalDateTime;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.chatting_review.MentoringRequest;
import com.itwill.jpa.entity.member_information.Member;

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
	private int requestStatus;
	private LocalDateTime requestStratDate;
	private LocalDateTime requestEndDate;
	
	private Long menteeNo;
	private Long mentorNo;
	
	public static MentoringRequestDto toDto(MentoringRequest mentoringRequestEntity) {
		return MentoringRequestDto.builder()
				.requestNo(mentoringRequestEntity.getRequestNo())
				.requestStatus(mentoringRequestEntity.getRequestStatus())
				.requestStratDate(mentoringRequestEntity.getRequestStartDate())
				.requestEndDate(mentoringRequestEntity.getRequestEndDate())
				.menteeNo(mentoringRequestEntity.getMentee().getMemberNo())
                .mentorNo(mentoringRequestEntity.getMentor().getMemberNo())
				.build();
	}
}	
