package com.itwill.jpa.dto;

import java.time.LocalDateTime;

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
public class ReportDto {
	private Long reportNo;
	private String reportType;
	private Integer reportTarget;
	private Integer reportReason;
	private String reportContent;
	private LocalDateTime reportDate;
	private LocalDateTime resolveDate;
	private Integer reportStatus;
	private Long memberNo;
	
	public static Report toEntity(ReportDto dto) {
		Member member = Member.builder()
				.memberNo(dto.memberNo)
				.build();
		
		return Report.builder()
				.reportNo(dto.getReportNo())
				.reportType(dto.getReportType())
				.reportTarget(dto.getReportTarget())
				.reportReason(dto.getReportReason())
				.reportContent(dto.getReportContent())
				.reportDate(dto.getReportDate())
				.resolvedDate(dto.getResolveDate())
				.reportStatus(dto.getReportStatus())
				.member(member)
				.build();
	}
}
