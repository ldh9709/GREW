package com.itwill.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.ReportRequestDto;
import com.itwill.jpa.entity.Report;
import com.itwill.jpa.repository.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;
	
	/*신고등록*/
	@Override
	public void saveReport(ReportRequestDto reportDto){
		try {
			Report report = ReportRequestDto.toEntity(reportDto);
			reportRepository.save(report);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getReport(Long reportNo) {
		
	}

	@Override
	public void updateStatusReport(ReportRequestDto report) {
		
	}
}

