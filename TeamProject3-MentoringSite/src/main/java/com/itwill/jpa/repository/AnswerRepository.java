package com.itwill.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.bullentin_board.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
	List<Answer> findByInquiryNo(Long inquiryNo);
}
