package com.itwill.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long>{
    List<Report> findByMemberMemberNo(Long memberNo);
}
