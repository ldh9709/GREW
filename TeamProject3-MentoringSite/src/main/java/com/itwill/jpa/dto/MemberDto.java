package com.itwill.jpa.dto;

import java.time.LocalDate;
import java.util.Date;

import com.itwill.jpa.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice.Local;

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
	private String memberRole;
	private String memberPoints;
	private String memberStatus;
	private LocalDate memberJoinDate;
	private String memberReportCount;
	
	
	 /* Entitiy -> DTO*/
	public static MemberDto toDto(Member memberEntity) {
		return MemberDto.builder()
	            .memberNo(memberEntity.getMemberNo())
	            .memberId(memberEntity.getMemberId())
	            .memberPassword(memberEntity.getMemberPassword())
	            .memberEmail(memberEntity.getMemberEmail())
	            .memberName(memberEntity.getMemberName())
	            .memberRole(memberEntity.getMemberRole())
	            .memberPoints(memberEntity.getMemberPoints())
	            .memberStatus(memberEntity.getMemberStatus())
	            .memberJoinDate(memberEntity.getMemberJoinDate())
	            .memberReportCount(memberEntity.getMemberReportCount())
	            .build();
	}
	
	
}
