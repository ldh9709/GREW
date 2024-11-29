package com.itwill.jpa.dto;

import java.time.LocalDate;

import com.itwill.jpa.entity.Member;
import com.itwill.jpa.entity.Report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequestDto {
	/* 신고등록(입력) 데이터 */
	private Long reportNo;
	private String reportType;
	private Integer reportTarget;
	private Integer reportReason;
	private String reportContent;
	private Long memberNo;
	
	public static Report toEntity(ReportRequestDto dto) {
		Member member = Member.builder()
				.memberNo(dto.memberNo)
				.build();
		
		return Report.builder()
				.reportNo(dto.getReportNo())
				.reportType(dto.getReportType())
				.reportTarget(dto.getReportTarget())
				.reportReason(dto.getReportReason())
				.reportContent(dto.getReportContent())
				.member(member)
				.build();
	}
}
