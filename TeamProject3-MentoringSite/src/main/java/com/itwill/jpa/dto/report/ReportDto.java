package com.itwill.jpa.dto.report;

import java.time.LocalDateTime;

import com.itwill.jpa.entity.report.Report;
import com.itwill.jpa.entity.user_information.Member;

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
	private Long reportTarget;
	private Integer reportReason;
	private String reportContent;
	private LocalDateTime reportDate;
	private LocalDateTime resolvedDate;
	private Integer reportStatus;
	private Long memberNo;
	
	public static ReportDto toDto(Report entity) {
		
		return ReportDto.builder()
				.reportNo(entity.getReportNo())
				.reportType(entity.getReportType())
				.reportTarget(entity.getReportTarget())
				.reportReason(entity.getReportReason())
				.reportContent(entity.getReportContent())
				.reportDate(entity.getReportDate())
				.resolvedDate(entity.getResolvedDate())
				.reportStatus(entity.getReportStatus())
				.memberNo(entity.getMember().getMemberNo())
				.build();
	}
}
