package com.itwill.jpa.repository.report;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.report.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long>{

	//전체 리스트 출력(최신순)
	List<Report> findAllByOrderByReportDateDesc();
	
	//신고 접수완료 리스트 출력(최신순)
	List<Report> findByReportStatusOrderByReportDateDesc(Integer status);
	
	//member 번호로 신고 리스트 출력(최신순)
    List<Report> findByMemberMemberNoOrderByReportDateDesc(Long memberNo);
    
    //reportNo로 신고자 출력
    Report findByReportNo(Long reportNo);
    
}
