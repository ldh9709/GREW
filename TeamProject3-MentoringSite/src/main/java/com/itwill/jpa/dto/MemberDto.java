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
	
	private String memberId;
	private String memberPassword;
	private String memberEmail;
	private String memberName;
	
	/*
	 * Entitiy -> DTO
	 */
	public static MemberDto toDto(Member userEntity) {
		return MemberDto.builder()
	            .memberNo(userEntity.getMemberNo())
	            .memberId(userEntity.getMemberId())
	            .memberPassword(userEntity.getMemberPassword())
	            .memberEmail(userEntity.getMemberEmail())
	            .memberName(userEntity.getMemberName())
	            .memberRole(userEntity.getMemberRole())
	            .memberPoints(userEntity.getMemberPoints())
	            .memberStatus(userEntity.getMemberStatus())
	            .memberJoinDate(userEntity.getMemberJoinDate())
	            .memberReportCount(userEntity.getMemberReportCount())
	            .build();
	}
	
	
}
