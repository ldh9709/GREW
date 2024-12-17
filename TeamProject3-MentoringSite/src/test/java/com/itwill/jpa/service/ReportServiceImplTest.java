package com.itwill.jpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.jpa.dto.report.ReportDto;
import com.itwill.jpa.service.report.ReportService;

@SpringBootTest
class ReportServiceImplTest {
	
	@Autowired
	private ReportService reportService;
	
//	@Test
	void testSaveReport() {
		ReportDto report = ReportDto.builder()
				.reportNo(0L)
				.reportType("ANSWER")
				.reportTarget(1L)
				.reportReason(2)
				.reportContent("흥")
				.memberNo(1L)
				.build();
		reportService.createReport(report);
	}

//	@Test
	void updateReportStatusToInProgress() {
		reportService.updateReportStatusToInProgress(2L);
	}
	@Test
	void updateReportStatusToResolved() {
		reportService.updateReportStatusToResolved(2L);
	}

//	@Test
	void testUpdateStatusReport() {
		reportService.updateReportStatusToFalseReport(3L);
	}
}
