package com.itwill.jpa.service.report;

import com.itwill.jpa.dto.report.ReportDto;
import com.itwill.jpa.entity.report.Report;

import java.util.List;

public interface ReportService {
	
	void createReport(ReportDto reportDto);
	void updateReportStatusToInProgress(Long reportNo);
	void updateReportStatusToResolved(Long reportNo);
	void updateReportStatusToFalseReport(Long reportNo);
	void updateReportStatusToCancel(Long reportNo);
	public ReportDto getReportByreportNo(Long reportNo);
	public List<ReportDto> getReportByUserNo(Long memberNo);
	public List<ReportDto> getReportAll();
}
