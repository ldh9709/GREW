package com.itwill.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.ReportDto;
import com.itwill.jpa.entity.Report;
import com.itwill.jpa.repository.ReportRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;
	
	/*신고등록*/
	@Override
	public void saveReport(ReportDto reportDto){
		try {
			Report report = ReportDto.toEntity(reportDto);
			reportRepository.save(report);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*신고 취소*/
	@Override
	public void updateReportStatusToCancel(Long reportNo) {
		try {
			Report report = reportRepository.findById(reportNo).get();
			report.setReportStatus(4);
			reportRepository.save(report);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* (어드민) 신고 상태 변경 : 검토중 */
	@Override
	public void updateReportStatusToInProgress(Long reportNo) {
		Report report = reportRepository.findById(reportNo).get();
		report.setReportStatus(2);
		reportRepository.save(report);
	}
	/* (어드민) 신고 상태 변경 : 처리완료 */
	@Override
	public void updateReportStatusToResolved(Long reportNo) {
		Report report = reportRepository.findById(reportNo).get();
		report.setReportStatus(3);
		report.setResolvedDate(LocalDateTime.now());
		reportRepository.save(report);
	}

	/* 신고 출력(특정 회원) */
	@Override
	public List<ReportDto> selectReportByUserNo(Long userNo) {
		List<Report> reports= reportRepository.findByMemberMemberNo(userNo);
		List<ReportDto> reportDtos = new ArrayList<ReportDto>();
		for (Report report : reports) {
			reportDtos.add(Report.toDto(report));
		}
		return reportDtos;
	}

	/*신고 전체 출력(어드민)*/
	@Override
	public List<ReportDto> selectReportAll() {
		List<Report> reports = reportRepository.findAll();
		List<ReportDto> reportDtos = new ArrayList<>();
		for (Report report : reports) {
			reportDtos.add(Report.toDto(report));
		}
		return reportDtos;
	}
}

