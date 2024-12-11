package com.itwill.jpa.service.report;

import com.itwill.jpa.dto.report.ReportDto;
import com.itwill.jpa.entity.report.Report;

import java.util.List;

public interface ReportService {
	
	ReportDto createReport(ReportDto reportDto);
	ReportDto updateReportStatusToInProgress(Long reportNo);
	ReportDto updateReportStatusToResolved(Long reportNo);
	ReportDto updateReportStatusToFalseReport(Long reportNo);
	ReportDto getReportByreportNo(Long reportNo);
	List<ReportDto> getReportByUserNo(Long memberNo);
	List<ReportDto> getReportAll(Integer status);
}
