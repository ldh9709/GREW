package com.itwill.jpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.jpa.dto.report.ReportDto;

@SpringBootTest
class ReportServiceImplTest {
	
	@Autowired
	private ReportService reportService;
	
//	@Test
	void testSaveReport() {
		ReportDto report = ReportDto.builder()
				.reportNo(0L)
				.reportType("ANSWER")
				.reportTarget(1)
				.reportReason(2)
				.reportContent("흥")
				.memberNo(1L)
				.build();
		reportService.saveReport(report);
	}

//	@Test
	void updateReportStatusToCancel() {
		reportService.updateReportStatusToCancel(3L);
	}
//	@Test
	void updateReportStatusToInProgress() {
		reportService.updateReportStatusToInProgress(3L);
	}

//	@Test
	void testUpdateStatusReport() {
		reportService.updateReportStatusToResolved(3L);
	}
//	@Test
	void testSelectByuserNo(){
		System.out.println(reportService.selectReportByUserNo(1L));
	}

	@Test
	void testSelectAll(){
		System.out.println(reportService.selectReportAll());
	}
}
