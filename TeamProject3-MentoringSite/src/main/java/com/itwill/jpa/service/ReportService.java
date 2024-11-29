package com.itwill.jpa.service;

import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.ReportRequestDto;

public interface ReportService {
	
	void saveReport(ReportRequestDto report); 
	void getReport(Long reportNo); 
	void updateStatusReport(ReportRequestDto report); 
}
