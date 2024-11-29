package com.itwill.jpa.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.jpa.dto.ReportRequestDto;

@SpringBootTest
class ReportServiceImplTest {
	
	@Autowired
	private ReportService reportService;
	
	@Test
	void testSaveReport() {
		ReportRequestDto report = ReportRequestDto.builder()
				.reportNo(0L)
				.reportType("ANSWER")
				.reportTarget(1)
				.reportReason(1)
				.reportContent("나한테 뭐라함 싸갈쓰바갈쓰")
				.memberNo(1L)
				.build();
		reportService.saveReport(report);
	}

	@Test
	void testGetReport() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateStatusReport() {
		fail("Not yet implemented");
	}

}
