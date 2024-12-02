package com.itwill.jpa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.dto.report.ReportDto;
import com.itwill.jpa.entity.report.Report;
import com.itwill.jpa.entity.user_information.Member;
import com.itwill.jpa.repository.MemberRepository;
import com.itwill.jpa.repository.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	/*신고등록*/
	@Override
	public void saveReport(ReportDto reportDto){
		Report report = Report.toEntity(reportDto);
		System.out.println(report);
		reportRepository.save(report);
	}

	/* [어드민] 신고 상태 변경 : 접수중 */
	@Transactional
	@Override
	public void updateReportStatusToInProgress(Long reportNo) {
		Report report = reportRepository.findById(reportNo).get();
		report.setReportStatus(2);
		reportRepository.save(report);
	}
	
	/* [어드민] 신고 상태 변경 : 처리완료 */
	@Transactional
	@Override
	public void updateReportStatusToResolved(Long reportNo) {
		Report report = reportRepository.findById(reportNo).get();
		report.setReportStatus(3);
		
		//멤버 신고 카운트 증가
		if(report.getReportType().equals("USER")) {
			memberRepository.incrementReportCount(report.getReportTarget());
		}

		/* report type, target 찾아서 상태 변경 내용 추가*/
		
		report.setResolvedDate(LocalDateTime.now());
		reportRepository.save(report);
	}
	
	/* [어드민] 신고 상태 변경 : 무고처리 */
	@Transactional
	@Override
	public void updateReportStatusToFalseReport(Long reportNo) {
		Report report = reportRepository.findById(reportNo).get();
		report.setReportStatus(4);
		report.setResolvedDate(LocalDateTime.now());
		reportRepository.save(report);
	}
	
	/*신고 취소*/
	@Override
	public void updateReportStatusToCancel(Long reportNo) {
			Report report = reportRepository.findById(reportNo).get();
			report.setReportStatus(5);
			reportRepository.save(report);
	}
	
	/* 신고 1개 출력 */ 
	public ReportDto selectReportByreportNo(Long reportNo) {
		Report report = reportRepository.findById(reportNo).get();
		ReportDto reportDto = ReportDto.toDto(report);
		return reportDto;
	}
	
	
	/* 신고 출력(특정 회원) */
	@Override
	public List<ReportDto> selectReportByUserNo(Long memberNo) {
		List<Report> reports= reportRepository.findByMemberMemberNo(memberNo);
		List<ReportDto> reportDtos = new ArrayList<ReportDto>();
		for (Report report : reports) {
			reportDtos.add(ReportDto.toDto(report));
		}
		return reportDtos;
	}

	/* [어드민] 신고 전체 출력 */
	@Override
	public List<ReportDto> selectReportAll() {
		List<Report> reports = reportRepository.findAll();
		List<ReportDto> reportDtos = new ArrayList<>();
		for (Report report : reports) {
			reportDtos.add(ReportDto.toDto(report));
		}
		return reportDtos;
	}
}

