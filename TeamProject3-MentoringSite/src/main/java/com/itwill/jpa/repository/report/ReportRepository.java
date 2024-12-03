package com.itwill.jpa.repository.report;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.report.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long>{
	//member 번호로 신고 리스트 출력
    List<Report> findByMemberMemberNo(Long memberNo);
}
