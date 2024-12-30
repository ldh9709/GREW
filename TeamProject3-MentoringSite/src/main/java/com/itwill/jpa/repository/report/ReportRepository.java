package com.itwill.jpa.repository.report;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.report.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long>{

	//전체 리스트 출력(최신순)
	Page<Report> findAllByOrderByReportNoDesc(Pageable pageable);
	
	//신고 접수완료 리스트 출력(최신순)
	Page<Report> findByReportStatusOrderByReportNoDesc(Integer status, Pageable pageble);
    
    //reportNo로 신고자 출력
    Report findByReportNo(Long reportNo);
    
}
