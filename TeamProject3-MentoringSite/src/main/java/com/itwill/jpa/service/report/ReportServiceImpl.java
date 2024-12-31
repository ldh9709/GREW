package com.itwill.jpa.service.report;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.undo.CannotUndoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.dto.report.ReportDto;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.report.Report;
import com.itwill.jpa.exception.CustomException;
import com.itwill.jpa.repository.bullentin_board.AnswerRepository;
import com.itwill.jpa.repository.bullentin_board.InquiryRepository;
import com.itwill.jpa.repository.member_information.MemberRepository;
import com.itwill.jpa.repository.report.ReportRepository;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.bullentin_board.AnswerService;
import com.itwill.jpa.service.bullentin_board.InquiryService;
import com.itwill.jpa.service.member_information.MemberService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;
	@Autowired
	private InquiryRepository inquiryRepository;
	@Autowired
	private MemberService memberService;
	@Autowired
	private InquiryService inquiryService;
	@Autowired
	private AnswerService answerService;
	
	/*신고등록*/
	@Override
	public ReportDto createReport(ReportDto reportDto){
		try {
			Report report = Report.toEntity(reportDto);
			reportRepository.save(report);
			return reportDto;
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.CREATED_REPORT_FAIL, ResponseMessage.CREATED_REPORT_FAIL, e);
		}
	} 

	/* [어드민] 신고 상태 변경 : 접수중 
	@Transactional
	@Override
	public ReportDto updateReportStatusToInProgress(Long reportNo) {
		try {
			Report report = reportRepository.findById(reportNo).get();
			report.setReportStatus(2);
			reportRepository.save(report);
			return ReportDto.toDto(reportRepository.findById(reportNo).get()); 
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.UPDATE_REPORT_FAIL, ResponseMessage.UPDATE_REPORT_FAIL, e);
		}
	}
	*/
	/* [어드민] 신고 상태 변경 : 처리완료 */
	@Transactional
	@Override
	public ReportDto updateReportStatusToResolved(Long reportNo) {
		try {
			Report report = reportRepository.findById(reportNo).get();
			System.out.println("처리완료" + report.getReportType());
			System.out.println("처리완료" + report.getReportTarget());
			if(report == null) {
				throw new IllegalArgumentException("report 생성 오류");
			}
			
			report.setReportStatus(2);
			
			/* type:MEMBER인 경우 
			 * - 해당 멤버 신고 카운트 증가 
			 * */
			if(report.getReportType().equals("MEMBER")) {
				memberService.incrementReportCount(report.getReportTarget());
			}
			
			/* 
			 * type:ANSWER인 경우
			 * - 해당 게시글 상태변경
			 * - 해당 게시글 작성자 신고 카운트 증가
			 * */
			if(report.getReportType().equals("ANSWER")) {
				try {
					AnswerDto answer = answerService.deleteAnswer(report.getReportTarget());
					Long writeNo = answer.getMemberNo();
					memberService.incrementReportCount(writeNo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			/* type:INQUIRY인 경우 
			 * - 해당 게시글 상태변경
			 * - 해당 게시글 작성자 카운트 증가 
			 * */
			if(report.getReportType().equals("INQUIRY")) {
				try {
					InquiryDto inquiry = inquiryService.deleteInquiry(report.getReportTarget());
					Long writerNo = inquiry.getMemberNo();
					memberService.incrementReportCount(writerNo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			report.setResolvedDate(LocalDateTime.now());
			return ReportDto.toDto(reportRepository.findById(reportNo).get());
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.UPDATE_REPORT_FAIL, ResponseMessage.UPDATE_REPORT_FAIL, e);
		}
	}
	
	/* [어드민] 신고 상태 변경 : 무고처리 */
	@Transactional
	@Override
	public ReportDto updateReportStatusToFalseReport(Long reportNo) {
		try {
			Report report = reportRepository.findById(reportNo).get();
			report.setReportStatus(3);
			report.setResolvedDate(LocalDateTime.now());
			return ReportDto.toDto(reportRepository.findById(reportNo).get());
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.UPDATE_REPORT_FAIL, ResponseMessage.UPDATE_REPORT_FAIL, e);
		}
	}
	
	
	/* 신고 정보 상세 보기 */ 
	public ReportDto getReportByreportNo(Long reportNo) {
		try {
			Report report = reportRepository.findById(reportNo).get();
			ReportDto reportDto = ReportDto.toResponseDto(report);
			return reportDto;
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_REPORT_FAIL, ResponseMessage.READ_REPORT_FAIL, e);
		}
	}
	
	

	/* [어드민] 신고 전체 출력 
	 * 필터링, 기본 순서 No
	 * 1 : 전체 , 2: 신고접수 3: 처리완료 4:무고처리 
	 * */
	@Override
	public Page<ReportDto> getReportAll(Integer filter,int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Report> reports = null;
			List<ReportDto> reportDtos = new ArrayList<>();
			
			switch (filter) {
				case 1: {
					/* 전체 출력 (번호 내림차순)*/
					reports = reportRepository.findAllByOrderByReportNoDesc(pageable);
					break;
				}
				case 2: {
					/* 신고 접수 출력 */
					reports = reportRepository.findByReportStatusOrderByReportNoDesc(1, pageable);
					break;
				}
				case 3: {
					/* 처리완료 신고 출력 */
					reports = reportRepository.findByReportStatusOrderByReportNoDesc(2, pageable);
					break;
				}
				case 4: {
					/* 무고처리 신고 출력 */
					reports = reportRepository.findByReportStatusOrderByReportNoDesc(3, pageable);
					break;
				}
			}
			
			for (Report report : reports) {
				reportDtos.add(ReportDto.toResponseDto(report));
			}
			return new PageImpl<>(reportDtos, pageable, reports.getTotalElements());
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_REPORT_LIST_FAIL, ResponseMessage.READ_REPORT_LIST_FAIL, e);
		}
	}

}

