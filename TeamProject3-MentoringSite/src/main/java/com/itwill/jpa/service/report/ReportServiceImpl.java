package com.itwill.jpa.service.report;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.dto.report.ReportDto;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.report.Report;
import com.itwill.jpa.repository.bullentin_board.AnswerRepository;
import com.itwill.jpa.repository.bullentin_board.InquiryRepository;
import com.itwill.jpa.repository.member_information.MemberRepository;
import com.itwill.jpa.repository.report.ReportRepository;
import com.itwill.jpa.service.bullentin_board.AnswerService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private InquiryRepository inquiryRepository;
	@Autowired
	private AnswerService answerService;
	
	/*신고등록*/
	@Override
	public ReportDto createReport(ReportDto reportDto){
		Report report = Report.toEntity(reportDto);
		reportRepository.save(report);
		return reportDto;
	}

	/* [어드민] 신고 상태 변경 : 접수중 */
	@Transactional
	@Override
	public ReportDto updateReportStatusToInProgress(Long reportNo) {
		Report report = reportRepository.findById(reportNo).get();
		report.setReportStatus(2);
		reportRepository.save(report);
		return ReportDto.toDto(reportRepository.findById(reportNo).get()); 
	}
	
	/* [어드민] 신고 상태 변경 : 처리완료 */
	@Transactional
	@Override
	public ReportDto updateReportStatusToResolved(Long reportNo) {
		Report report = reportRepository.findById(reportNo).get();
		report.setReportStatus(3);
		
		/* type:MEMBER인 경우 멤버 신고 카운트 증가 */
		if(report.getReportType().equals("MEMBER")) {
			memberRepository.incrementReportCount(report.getReportTarget());
		}

		/* type:ANSWER인 경우 해당 게시글 상태변경 */
		if(report.getReportType().equals("ANSWER")) {
		}
		
		/* type:INQUIRY인 경우 해당 게시글 상태변경 */
		if(report.getReportType().equals("INQUIRY")) {
			inquiryRepository.delete(null);
		}
		report.setResolvedDate(LocalDateTime.now());
		return ReportDto.toDto(reportRepository.findById(reportNo).get());
	}
	
	/* [어드민] 신고 상태 변경 : 무고처리 */
	@Transactional
	@Override
	public ReportDto updateReportStatusToFalseReport(Long reportNo) {
		Report report = reportRepository.findById(reportNo).get();
		report.setReportStatus(4);
		report.setResolvedDate(LocalDateTime.now());
		return ReportDto.toDto(reportRepository.findById(reportNo).get());
	}
	
	
	/* 신고 정보 상세 보기 */ 
	public ReportDto getReportByreportNo(Long reportNo) {
		Report report = reportRepository.findById(reportNo).get();
		ReportDto reportDto = ReportDto.toDto(report);
		return reportDto;
	}
	
	
	/* 신고 출력(특정 회원) */
	@Override
	public List<ReportDto> getReportByUserNo(Long memberNo) {
		List<Report> reports= reportRepository.findByMemberMemberNo(memberNo);
		List<ReportDto> reportDtos = new ArrayList<ReportDto>();
		for (Report report : reports) {
			reportDtos.add(ReportDto.toDto(report));
		}
		return reportDtos;
	}

	/* [어드민] 신고 전체 출력 
	 * 필터링: 상태별
	 * 1 : 전체 , 2: 
	 * */
	@Override
	public List<ReportDto> getReportAll(Integer status) {
		List<Report> reports = reportRepository.findAll();
		List<ReportDto> reportDtos = new ArrayList<>();
		for (Report report : reports) {
			reportDtos.add(ReportDto.toDto(report));
		}
		return reportDtos;
	}
}

