package com.itwill.jpa.service.report;

import com.itwill.jpa.dto.report.ReportDto;
import com.itwill.jpa.entity.report.Report;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ReportService {
	
	ReportDto createReport(ReportDto reportDto);
//	ReportDto updateReportStatusToInProgress(Long reportNo);
	ReportDto updateReportStatusToResolved(Long reportNo);
	ReportDto updateReportStatusToFalseReport(Long reportNo);
	ReportDto getReportByreportNo(Long reportNo);
	Page<ReportDto> getReportAll(Integer filter,int pageNumber, int pageSize);
}
