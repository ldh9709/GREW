package com.itwill.jpa.dto.chatting_review;

import java.time.LocalDateTime;

import com.itwill.jpa.dto.user_information.MemberDto;
import com.itwill.jpa.entity.chatting_review.MentoringRequest;
import com.itwill.jpa.entity.user_information.Member;

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
	
	private MemberDto mentee;
	private MemberDto mentor;
	
	public static MentoringRequestDto toDto(MentoringRequest mentoringRequestEntity) {
		return MentoringRequestDto.builder()
				.requestNo(mentoringRequestEntity.getRequestNo())
				.requestStatus(mentoringRequestEntity.getRequestStatus())
				.requestDate(mentoringRequestEntity.getRequestDate())
				.mentee(MemberDto.toDto(mentoringRequestEntity.getMentee()))
                .mentor(MemberDto.toDto(mentoringRequestEntity.getMentor()))
				.build();
	}
}	
