package com.itwill.jpa.service.report;

import com.itwill.jpa.dto.report.ReportDto;
import com.itwill.jpa.entity.report.Report;

import java.util.List;

public interface ReportService {
	
	void saveReport(ReportDto reportDto);
	void updateReportStatusToInProgress(Long reportNo);
	void updateReportStatusToResolved(Long reportNo);
	void updateReportStatusToFalseReport(Long reportNo);
	void updateReportStatusToCancel(Long reportNo);
	public ReportDto selectReportByreportNo(Long reportNo);
	public List<ReportDto> selectReportByUserNo(Long memberNo);
	public List<ReportDto> selectReportAll();
}
