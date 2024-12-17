package com.itwill.jpa.dto.member_information;

import java.time.LocalDateTime;
import java.util.List;

import com.itwill.jpa.entity.member_information.Interest;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.role.Role;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MemberDto {
	private Long memberNo;
	private String memberId;
	private String memberPassword;
	private String memberEmail;
	private String memberName;
	private Integer memberStatus;
	private Integer memberPoint;
	private LocalDateTime memberJoinDate;
	private Integer memberReportCount;
	private Role memberRole;
	private String memberProvider;
	
	private List<InterestDto> interests;
	
	 /* Entitiy -> DTO*/
	public static MemberDto toDto(Member memberEntity) {
		return MemberDto.builder()
				.memberNo(memberEntity.getMemberNo())
	            .memberId(memberEntity.getMemberId())
	            .memberPassword(memberEntity.getMemberPassword())
	            .memberEmail(memberEntity.getMemberEmail())
	            .memberName(memberEntity.getMemberName())
	            .memberStatus(memberEntity.getMemberStatus())
	            .memberPoint(memberEntity.getMemberPoints())
	            .memberJoinDate(memberEntity.getMemberJoinDate())
	            .memberReportCount(memberEntity.getMemberReportCount())
	            .memberRole(memberEntity.getMemberRole())
	            .memberProvider(memberEntity.getMemberProvider())
	            .interests(memberEntity.getInterests().stream()
	            		.map(InterestDto::toDto)
	            		.toList())
	            .build();
	}
	
	public static MemberDto toBasicDto(Member memberEntity) {
		return MemberDto.builder()
				.memberNo(memberEntity.getMemberNo())
	            .memberId(memberEntity.getMemberId())
	            .memberPassword(memberEntity.getMemberPassword())
	            .memberEmail(memberEntity.getMemberEmail())
	            .memberName(memberEntity.getMemberName())
	            .memberStatus(memberEntity.getMemberStatus())
	            .memberPoint(memberEntity.getMemberPoints())
	            .memberJoinDate(memberEntity.getMemberJoinDate())
	            .memberReportCount(memberEntity.getMemberReportCount())
	            .memberRole(memberEntity.getMemberRole())
	            .memberProvider(memberEntity.getMemberProvider())
	            .build();
	}
	
	
}
