package com.itwill.jpa.dto.report;

import java.time.LocalDateTime;

import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.report.Report;

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
	@Builder.Default
	private LocalDateTime reportDate = LocalDateTime.now();
	@Builder.Default
	private LocalDateTime resolvedDate = LocalDateTime.now();
	@Builder.Default
	private Integer reportStatus = 1 ;
	private Long memberNo;
	private String memberId;
	
	public static ReportDto toResponseDto(Report entity) {
		
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
				.memberId(entity.getMember().getMemberId())
				.build();
	}
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
