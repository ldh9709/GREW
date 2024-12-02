package com.itwill.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.bullentin_board.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
	/*질문 하나에 달린 답변 리스트*/
	List<Answer> findByInquiryNo(Long inquiryNo);
	
	/*카테고리별 답변 리스트(검색)*/
}
